package mentorme.csumb.edu.mentorme.addingMentor;

/**
 * Controller class for  AddingMentors.
 */


public class AddingMentorController implements AddingMentorLayout.AddingMentorLayoutListener{

    private AddingMentorActivity mActivity;

    private AddingMentorLayout mLayout;

    AddingMentorController(AddingMentorActivity activity) {

        mActivity = activity;
        mLayout = new AddingMentorLayout(mActivity, this);
    }

    @Override
    public void onNavigationMenuClick() { }
}
