package mentorme.csumb.edu.mentorme.mentorMeActivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Super controller for MentorMe.
 */
public class MentorMeController implements GoogleApiClient.OnConnectionFailedListener{

    private final String TAG = "MentorMeController";
    private GoogleApiSignInModel googleApiSignInModel;
    private GoogleApiClient mGoogleApiClient;

    public MentorMeController() {
        googleApiSignInModel = GoogleApiSignInModel.getInstance();
    }

    /**
     * Given an activity, returns GoogleApiClient from GoogleApiSignInModel
     *
     * @param activity class activity
     * @return GoogleApiClient
     */
    public GoogleApiClient getGoogleApiClient(AppCompatActivity activity) {

        mGoogleApiClient =  googleApiSignInModel.getGoogleApiClient(activity, this);
        return mGoogleApiClient;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }
}
