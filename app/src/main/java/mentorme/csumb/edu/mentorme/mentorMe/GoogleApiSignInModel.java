package mentorme.csumb.edu.mentorme.mentorMe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import mentorme.csumb.edu.mentorme.R;

import static android.provider.Settings.Global.getString;

/**
 * Super model for initializing google account sign in.
 */
public class GoogleApiSignInModel {

    private static GoogleApiSignInModel instance = null;

    private GoogleSignInOptions gso;

    private GoogleApiSignInModel(Context context) {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.server_client_id))
                .requestEmail()
                .build();
    }

    /**
     * If {@link GoogleApiSignInModel} is null, initialize GoogleApiSignModel.
     * Else return GoogleApiSignModel.
     *
     * @return the GoogleAPiSignModel.
     */
    public static GoogleApiSignInModel getInstance(Context context) {
        if (instance == null) {
            instance = new GoogleApiSignInModel(context);
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
