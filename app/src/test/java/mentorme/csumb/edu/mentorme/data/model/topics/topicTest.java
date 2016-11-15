package mentorme.csumb.edu.mentorme.data.model.topics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Unit test coverage for {@link Topic}.
 */
@RunWith(JUnit4.class)
public class TopicTest {
    private final String MATCH_TOPIC = "CST";
    private final String MATCH_TITLE = "Algorithms";
    private final String NO_MATCH_TOPIC = "ENG";
    private final String NO_MATCH_TITLE = "English 1";

    private Topic mTopic;

    @Before
    public void onSetup() {
        mTopic = new Topic();
    }

    @Test
    public void getTopic_whenTopicMatch_shouldPass() {
        // Given
        mTopic.setTopic(MATCH_TOPIC);

        // When
        assertEquals(mTopic.getTopic(), MATCH_TOPIC);
    }

    @Test
    public void getTopic_whenTopicDoNotMatch_shouldFail() {
        mTopic.setTopic(MATCH_TOPIC);

        assertFalse(mTopic.getTopic().equals(NO_MATCH_TOPIC));
    }

    @Test
    public void getTitle_whenTitleMatch_shouldPass() {
        // Given
        mTopic.setTitle(MATCH_TITLE);

        // Then
        assertEquals(mTopic.getTitle(), MATCH_TITLE);
    }

    @Test
    public void getTitle_whenTitleDoNotMatch_shouldFail() {
        // Given
        mTopic.setTitle(MATCH_TITLE);

        // Then
        assertFalse(mTopic.getTitle().equals(NO_MATCH_TITLE));
    }
}
