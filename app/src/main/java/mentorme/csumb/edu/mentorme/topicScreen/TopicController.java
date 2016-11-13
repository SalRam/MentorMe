package mentorme.csumb.edu.mentorme.topicScreen;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by benitosanchez on 11/12/16.
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
