package mentorme.csumb.edu.mentorme.homeScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import mentorme.csumb.edu.mentorme.MentorMeApp;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.addingMentor.AddingMentorActivity;
import mentorme.csumb.edu.mentorme.data.component.NetComponent;
import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import mentorme.csumb.edu.mentorme.util.PerController;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Controller for the {@link HomeActivity}.
 */
public class HomeController implements HomeLayout.HomeLayoutListener {

    private HomeActivity mActivity;

    @Inject HomeLayout mHomeLayout;
    @Inject Retrofit mRetrofit;

    public HomeController(@NonNull HomeActivity activity) {

        mActivity = activity;

        DaggerHomeController_HomeComponent.builder()
                .netComponent(((MentorMeApp) mActivity.getApplicationContext()).getNetComponent())
                .homeControllerModule(new HomeControllerModule(mActivity, this))
                .build()
                .inject(this);

        onAttach();
    }

    private void onAttach()  {
        mRetrofit.create(MentorMeApi.class).getSubjects()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mHomeLayout);
    }
    @Override
    public void onNavigationMenuClick() { }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_mentor:
                Intent intent = new Intent(mActivity, AddingMentorActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mActivity.startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    /**
     * Component for {@link HomeController}
     */
    @PerController
    @Component(dependencies = NetComponent.class, modules = HomeControllerModule.class)
    interface HomeComponent {
        void inject(HomeController homeController);
    }

    /**
     * Module for {@link HomeController}
     * Provides a {@link HomeLayout}
     */
    @Module
    static class HomeControllerModule {
        private final HomeActivity mActivity;
        private final HomeLayout.HomeLayoutListener mListener;

        public HomeControllerModule(HomeActivity activity, HomeLayout.HomeLayoutListener listener) {
            mActivity = activity;
            mListener = listener;
        }

        @Provides
        @PerController
        HomeLayout providesHomeLayout() {
            return new HomeLayout(mActivity, mListener);
        }
    }

}
