package mentorme.csumb.edu.mentorme.login;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;

/**
 * Dialog that shows if selected account is not a CSUMB account.
 */
public class AccountDialog extends Dialog {

    private Activity mActivity;

    @BindView(R.id.okay_button) Button mOkayButton;
    @BindView(R.id.dialog_message) TextView mMessage;

    public AccountDialog(Activity activity) {
        super(activity);
        mActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invalid_account_dialog);
        setCanceledOnTouchOutside(false);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.okay_button)
    public void onOkayClicked() {
        dismiss();
    }

}
