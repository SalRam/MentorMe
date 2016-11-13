package mentorme.csumb.edu.mentorme.topicScreen;

import android.os.Bundle;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;

/**
 * Activity class for the Topics Activity.
 */
public class TopicActivity extends MentorMeActivity {

    private TopicController mTopicsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTopicsController = new TopicController(this);
    }
}
