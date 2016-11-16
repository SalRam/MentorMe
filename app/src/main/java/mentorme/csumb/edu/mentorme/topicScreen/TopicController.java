package mentorme.csumb.edu.mentorme.topicScreen;

import android.support.v7.app.AppCompatActivity;

import mentorme.csumb.edu.mentorme.mentorMeApi.mentorMeImpl.Factory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Class to control the UI logic for {@link TopicActivity}
 */
public class TopicController implements TopicLayout.TopicLayoutListener {

    private TopicActivity mActivity;
    private TopicLayout mTopicLayout;

    TopicController(TopicActivity activity) {
        mActivity = activity;
        mTopicLayout = new TopicLayout(mActivity, this);

        onAttach();
    }

    private void onAttach() {
        Factory.getInstance().getTopics()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mTopicLayout);
    }

    @Override
    public void onNavigationMenuClick() { }
}
