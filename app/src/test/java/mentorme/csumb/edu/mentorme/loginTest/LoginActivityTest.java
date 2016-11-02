package mentorme.csumb.edu.mentorme.loginTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import mentorme.csumb.edu.mentorme.login.LoginActivity;

import static junit.framework.Assert.assertNotNull;

/**
 * Unit tests to test {@link LoginActivity} functionality.
 */
@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    private LoginActivity mLoginActivity;

    @Before
    public void setup() {
        mLoginActivity = Robolectric.buildActivity(LoginActivity.class).get();
    }

    @Test
    public void onCreate_shouldInitializeLoginActivity() {
        assertNotNull(mLoginActivity);
    }
}
