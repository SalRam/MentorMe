package mentorme.csumb.edu.mentorme.topicScreen;

import android.support.v7.app.AppCompatActivity;

/**
 * Class to control the UI logic for {@link TopicActivity}
 */
public class TopicController implements TopicLayout.TopicLayoutListener {

    private AppCompatActivity mActivity;
    private TopicLayout mTopicLayout;

    TopicController(AppCompatActivity activity) {
        mActivity = activity;
        mTopicLayout = new TopicLayout(mActivity, this);
    }

    @Override
    public void onNavigationMenuClick() { }
}
