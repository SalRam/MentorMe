package mentorme.csumb.edu.mentorme.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import rx.Subscriber;

/**
 * Contains the functionality for the results that need to be displayed
 */
public class LoginLayout extends Subscriber<GoogleSignInResult>{

    @BindView(R.id.loading_sign_in) ProgressBar mProgressBar;
    @BindView(R.id.login_button) Button mLoginButton;

    AppCompatActivity mActivity;
    Context mContext;
    private ProgressDialog mProgressDialog;
    private static final String TAG = "SignInActivity";

    private Listener listener;

    LoginLayout(AppCompatActivity activity, Listener listener){
        mActivity = activity;
        mContext= activity.getApplicationContext();

        mActivity.setContentView(R.layout.login);

        this.listener = listener;
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
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    /**
     * Shows progress dialog
     */
    public void showProgressDialog(){
        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(mActivity);
            mProgressDialog.setMessage("Loading");
        }
        mProgressDialog.show();
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked() {
        showProgressDialog();
        listener.onLoginButtonClick();

    }

    public interface Listener{
        void onLoginButtonClick();
    }
}
