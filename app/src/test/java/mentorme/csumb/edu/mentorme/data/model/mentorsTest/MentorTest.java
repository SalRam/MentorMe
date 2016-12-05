package mentorme.csumb.edu.mentorme.data.model.mentorsTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mentorme.csumb.edu.mentorme.data.model.mentors.Mentor;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Test functionality of {@link Mentor}
 */
public class MentorTest {

    @Mock Mentor mMockMentor;
    Mentor mentor;

    private final static String ID = "ID";
    private final static String NAME = "Bob";

    @Before
    public void setUp() {
        mentor = new Mentor();
        MockitoAnnotations.initMocks(this);

        when(mMockMentor.getId()).thenReturn(ID);
        when(mMockMentor.getName()).thenReturn(NAME);
    }

    @Test
    public void getId_shouldReturnExpectedId() {
        String id = mMockMentor.getId();

        assertEquals(id, ID);
    }

    @Test
    public void getName_shouldReturnExpectedName() {
        String name = mMockMentor.getName();

        assertEquals(name, NAME);
    }

    @Test
    public void setId_shouldChangeId() {
        mentor.setId("TEST");

        String id = mentor.getId();

        assertEquals(id, mentor.getId());

    }

    @Test
    public void setName_shouldChangeName() {
        mentor.setName("bob");

        String name = mentor.getName();

        assertEquals(name, mentor.getName());
    }
}
