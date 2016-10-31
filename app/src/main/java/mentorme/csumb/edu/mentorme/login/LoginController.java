package mentorme.csumb.edu.mentorme.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import mentorme.csumb.edu.mentorme.homeScreen.HomeActivity;


/**
 * Logic implementation
 */
public class LoginController implements LoginLayout.Listener {

    private static final int RC_SIGN_IN = 9001;
    private final String TAG = "LoginController";

    private LoginLayout mLoginLayout;
    private LoginModel mLoginModel;
    private GoogleApiClient mGoogleApiClient;
    private AppCompatActivity mActivity;

    public LoginController(@NonNull AppCompatActivity activity) {

        mActivity = activity;
        mLoginModel = new LoginModel();
        mLoginLayout = new LoginLayout(activity, this);
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
            mLoginLayout.showProgressDialog();
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
                startHomeActivity();
            }
            else if (result.getSignInAccount() != null) {
                Log.d(TAG, "EMAIL IS NOT VALID");

                AlertDialog alertDialog = new AlertDialog.Builder(mActivity).create();
                alertDialog.setTitle("Invalid Account");
                alertDialog.setMessage("Please log in with CSUMB account");
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                revokeAccess();
                            }
                        });
                alertDialog.show();
            }
        }
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
