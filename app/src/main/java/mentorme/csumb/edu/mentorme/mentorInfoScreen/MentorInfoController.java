package mentorme.csumb.edu.mentorme.mentorInfoScreen;

import mentorme.csumb.edu.mentorme.mentorMeApi.mentorMeImpl.Factory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Controller class for {@link MentorInfoActivity}.
 */
public class MentorInfoController {

    private MentorInfoActivity mActivity;
    private MentorInfoLayout mMentorInfoLayout;

    MentorInfoController(MentorInfoActivity activity) {
        mActivity = activity;
        mMentorInfoLayout = new MentorInfoLayout(mActivity);

        onAttach();
    }

    private void onAttach() {
        Factory.getInstance().getMentorInformation()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mMentorInfoLayout);
    }
}
