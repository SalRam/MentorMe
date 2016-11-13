package mentorme.csumb.edu.mentorme.mentorMe;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.R;

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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
