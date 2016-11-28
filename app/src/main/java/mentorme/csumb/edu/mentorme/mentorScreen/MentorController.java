package mentorme.csumb.edu.mentorme.mentorScreen;

import mentorme.csumb.edu.mentorme.mentorMeApi.mentorMeImpl.Factory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Controller for the {@link MentorActivity}.
 */
public class MentorController {

    private MentorActivity mActivity;
    private MentorLayout mMentorLayout;

    public MentorController(MentorActivity activity) {

        mActivity = activity;
        mMentorLayout = new MentorLayout(mActivity);

        onAttach();
    }

    private void onAttach() {
        Factory.getInstance().getMentors()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mMentorLayout);
    }
}
