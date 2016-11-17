package mentorme.csumb.edu.mentorme.user.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by benitosanchez on 11/16/16.
 */

public class UserLocalStorage {

    private static final String STUDENT_ACCOUNT = "STUDENT_ACCOUNT";

    private static final String STUDENT_DISPLAY_NAME = "STUDENT_DISPLAY_NAME";
    private static final String STUDENT_FAMILY_NAME = "STUDENT_FAMILY_NAME";
    private static final String STUDENT_GIVEN_NAME = "STUDENT_GIVEN_NAME";
    private static final String STUDENT_EMAIL = "EMAIL";
    private static final String LOGGED_IN = "loggedIn";

    SharedPreferences userLocalDatabase;

    public UserLocalStorage(Context context) {
        userLocalDatabase = context.getSharedPreferences(STUDENT_ACCOUNT, context.MODE_PRIVATE);
    }

    public void storeUserData(GoogleSignInAccount user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString(STUDENT_DISPLAY_NAME, user.getDisplayName());
        userLocalDatabaseEditor.putString(STUDENT_FAMILY_NAME, user.getFamilyName());
        userLocalDatabaseEditor.putString(STUDENT_GIVEN_NAME, user.getGivenName());
        userLocalDatabaseEditor.putString(STUDENT_EMAIL, user.getEmail());
        userLocalDatabaseEditor.commit();
    }

    public void setStudentLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean(LOGGED_IN, loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (!userLocalDatabase.getBoolean(LOGGED_IN, false)) {
            return null;
        }

        String displayName = userLocalDatabase.getString(STUDENT_DISPLAY_NAME, "");
        String familyName = userLocalDatabase.getString(STUDENT_FAMILY_NAME, "");
        String givenName = userLocalDatabase.getString(STUDENT_GIVEN_NAME, "");
        String email = userLocalDatabase.getString(STUDENT_EMAIL, "");

        User user = new User(displayName, familyName, givenName, email);

        return user;
    }
}
