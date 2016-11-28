package mentorme.csumb.edu.mentorme.profileScreen;

import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.user.local.User;
import mentorme.csumb.edu.mentorme.user.local.UserLocalStorage;

/**
 * Layout class for {@link ProfileActivity}
 */

public class ProfileLayout {

    private static final String TAG = "ProfileLayout";

    @BindView(R.id.toolbar_no_menu) Toolbar mToolbar;
    @BindView(R.id.toolbar_title) TextView mToolbarTitle;
    @BindView(R.id.toolbar_back_arrow) ImageView mBackArrow;
    @BindView(R.id.student_name) EditText mStudentDisplayName;
    @BindView(R.id.student_email) TextView mStudentEmail;

    private UserLocalStorage userLocalStorage;

    private ProfileActivity mActivity;

    public ProfileLayout(ProfileActivity activity) {
        mActivity = activity;

        mActivity.setContentView(R.layout.profile_layout);

        ButterKnife.bind(this, mActivity);
        mToolbarTitle.setText("Profile");
        mActivity.setSupportActionBar(mToolbar);

        userLocalStorage = new UserLocalStorage(mActivity);

        onAttach();
    }

    private void onAttach() {
        User user = userLocalStorage.getLoggedInUser();

        mStudentDisplayName.setText(user.getDisplayName());
        mStudentEmail.setText(user.getEmail());
    }

    @OnClick(R.id.toolbar_back_arrow)
    public void onBackArrowClicked() {
        mActivity.onBackPressed();
    }
}

