package mentorme.csumb.edu.mentorme.mentorScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import mentorme.csumb.edu.mentorme.MentorMeApp;
import mentorme.csumb.edu.mentorme.data.component.NetComponent;
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentor;
import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import mentorme.csumb.edu.mentorme.util.PerController;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Controller for the {@link MentorActivity}.
 */
public class MentorController implements TextWatcher {

    private MentorActivity mActivity;
    private ArrayList<Mentor> mMentors;
    @Inject MentorLayout mMentorLayout;
    @Inject Retrofit mRetrofit;

    public MentorController(@NonNull MentorActivity activity) {

        mActivity = activity;

        DaggerMentorController_MentorControllerComponent.builder()
                .netComponent(((MentorMeApp) mActivity.getApplicationContext()).getNetComponent())
                .mentorControllerModule(new MentorControllerModule(mActivity, this))
                .build()
                .inject(this);

        onAttach();
    }

    private void onAttach() {
        String subjectId = "";
        String topicId = "";
        Bundle bundle = mActivity.getIntent().getExtras();
        if (bundle != null) {
            subjectId = bundle.getString("subjectId");
            topicId = bundle.getString("topicId");
        }

        mRetrofit.create(MentorMeApi.class).getMentors(subjectId, topicId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mMentorLayout);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String string  = s.toString().toLowerCase();
        ArrayList<Mentor> mFilteredList = new ArrayList<>();

        mMentors = mMentorLayout.getMentors();

        if (!string.trim().isEmpty()) {
            if (!mMentors.isEmpty() && mMentors != null) {

                for (int i =0; i < mMentors.size(); i++) {
                    final String mentor = mMentors.get(i).getName().toLowerCase();
                    if (mentor.contains(s)) {
                        mFilteredList.add(mMentors.get(i));
                    }
                }
            }
        } else {
            mFilteredList = mMentors;
        }

        mMentorLayout.notifyTextChanged(mFilteredList);
    }

    @Override
    public void afterTextChanged(Editable s) { }

    /**
     * Component for {@link MentorController}
     */
    @PerController
    @Component(dependencies = NetComponent.class, modules = MentorControllerModule.class)
    interface MentorControllerComponent {
        void inject(MentorController mentorController);
    }

    /**
     * Module for {@link MentorController}
     */
    @Module
    static class MentorControllerModule {
        private final MentorActivity mActivity;
        private final MentorController mController;

        public MentorControllerModule(
                @NonNull MentorActivity activity,
                @NonNull MentorController controller) {
            mActivity = activity;
            mController = controller;
        }

        @Provides
        @PerController
        MentorLayout providesMentorLayout() {
            return new MentorLayout(mActivity, mController);
        }
    }
}
