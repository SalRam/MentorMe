package mentorme.csumb.edu.mentorme.addingMentor;

import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.ToolbarSupport;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.backArrowToolbar.BackArrowToolbar;

/**
 * Handles user Interface for AddingMentors.
 */

public class AddingMentorLayout {

    private final String TAG = "AddingMentorLayout";

    private AddingMentorActivity mActivity;
    private AddingMentorLayoutListener mListener;
    private ToolbarSupport mToolbar;


    public AddingMentorLayout(AddingMentorActivity activity, AddingMentorLayoutListener listener) {

        mActivity = activity;
        mListener = listener;

        mActivity.setContentView(R.layout.topics_layout);

        ButterKnife.bind(this, mActivity);

        mToolbar = new BackArrowToolbar(mActivity, R.string.mentor_info);

    }

    public interface AddingMentorLayoutListener {
        void onNavigationMenuClick();
    }

}