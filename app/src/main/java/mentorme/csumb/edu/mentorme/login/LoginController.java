package mentorme.csumb.edu.mentorme.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import mentorme.csumb.edu.mentorme.homeScreen.HomeActivity;
import mentorme.csumb.edu.mentorme.login.googleSignInModel.GoogleApiSignInModel;
import mentorme.csumb.edu.mentorme.user.local.UserLocalStorage;


/**
 * Logic implementation
 */
public class LoginController implements LoginLayout.Listener, GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 9001;
    private final String TAG = "LoginController";

    private LoginLayout mLoginLayout;
    private LoginModel mLoginModel;
    private GoogleApiClient mGoogleApiClient;
    private GoogleApiSignInModel mGoogleApiSignInModel;
    private LoginActivity mActivity;

    private UserLocalStorage userLocalStorage;

    public LoginController(@NonNull LoginActivity activity) {

        mActivity = activity;
        mLoginModel = new LoginModel();
        mLoginLayout = new LoginLayout(activity, this);

        userLocalStorage = new UserLocalStorage(mActivity.getApplicationContext());
        mGoogleApiSignInModel = GoogleApiSignInModel.getInstance(mActivity);
    }

    /**
     * Creates Observable with Sign in data
     */
    public void initialSignIn(@NonNull GoogleApiClient googleApiClient) {

        OptionalPendingResult<GoogleSignInResult> opr = mLoginModel.startSignIn(googleApiClient);

        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }

    private void handleSignInResult(@NonNull GoogleSignInResult result) {
        mLoginLayout.hideProgressDialog();
        if (result.isSuccess()) {
            if (isEmailValid(result.getSignInAccount().getEmail())) {
                Log.d(TAG, "EMAIL IS VALID");
                logUserIn(result.getSignInAccount());
                startHomeActivity();
                mActivity.finish();
            }
            else if (result.getSignInAccount() != null) {
                Log.d(TAG, "EMAIL IS NOT VALID");

                revokeAccess();
                AccountDialog dialog = new AccountDialog(mActivity);
                dialog.show();
            }
        }
    }

    private void logUserIn(GoogleSignInAccount account) {
        userLocalStorage.storeUserData(account);
        userLocalStorage.setStudentLoggedIn(true);
        startHomeActivity();
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });
    }
    private boolean isEmailValid(String email) {
        return email.endsWith("@csumb.edu");
    }

    private void startHomeActivity() {
        Intent intent = new Intent(mActivity, HomeActivity.class);
        mActivity.startActivity(intent);
    }

    /**
     * Converts sign in results into an Observable
     *
     * @param requestCode expected for the result
     * @param data intent with sign-in information
     * @return signInResult as an observable
     */
    public void sigInResult(@NonNull int requestCode, @NonNull Intent data) {

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
    public void initialSubscriber(){
        mGoogleApiClient = mGoogleApiSignInModel.getGoogleApiClient(mActivity, this);
        initialSignIn(mGoogleApiClient);
    }

    @Override
    public void onLoginButtonClick() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mActivity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
