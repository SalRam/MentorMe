package mentorme.csumb.edu.mentorme.mentorInfoScreen;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.mentorInfo.MentorInfo;
import rx.Subscriber;

/**
 * Takes care of {@link MentorInfoActivity}'s layout
 */

public class MentorInfoLayout extends Subscriber<MentorInfo> {

    private final String TAG = "MentorInfoLayout";

    private MentorInfoActivity mActivity;

    @BindView(R.id.toolbar_no_menu) Toolbar mToolbar;
    @BindView(R.id.toolbar_title) TextView mToolbarTitle;
    @BindView(R.id.toolbar_back_arrow) ImageView mBackArrow;
    @BindView(R.id.network_error_layout) LinearLayout mNetworkErrorLayout;

    @BindView(R.id.mentor_name) TextView mMentorName;
    @BindView(R.id.mentor_email) TextView mMentorEmail;
    @BindView(R.id.mentor_phone_number) TextView mMentorPhoneNumber;
    @BindView(R.id.mentor_description) TextView mMentorDescription;

    MentorInfoLayout(MentorInfoActivity activity) {
        mActivity = activity;

        mActivity.setContentView(R.layout.activity_mentor_info);

        ButterKnife.bind(this, mActivity);
        mToolbarTitle.setText(R.string.mentor_info);
        mActivity.setSupportActionBar(mToolbar);
    }

    @Override
    public void onCompleted() { }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, e.getMessage());
        mNetworkErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNext(MentorInfo mentorInfo) {
        Log.d(TAG, "onNExt");

        mMentorName.setText(mentorInfo.getMentor().getName());
        mMentorEmail.setText(mentorInfo.getMentor().getEmail());
        mMentorPhoneNumber.setText(mentorInfo.getMentor().getPhoneNumber());
        mMentorDescription.setText(mentorInfo.getMentor().getDescription());
    }
}
