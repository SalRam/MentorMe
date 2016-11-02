package mentorme.csumb.edu.mentorme.mentorMeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by benitosanchez on 11/1/16.
 */
@RunWith(RobolectricTestRunner.class)
public class MentorMeActivityTest {

    private MentorMeActivity mMentorMeActivity;

    @Before
    public void setup() {
        mMentorMeActivity = Robolectric.buildActivity(MentorMeActivity.class).get();
    }

    @Test
    public void onCreate_shouldInitializeMentorMeActivity() {
        assertNotNull(mMentorMeActivity);
    }
}
