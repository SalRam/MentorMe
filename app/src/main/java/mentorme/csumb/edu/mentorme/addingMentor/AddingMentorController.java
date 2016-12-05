package mentorme.csumb.edu.mentorme.addingMentor;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.ToolbarSupport;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.backArrowToolbar.BackArrowToolbar;

/**
 * Controller class for  AddingMentors.
 */


public class AddingMentorController implements AddingMentorLayout.AddingMentorLayoutListener{

    private AddingMentorActivity mActivity;

    private AddingMentorLayout mLayout;

    private ToolbarSupport mToolbar;

    AddingMentorController(AddingMentorActivity activity) {

        mActivity = activity;
        mLayout = new AddingMentorLayout(mActivity, this);

        mToolbar = new BackArrowToolbar(mActivity, R.string.add_mentor);
    }

    @Override
    public void onNavigationMenuClick() { }
}
