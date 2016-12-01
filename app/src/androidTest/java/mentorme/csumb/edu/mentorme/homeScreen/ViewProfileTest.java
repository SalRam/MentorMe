package mentorme.csumb.edu.mentorme.homeScreen;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mentorme.csumb.edu.mentorme.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ViewProfileTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void viewProfileTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login_button), withText("Login"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open"),
                        withParent(withId(R.id.toolbar_layout)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Profile"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.toolbar_back_arrow),
                        withParent(withId(R.id.toolbar_no_menu)),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open"),
                        withParent(withId(R.id.toolbar_layout)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Sign out"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

    }

}
