package mentorme.csumb.edu.mentorme.mentorMe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import mentorme.csumb.edu.mentorme.login.googleSignInModel.GoogleApiSignInModel;

/**
 * Super controller for MentorMe.
 */
public class MentorMeController {

    private final String TAG = "MentorMeController";
    private Context mContext;

    public MentorMeController(Context activity) {
        mContext = activity;
    }
}
