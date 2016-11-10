package mentorme.csumb.edu.mentorme.topicsScreen;

import android.os.Bundle;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;

/**
 * Activity for the {@link TopicsActivity}
 */
public class TopicsActivity extends MentorMeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
    }
}
