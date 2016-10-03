package mentorme.csumb.edu.mentorme.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import mentorme.csumb.edu.mentorme.R;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.loading_sign_in) ProgressBar mProgressBar;
    @BindView(R.id.login_button) Button mLoginButton;

    @VisibleForTesting
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;
    private LoginController mLoginController;

    private ProgressDialog mProgressDialog;

    private Subscription subscription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ** Needed for Fabric **//
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.login);

        mLoginController = new LoginController();

        mGoogleApiClient = mLoginController.getGoogleApiClient(this);

        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        showProgressDialog();

        subscription = mLoginController.initialSignIn(mGoogleApiClient)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoogleSignInResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressDialog();
                        Log.d(TAG, e.getMessage());
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(GoogleSignInResult result) {
                        hideProgressDialog();
                        Toast.makeText(
                                getApplicationContext(),
                                result.getSignInAccount().getEmail(),
                                Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLoginController.sigInResult(requestCode, data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoogleSignInResult>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressDialog();
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(GoogleSignInResult result) {
                        hideProgressDialog();
                        Toast.makeText(getApplicationContext(),
                                result.getSignInAccount().getEmail(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void singIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(LoginActivity.this);
            mProgressDialog.setMessage("Loading");
        }
        mProgressDialog.show();
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked() {
        singIn();
        showProgressDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

    }
}

