package mentorme.csumb.edu.mentorme.homeScreen;

import android.os.Bundle;
import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;

/**
 * Activity class for the HomeActivity.
 */
public class HomeActivity extends MentorMeActivity {

    private static final String TAG = "HomeActivity";

    private HomeController mHomeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHomeController = new HomeController(this);
    }
}
