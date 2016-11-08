package mentorme.csumb.edu.mentorme.loginIntegrationTest;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mentorme.csumb.edu.mentorme.login.LoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Integration test for the Login Screen.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginIntegrationTest {

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivity = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void onLoginActivityStart_whenAccountIsConnected_shouldLaunchSubjectsScreen() {
        // Type text and then press the button.
        onView(withText("Subjects"))
                .check(matches(isDisplayed()));
    }


}
