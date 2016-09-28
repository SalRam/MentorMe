package mentorme.csumb.edu.mentorme.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by benitosanchez on 9/29/16.
 */
public class LoginController
        implements GoogleApiClient.OnConnectionFailedListener,
        LoginModel.GoogleLoginListener {

    private static final int RC_SIGN_IN = 9001;

    private LoginModel mLoginModel;
    private LoginModel.GoogleLoginListener mGoogleLoginListener;
    private LoginControllerListener mListener;
    private ProgressDialog mProgressDialog;
    private Context mContext;

    private final String TAG = "LoginController";

    public LoginController(Context context, LoginControllerListener controllerListener) {
        mLoginModel = new LoginModel(this);
        mListener = controllerListener;
    }

    public void sigInResult(int requestCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    public void  initialSignIn(GoogleApiClient googleApiClient) {
        mLoginModel.startSignIn(googleApiClient);
    }

    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            mListener.onGoogleSignInResultCompleted(result);
        } else {
            mListener.onGoogleSignInResultFail();
        }
    }

    @Override
    public void onGoogleSignInResultCompleted(GoogleSignInResult result) {
        mListener.onGoogleSignInResultCompleted(result);
    }

    public GoogleApiClient getGoogleApiClient(AppCompatActivity activity) {
        return mLoginModel.getGoogleApiClient(activity, this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }

    public interface LoginControllerListener {
        void onGoogleSignInResultCompleted(GoogleSignInResult result);
        void onGoogleSignInResultFail();
    }
}
