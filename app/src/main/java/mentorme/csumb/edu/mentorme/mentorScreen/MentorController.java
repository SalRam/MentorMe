package mentorme.csumb.edu.mentorme.mentorScreen;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import mentorme.csumb.edu.mentorme.MentorMeApp;
import mentorme.csumb.edu.mentorme.data.component.NetComponent;
import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import mentorme.csumb.edu.mentorme.mentorMeApi.mentorMeImpl.Factory;
import mentorme.csumb.edu.mentorme.util.PerController;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Controller for the {@link MentorActivity}.
 */
public class MentorController {

    private MentorActivity mActivity;
    @Inject MentorLayout mMentorLayout;
    @Inject Retrofit mRetrofit;

    public MentorController(@NonNull MentorActivity activity) {

        mActivity = activity;

        DaggerMentorController_MentorControllerComponent.builder()
                .netComponent(((MentorMeApp) mActivity.getApplicationContext()).getNetComponent())
                .mentorControllerModule(new MentorControllerModule(mActivity))
                .build()
                .inject(this);

        onAttach();
    }

    private void onAttach() {
        mRetrofit.create(MentorMeApi.class).getMentors()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mMentorLayout);
    }

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

        public MentorControllerModule(@NonNull MentorActivity activity) {
            mActivity = activity;
        }

        @Provides
        @PerController
        MentorLayout providesMentorLayout() {
            return new MentorLayout(mActivity);
        }
    }
}
