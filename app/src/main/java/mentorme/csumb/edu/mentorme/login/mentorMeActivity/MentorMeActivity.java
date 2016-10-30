package mentorme.csumb.edu.mentorme.login.mentorMeActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * MentorMeActivity will be a super activity that initializes default set up for each activity.
 * All activity should extend MentorMeActivity
 */
public class MentorMeActivity extends AppCompatActivity {

    private GoogleApiClient mGoogleApiClient;
    private MentorMeController mentorMeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mentorMeController = new MentorMeController();
        mGoogleApiClient = mentorMeController.getGoogleApiClient(this);
    }

    /**
     * Returns mGoogleApiClient.
     *
     * @return {@link GoogleApiClient}
     */
    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }
}
