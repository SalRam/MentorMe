package mentorme.csumb.edu.mentorme.login;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import java.util.Observable;

/**
 * Created by benitosanchez on 9/28/16.
 */
public class LoginModel {
    private static String TAG = "LoginModel";
    private GoogleSignInOptions gso;
    private GoogleLoginListener mGoogleLoginListener;

    LoginModel(GoogleLoginListener listener) {
        mGoogleLoginListener = listener;
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

    public void startSignIn(GoogleApiClient mGoogleApiClient) {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            mGoogleLoginListener.onGoogleSignInResultCompleted(opr.get());
        }
    }

    public interface GoogleLoginListener {
        void onGoogleSignInResultCompleted(GoogleSignInResult result);
    }
}

