package mentorme.csumb.edu.mentorme.data.model.topics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Unit test for {@link Topics} functionality.
 */
public class TopicsTest {

    private final String FIRST_TOPIC = "CST";
    private final String FIRST_TOPIC_TITLE = "Algorithms";
    private final String SECOND_TOPIC = "BIO";
    private final String SECOND_TOPIC_TITLE = "LIFE";

    private Topic mTopic;
    private Topics mTopics;
    private ArrayList<Topic> mListOfTopics;

    @Before
    public void setUp() {
        mTopics = new Topics();
        mListOfTopics = new ArrayList<>();
    }

    @Test
    public void getTopics_whenTopicsMatch_shouldPass() {
        // Given
        mTopic = new Topic();
        mTopic.setTopic(FIRST_TOPIC);
        mTopic.setTitle(FIRST_TOPIC_TITLE);
        mListOfTopics.add(mTopic);

        mTopic = new Topic();
        mTopic.setTopic(SECOND_TOPIC);
        mTopic.setTitle(SECOND_TOPIC_TITLE);
        mListOfTopics.add(mTopic);

        mTopics.setTopics(mListOfTopics);

        // When
        ArrayList<Topic> list = mTopics.getTopics();

        // Then
        assertEquals(list, mListOfTopics);
    }

    @Test
    public void getTopics_whenTopicsDoNotMatch_shouldFail() {
        // Given
        mTopic = new Topic();
        mTopic.setTopic(FIRST_TOPIC);
        mTopic.setTitle(FIRST_TOPIC_TITLE);

        mListOfTopics.add(mTopic);

        mTopic = new Topic();
        mTopic.setTopic(SECOND_TOPIC);
        mTopic.setTitle(SECOND_TOPIC_TITLE);

        ArrayList<Topic> list = new ArrayList<>();
        list.add(mTopic);

        assertFalse(list.equals(mListOfTopics));
    }
}
