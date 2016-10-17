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
import com.google.android.gms.common.api.ResultCallback;

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

    private static final int RC_SIGN_IN = 9001;
    private final String TAG = "LoginController";

    private LoginLayout mLoginLayout;
    private LoginModel mLoginModel;

    private GoogleApiClient mGoogleApiClient;

    AppCompatActivity mActivity;

    public LoginController(AppCompatActivity activity) {

        mActivity = activity;
        mLoginModel = new LoginModel();
        mLoginLayout = new LoginLayout(activity, this);
    }

    /**
     * Creates Observable with Sign in data
     */
    public void initialSignIn(GoogleApiClient googleApiClient) {

        OptionalPendingResult<GoogleSignInResult> opr = mLoginModel.startSignIn(googleApiClient);

        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    mLoginLayout.showProgressDialog();
                    handleSignInResult(result);
                }
            });
        }
    }

    public void handleSignInResult(GoogleSignInResult result) {
        Toast.makeText(mActivity.getApplicationContext(), result.getSignInAccount().getEmail(), Toast.LENGTH_SHORT).show();
        mLoginLayout.hideProgressDialog();
    }

    /**
     * Converts sign in results into an Observable
     *
     * @param requestCode expected for the result
     * @param data intent with sign-in information
     * @return signInResult as an observable
     */
    public void sigInResult(int requestCode, Intent data) {

        if (requestCode == RC_SIGN_IN) {
            final GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, result.toString());

            handleSignInResult(result);
        }
    }

    /**
     * Makes a request a GoogleApiClient and sends result to LoginLayout
     */
    public void onActivityResult(int requestCode, Intent data) {

        sigInResult(requestCode, data);
    }

    /**
     *  makes a request to see if the user is already signed in(Api client is signed up)
     */
    public void initialSubscriber(GoogleApiClient googleApiClient){
        mGoogleApiClient = googleApiClient;
        initialSignIn(googleApiClient);
    }

    @Override
    public void onLoginButtonClick() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mActivity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
