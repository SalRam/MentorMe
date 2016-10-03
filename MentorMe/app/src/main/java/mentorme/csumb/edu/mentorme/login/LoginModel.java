package mentorme.csumb.edu.mentorme.login;

import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

/**
 * Created by benitosanchez on 9/28/16.
 */
public class LoginModel {
    private static final String TAG = "LoginModel";
    private GoogleSignInOptions gso;

    LoginModel() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

    }

    public GoogleApiClient getGoogleApiClient(
            AppCompatActivity activity,
            GoogleApiClient.OnConnectionFailedListener listener) {
        return new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, listener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    public OptionalPendingResult<GoogleSignInResult> startSignIn(GoogleApiClient mGoogleApiClient) {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

            return opr;

    }
}

