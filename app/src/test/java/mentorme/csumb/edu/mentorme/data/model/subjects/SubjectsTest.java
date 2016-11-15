package mentorme.csumb.edu.mentorme.data.model.subjects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static net.bytebuddy.matcher.ElementMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertArrayEquals;

/**
 * Unit tests for {@link Subjects} functionality.
 */
@RunWith(JUnit4.class)
public class SubjectsTest {

    private final String FIRST_SUBJECT = "Algorythms";
    private final String SECOND_SUBJECT = "Math";
    private final String THIRD_SUBJECT = "EnglisH";

    private Subject mSubject;
    private Subjects mSubjects;
    private ArrayList<Subject> mListOfSubjects;

    @Before
    public void setUp() {
        mSubjects = new Subjects();
        mListOfSubjects = new ArrayList<>();
    }

    @Test
    public void getSubjects_whenSubjectsMatch_shouldPass() {
        // Given
        mSubject = new Subject();
        mSubject.setSubject(FIRST_SUBJECT);
        mListOfSubjects.add(mSubject);

        mSubject = new Subject();
        mSubject.setSubject(SECOND_SUBJECT);
        mListOfSubjects.add(mSubject);

        mSubjects.setSubjects(mListOfSubjects);

        // When
        ArrayList<Subject> list = mSubjects.getSubjects();

        // Then
        assertEquals(list, mListOfSubjects);
    }

    @Test
    public void getSubjects_whenSubjectsDoNotMatch_shouldFail() {
        // Given
        mSubject = new Subject();
        mSubject.setSubject(FIRST_SUBJECT);
        mListOfSubjects.add(mSubject);

        mSubject = new Subject();
        mSubject.setSubject(THIRD_SUBJECT);
        mListOfSubjects.add(mSubject);

        mSubjects.setSubjects(mListOfSubjects);

        mSubject = new Subject();
        mSubject.setSubject(SECOND_SUBJECT);

        // When
        ArrayList<Subject> list = new ArrayList<>();
        list.add(mSubject);

        // Then
        assertFalse(list.equals(mListOfSubjects));
    }
}
