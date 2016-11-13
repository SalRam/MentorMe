package mentorme.csumb.edu.mentorme.topicScreen;

import android.os.Bundle;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;

/**
 * Created by benitosanchez on 11/12/16.
 */

public class TopicActivity extends MentorMeActivity {

    private TopicController mTopicsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        mTopicsController = new TopicController(this);
    }
}
