package mentorme.csumb.edu.mentorme.user.local;

/**
 * Created by benitosanchez on 11/16/16.
 */

public class User {
    private String mDisplayName, mFamilyName, mGivenName, mEmail;

    public User(String displayName, String familyName, String givenName, String email) {
        mDisplayName = displayName;
        mFamilyName = familyName;
        mGivenName = givenName;
        mEmail = email;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public String getFamilyName() {
        return mFamilyName;
    }

    public String getGivenname() {
        return mGivenName;
    }

    public String getEmail() {
        return mEmail;
    }

}
