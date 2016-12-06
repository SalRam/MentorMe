package mentorme.csumb.edu.mentorme.addingMentor;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import mentorme.csumb.edu.mentorme.MentorMeApp;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.component.NetComponent;
import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import mentorme.csumb.edu.mentorme.util.PerController;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.ToolbarSupport;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.backArrowToolbar.BackArrowToolbar;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Controller class for  AddingMentors.
 */

public class AddingMentorController implements AddingMentorLayout.AddingMentorListener {

    private AddingMentorActivity mActivity;
    private AddingMentorLayout mLayout;
    private ToolbarSupport mToolbar;

    @Inject AddingMentorLayout mAddingMentorLayout;
    @Inject Retrofit mRetrofit;

    AddingMentorController(AddingMentorActivity activity) {

        mActivity = activity;
        mLayout = new AddingMentorLayout(mActivity);
        mToolbar = new BackArrowToolbar(mActivity, R.string.add_mentor);

        DaggerAddingMentorController_AddingMentorComponent.builder()
                .netComponent(((MentorMeApp) mActivity.getApplicationContext()).getNetComponent())
                .addingMentorModule(new AddingMentorModule(mActivity))
                .build()
                .inject(this);

        onAttach();
    }

    private void onAttach()  {
        mRetrofit.create(MentorMeApi.class).getSubjects()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mAddingMentorLayout);
    }

    @Override
    public void onNavigationMenuClick() { }

    /**
     * Component for {@link AddingMentorController}
     */
    @PerController
    @Component(dependencies = NetComponent.class, modules = AddingMentorModule.class)
    interface AddingMentorComponent{
        void inject(AddingMentorController addingMentorController);
    }

    /**
     * Module for {@link AddingMentorController}
     * Provides a {@link AddingMentorLayout}
     */
    @Module
    static class AddingMentorModule{

        private final AddingMentorActivity mActivity;

        public AddingMentorModule(AddingMentorActivity mActivity) {
            this.mActivity = mActivity;
        }

        @Provides
        @PerController
        AddingMentorLayout provideAddingMentorLayout() {

            return new AddingMentorLayout(mActivity);
        }
    }
}

