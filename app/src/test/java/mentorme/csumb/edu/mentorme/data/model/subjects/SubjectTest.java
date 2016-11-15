package mentorme.csumb.edu.mentorme.data.model.subjects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Tests for {@link Subject}
 */
@RunWith(JUnit4.class)
public class SubjectTest {

    private final String MATCH_SUBJECT = "Database";
    private final String NO_MATCH_SUBJECT = "Fallas";

    private Subject subject;

    @Before
    public void setUp() {
        subject = new Subject();
    }

    @Test
    public void getSubject_whenSubjectsMatch_shouldPass() {
        subject.setSubject(MATCH_SUBJECT);
        assertEquals(subject.getSubject(), MATCH_SUBJECT);
    }

    @Test
    public void getSubject_whenSubjectsDoNotMatch_shouldFail() {
        subject.setSubject(MATCH_SUBJECT);
        assertFalse(subject.getSubject().equals(NO_MATCH_SUBJECT));
    }
}
