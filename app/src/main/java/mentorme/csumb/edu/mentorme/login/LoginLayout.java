package mentorme.csumb.edu.mentorme.login;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import rx.Subscriber;

/**
 * Contains the functionality for the results that need to be displayed
 */
class LoginLayout extends Subscriber<GoogleSignInResult>{

    private static final String TAG = "SignInActivity";

    @BindView(R.id.loading_sign_in) ProgressBar mProgressBar;
    @BindView(R.id.login_button) Button mLoginButton;

    private AppCompatActivity mActivity;
    private ProgressDialog mProgressDialog;

    private Listener mListener;

    LoginLayout(AppCompatActivity activity, Listener listener){
        mActivity = activity;
        mActivity.setContentView(R.layout.login);

        mListener = listener;
        ButterKnife.bind(this, mActivity);
    }

    @Override
    public void onCompleted() { }

    @Override
    public void onError(Throwable e) {
        hideProgressDialog();
        Log.d(TAG, e.getMessage());
    }

    @Override
    public void onNext(GoogleSignInResult googleSignInResult) {
        hideProgressDialog();
    }

    /**
     * Hides progress dialog
     */
    void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    /**
     * Shows progress dialog
     */
    private void showProgressDialog(){
        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(mActivity);
            mProgressDialog.setMessage("Loading");
        }
        mProgressDialog.show();
    }

    @OnClick(R.id.login_button)
    void onLoginButtonClicked() {
        showProgressDialog();
        mListener.onLoginButtonClick();

    }

    interface Listener{
        void onLoginButtonClick();
    }
}
