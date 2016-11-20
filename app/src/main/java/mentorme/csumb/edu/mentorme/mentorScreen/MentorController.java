package mentorme.csumb.edu.mentorme.mentorScreen;

import mentorme.csumb.edu.mentorme.mentorMeApi.mentorMeImpl.Factory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Donkey on 11/16/16.
 */

public class MentorController implements MentorLayout.MentorLayoutListener{

    private MentorActivity mActivity;
    private MentorLayout mMentorLayout;

    public MentorController(MentorActivity activity) {

        mActivity = activity;
        mMentorLayout = new MentorLayout(mActivity, this);

        onAttach();
    }

    private void onAttach() {
        Factory.getInstance().getMentors()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mMentorLayout);
    }


    @Override
    public void onNavigationMenuClick() { }
}
