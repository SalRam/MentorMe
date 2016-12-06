package mentorme.csumb.edu.mentorme.topicScreen;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import mentorme.csumb.edu.mentorme.MentorMeApp;
import mentorme.csumb.edu.mentorme.data.component.NetComponent;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subject;
import mentorme.csumb.edu.mentorme.data.model.topics.Topic;
import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import mentorme.csumb.edu.mentorme.util.PerController;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Class to control the UI logic for {@link TopicActivity}
 */
class TopicController implements TextWatcher {

    private TopicActivity mActivity;
    private ArrayList<Topic> mTopics;

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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String string  = s.toString().toLowerCase();
        ArrayList<Topic> mFilteredList = new ArrayList<>();

        mTopics = mTopicLayout.getTopics();

        if (!string.trim().isEmpty()) {
            if (!mTopics.isEmpty() && mTopics != null) {

                for (int i =0; i < mTopics.size(); i++) {
                    final String topic = mTopics.get(i).getTopic().toLowerCase();
                    final String subject = mTopics.get(i).getTitle().toLowerCase();
                    if (topic.contains(s) || subject.contains(s)) {
                        mFilteredList.add(mTopics.get(i));
                    }
                }
            }
        } else {
            mFilteredList = mTopics;
        }

        mTopicLayout.notifyTextChanged(mFilteredList);
    }

    @Override
    public void afterTextChanged(Editable s) { }

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
        private final TopicController mController;

        TopicControllerModule(TopicActivity activity, TopicController controller) {
            mActivity = activity;
            mController = controller;
        }

        @Provides
        @PerController
        TopicLayout providesTopicLayout() {
            return new TopicLayout(mActivity, mController);
        }
    }
}
