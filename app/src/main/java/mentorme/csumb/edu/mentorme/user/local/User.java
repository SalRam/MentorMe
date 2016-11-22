package mentorme.csumb.edu.mentorme.user.local;

/**
 *
 */
public class User {

    private String mDisplayName,
                    mFamilyName,
                    mGivenName,
                    mEmail;

    /**
     * Initializes a Student with given values.
     *
     * @param displayName The Students display name.
     * @param familyName The Student's family name.
     * @param givenName The Student's given name.
     * @param email The Student's email.
     */
    public User(String displayName, String familyName, String givenName, String email) {
        mDisplayName = displayName;
        mFamilyName = familyName;
        mGivenName = givenName;
        mEmail = email;
    }

    /**
     * @return the Student's display name.
     */
    public String getDisplayName() {
        return mDisplayName;
    }

    /**
     * @return The Student's Family Name (Last name).
     */
    public String getFamilyName() {
        return mFamilyName;
    }

    /**
     * @return The Student's given name.
     */
    public String getGivenName() {
        return mGivenName;
    }

    /**
     * @return The Student's email address.
     */
    public String getEmail() {
        return mEmail;
    }
}
