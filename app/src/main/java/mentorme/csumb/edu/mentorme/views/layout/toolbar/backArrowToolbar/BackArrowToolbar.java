package mentorme.csumb.edu.mentorme.views.layout.toolbar.backArrowToolbar;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.ToolbarSupport;

/**
 * Provides support for the back arrow toolbar.
 */
public class BackArrowToolbar implements ToolbarSupport {

    @BindView(R.id.toolbar_no_menu) Toolbar mToolbar;
    @BindView(R.id.toolbar_title) TextView mToolbarTitle;
    @BindView(R.id.toolbar_back_arrow) ImageView mBackArrow;

    private MentorMeActivity mActivity;

    public BackArrowToolbar(MentorMeActivity activity, int titleId) {

        mActivity = activity;
        ButterKnife.bind(this, mActivity);

        mToolbarTitle.setText(titleId);
        mActivity.setSupportActionBar(mToolbar);
    }

    @OnClick(R.id.toolbar_back_arrow)
    void onBackArrowClicked() {
        mActivity.onBackPressed();
    }
}
