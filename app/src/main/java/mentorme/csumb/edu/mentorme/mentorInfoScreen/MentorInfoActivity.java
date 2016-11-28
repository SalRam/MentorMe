package mentorme.csumb.edu.mentorme.mentorInfoScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mentorme.csumb.edu.mentorme.R;

/**
 * Activity for the Mentor's information screen.
 */
public class MentorInfoActivity extends AppCompatActivity {

    private MentorInfoController mMentorInfoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMentorInfoController = new MentorInfoController(this);
    }
}
