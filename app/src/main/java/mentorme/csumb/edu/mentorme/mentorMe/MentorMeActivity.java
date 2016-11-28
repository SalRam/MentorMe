package mentorme.csumb.edu.mentorme.mentorMe;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import mentorme.csumb.edu.mentorme.login.googleSignInModel.GoogleApiSignInModel;

/**
 * MentorMeActivity will be a super activity that initializes default set up for each activity.
 * All activity should extend MentorMeActivity
 */
public class MentorMeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private MentorMeController mentorMeController;
    private GoogleApiClient mGoogleApiClient;

    private GoogleApiSignInModel mGoogleApiSignInModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mGoogleApiSignInModel = GoogleApiSignInModel.getInstance(this);
        mGoogleApiClient = mGoogleApiSignInModel.getGoogleApiClient(this, this);
        mentorMeController = new MentorMeController(getApplicationContext());
    }

    /*
     * Returns the GoogleApi Client created in the MentorMe activity
     */
    public GoogleApiClient getGoogleApiClient(){

        return mGoogleApiClient;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { }
}
