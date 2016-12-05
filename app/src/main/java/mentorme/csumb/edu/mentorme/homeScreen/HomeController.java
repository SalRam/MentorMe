package mentorme.csumb.edu.mentorme.homeScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import mentorme.csumb.edu.mentorme.MentorMeApp;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.addingMentor.AddingMentorActivity;
import mentorme.csumb.edu.mentorme.data.component.NetComponent;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subject;
import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import mentorme.csumb.edu.mentorme.util.PerController;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Controller for the {@link HomeActivity}.
 */
public class HomeController
        implements HomeLayout.HomeLayoutListener,
        TextWatcher {

    private HomeActivity mActivity;
    private ArrayList<Subject> mSubjects;

    @Inject HomeLayout mHomeLayout;
    @Inject Retrofit mRetrofit;


    public HomeController(@NonNull HomeActivity activity) {

        mActivity = activity;

        DaggerHomeController_HomeComponent.builder()
                .netComponent(((MentorMeApp) mActivity.getApplicationContext()).getNetComponent())
                .homeControllerModule(new HomeControllerModule(mActivity, this, this))
                .build()
                .inject(this);

        onAttach();
    }

    private void onAttach()  {
        mRetrofit.create(MentorMeApi.class).getSubjects()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mHomeLayout);

        mSubjects = mHomeLayout.getSubjects();
    }
    @Override
    public void onNavigationMenuClick() { }

    /**
     * Handles menu item click.
     *
     * @param item Menu item clicked.
     *
     * @return {@true} if item exists. {@false} otherwise.
     */
    boolean onOptionsItemSelected(MenuItem item) {
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String string  = s.toString().toLowerCase();
        ArrayList<Subject> mFilteredList = new ArrayList<>();

        mSubjects = mHomeLayout.getSubjects();

        if (!string.trim().isEmpty()) {
            if (!mSubjects.isEmpty() && mSubjects != null) {

                for (int i =0; i < mSubjects.size(); i++) {
                    final String subjectTile = mSubjects.get(i).getSubject().toLowerCase();
                    if (subjectTile.contains(s)) {
                        mFilteredList.add(mSubjects.get(i));
                    }
                }
            }
        } else {
            mFilteredList = mSubjects;
        }

        mHomeLayout.notifyTextChanged(mFilteredList);
    }

    @Override
    public void afterTextChanged(Editable s) { }

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
        private final HomeController mController;

        public HomeControllerModule(
                HomeActivity activity,
                HomeLayout.HomeLayoutListener listener,
                HomeController controller) {
            mActivity = activity;
            mListener = listener;
            mController = controller;
        }

        @Provides
        @PerController
        HomeLayout providesHomeLayout() {
            return new HomeLayout(mActivity, mListener, mController);
        }
    }

}
