package mentorme.csumb.edu.mentorme.homeScreen;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class HomeController {

    private AppCompatActivity mActivity;
    private HomeLayout mHomeLayout;

    HomeController(@NonNull AppCompatActivity activity) {
        mActivity = activity;
        mHomeLayout = new HomeLayout(activity);
        onAttach();
    }

    private void onAttach()  {
        MentorMeApi.Factory.getInstance().getSubjects()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mHomeLayout);
    }
}
