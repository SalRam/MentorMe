package mentorme.csumb.edu.mentorme.HomeScreenIT;

import android.content.ClipData;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.homeScreen.HomeActivity;
import mentorme.csumb.edu.mentorme.login.LoginActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Donkey on 11/7/16.
 */


@RunWith(AndroidJUnit4.class)
public class viewLisIT {

    @Rule
    public ActivityTestRule<HomeActivity> mLoginActivity = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void onLoginActivityStart_whenAccountIsConnected_shouldLaunchSubjectsScreen() {
        // Type text and then press the button.
        onView(withText("CST"))
                .check(matches(isDisplayed()));
    }

}
