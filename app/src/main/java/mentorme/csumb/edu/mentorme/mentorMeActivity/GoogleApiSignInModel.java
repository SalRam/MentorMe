package mentorme.csumb.edu.mentorme.mentorMeActivity;

import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Super model for initializing google account sign in.
 */
public class GoogleApiSignInModel {

    private static GoogleApiSignInModel instance = null;

    private GoogleSignInOptions gso;

    private GoogleApiSignInModel() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

    /**
     * If {@link GoogleApiSignInModel} is null, initialize GoogleApiSignModel.
     * Else return GoogleApiSignModel.
     *
     * @return the GoogleAPiSignModel.
     */
    public static GoogleApiSignInModel getInstance() {
        if (instance == null) {
            instance = new GoogleApiSignInModel();
        }
        return instance;
    }

    /**
     * Returns {@link GoogleApiClient}
     *
     * @param activity the current activity
     * @param listener {@link MentorMeController}
     *
     * @return returns {@link GoogleApiClient}
     */
    public GoogleApiClient getGoogleApiClient(
            AppCompatActivity activity,
            GoogleApiClient.OnConnectionFailedListener listener) {
        return new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, listener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }
}
