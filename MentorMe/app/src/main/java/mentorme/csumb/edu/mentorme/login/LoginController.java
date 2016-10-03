package mentorme.csumb.edu.mentorme.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import rx.Observable;
import rx.functions.Func0;


/**
 * Created by benitosanchez on 9/29/16.
 */
public class LoginController
        implements GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 9001;

    private LoginModel mLoginModel;

    private final String TAG = "LoginController";

    public LoginController() {
        mLoginModel = new LoginModel();
    }

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

    public GoogleApiClient getGoogleApiClient(AppCompatActivity activity) {
        return mLoginModel.getGoogleApiClient(activity, this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }

}
