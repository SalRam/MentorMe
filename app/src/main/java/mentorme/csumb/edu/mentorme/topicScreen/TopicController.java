package mentorme.csumb.edu.mentorme.topicScreen;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import mentorme.csumb.edu.mentorme.MentorMeApp;
import mentorme.csumb.edu.mentorme.data.component.NetComponent;
import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import mentorme.csumb.edu.mentorme.util.PerController;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Class to control the UI logic for {@link TopicActivity}
 */
class TopicController {

    private TopicActivity mActivity;

    @Inject TopicLayout mTopicLayout;
    @Inject Retrofit mRetrofit;

    TopicController(TopicActivity activity) {
        mActivity = activity;

        DaggerTopicController_TopicControllerComponent.builder()
                .netComponent(((MentorMeApp) mActivity.getApplicationContext()).getNetComponent())
                .topicControllerModule(new TopicControllerModule(mActivity))
                .build()
                .inject(this);

        onAttach();
    }

    private void onAttach() {
        String subjectId = "";
        Bundle bundle = mActivity.getIntent().getExtras();
        if (bundle != null) {
            subjectId = bundle.getString("subjectId");
        }

        mRetrofit.create(MentorMeApi.class).getTopics(subjectId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mTopicLayout);
    }

    /**
     * Component for {@link TopicController}
     */
    @PerController
    @Component(dependencies = NetComponent.class, modules = TopicControllerModule.class)
    interface TopicControllerComponent {
        void inject(TopicController topicController);

    }

    /**
     * Module for {@link TopicController}
     */
    @Module
    static class TopicControllerModule {
        private final TopicActivity mActivity;

        TopicControllerModule(TopicActivity activity) {
            mActivity = activity;
        }

        @Provides
        @PerController
        TopicLayout providesTopicLayout() {
            return new TopicLayout(mActivity);
        }
    }
}
