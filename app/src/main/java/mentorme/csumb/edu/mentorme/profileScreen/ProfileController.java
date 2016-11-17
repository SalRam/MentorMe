package mentorme.csumb.edu.mentorme.profileScreen;

import android.support.annotation.NonNull;

/**
 * Controller class for {@link ProfileActivity}.
 */

public class ProfileController {

    private ProfileActivity mActivity;
    private ProfileLayout mProfileLayout;

    public ProfileController(@NonNull ProfileActivity activity) {
        mActivity = activity;

        mProfileLayout = new ProfileLayout(mActivity);
    }
}
