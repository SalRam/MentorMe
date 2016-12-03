package mentorme.csumb.edu.mentorme.profileScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;

/**
 * Activity for the {@link ProfileActivity} screen.
 */
public class ProfileActivity extends MentorMeActivity {

    private ProfileController mProfileController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProfileController = new ProfileController(this);
    }

}
