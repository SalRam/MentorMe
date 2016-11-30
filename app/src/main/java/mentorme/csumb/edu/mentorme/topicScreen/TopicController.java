package mentorme.csumb.edu.mentorme.topicScreen;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import mentorme.csumb.edu.mentorme.MentorMeApp;
import mentorme.csumb.edu.mentorme.data.component.NetComponent;
import mentorme.csumb.edu.mentorme.mentorMeApi.mentorMeImpl.Factory;
import mentorme.csumb.edu.mentorme.util.PerController;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Class to control the UI logic for {@link TopicActivity}
 */
public class TopicController implements TopicLayout.TopicLayoutListener {

    private TopicActivity mActivity;

    @Inject TopicLayout mTopicLayout;
    @Inject Retrofit mRetrofit;

    TopicController(TopicActivity activity) {
        mActivity = activity;

        DaggerTopicController_TopicControllerComponent.builder()
                .netComponent(((MentorMeApp) mActivity.getApplicationContext()).getNetComponent())
                .topicControllerModule(new TopicControllerModule(mActivity, this))
                .build()
                .inject(this);

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

    @PerController
    @Component(dependencies = NetComponent.class, modules = TopicControllerModule.class)
    interface TopicControllerComponent {
        void inject(TopicController topicController);

    }

    @Module
    static class TopicControllerModule {
        private final TopicActivity mActivity;
        private final TopicLayout.TopicLayoutListener mListener;

        public TopicControllerModule(TopicActivity activity, TopicLayout.TopicLayoutListener listener) {
            mActivity = activity;
            mListener = listener;
        }

        @Provides
        @PerController
        TopicLayout providesTopicLayout() {
            return new TopicLayout(mActivity, mListener);
        }
    }
}
