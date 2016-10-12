package mentorme.csumb.edu.mentorme.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import mentorme.csumb.edu.mentorme.login.mentorMeActivity.GoogleApiSignInModel;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;


/**
 * Logic implementation
 */
public class LoginController implements LoginLayout.Listener {

    private LoginLayout mLoginLayout;

    private static final int RC_SIGN_IN = 9001;

    private LoginModel mLoginModel;

    private final String TAG = "LoginController";

    private GoogleApiClient mGoogleApiClient;

    AppCompatActivity mActivity;

    public LoginController(AppCompatActivity activity) {

        mActivity = activity;
        mLoginModel = new LoginModel();
        mLoginLayout = new LoginLayout(activity, this);
    }

    /**
     * Converts sign in results into an Observable
     * @param requestCode expected for the result
     * @param data intent with sign-in information
     * @return signInResult as an observable
     */
    public Observable<GoogleSignInResult> sigInResult(int requestCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            final GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            return Observable.defer(new Func0<Observable<GoogleSignInResult>>() {
                @Override
                public Observable<GoogleSignInResult> call() {
                    return Observable.just(result);
                }
            });
        }
        return null;
    }

    /**
     * Makes a request a GoogleApiClient and sends result to LoginLayout
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        sigInResult(requestCode, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mLoginLayout);
    }
    /**
     * Creates Observable with Sign in data
     */
    public Observable<GoogleSignInResult> initialSignIn(GoogleApiClient googleApiClient) {

        final OptionalPendingResult<GoogleSignInResult> opr = mLoginModel.startSignIn(googleApiClient);

        Log.d(TAG, "Got cached sign-in");
        return Observable.defer(new Func0<Observable<GoogleSignInResult>>() {
            @Override
            public Observable<GoogleSignInResult> call() {
                return Observable.just(opr.get());
            }
        });
    }
    /**
     *  makes a request to see if the user is already signed in(Api client is signed up)
     */
    public void initialSubscriber(GoogleApiClient googleApiClient){
        mGoogleApiClient = googleApiClient;
        initialSignIn(googleApiClient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mLoginLayout);
    }

    @Override
    public void onLoginButtonClick() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mActivity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
