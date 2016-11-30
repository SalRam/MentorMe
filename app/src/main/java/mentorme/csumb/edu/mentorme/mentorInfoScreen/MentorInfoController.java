package mentorme.csumb.edu.mentorme.mentorInfoScreen;

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
 * Controller class for {@link MentorInfoActivity}.
 */
public class MentorInfoController {

    private MentorInfoActivity mActivity;
    @Inject MentorInfoLayout mMentorInfoLayout;
    @Inject Retrofit mRetrofit;

    MentorInfoController(MentorInfoActivity activity) {
        mActivity = activity;

        DaggerMentorInfoController_MentorInfoComponent.builder()
                .netComponent(((MentorMeApp) mActivity.getApplicationContext()).getNetComponent())
                .mentorInfoModule(new MentorInfoModule(mActivity))
                .build()
                .inject(this);

        onAttach();
    }

    private void onAttach() {
        mRetrofit.create(MentorMeApi.class).getMentorInformation()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mMentorInfoLayout);
    }

    @PerController
    @Component(dependencies = NetComponent.class, modules = MentorInfoModule.class)
    interface MentorInfoComponent {
        void inject(MentorInfoController mentorInfoController);
    }

    @Module
    static class MentorInfoModule {
        private final MentorInfoActivity mActivity;

        public MentorInfoModule(MentorInfoActivity activity) {
            mActivity = activity;
        }

        @Provides
        @PerController
        MentorInfoLayout providesMentorInfoLayout() {
            return new MentorInfoLayout(mActivity);
        }
    }
}
