package mentorme.csumb.edu.mentorme.data.model.mentorInfoTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mentorme.csumb.edu.mentorme.data.model.mentorInfo.Mentor;
import mentorme.csumb.edu.mentorme.data.model.mentorInfo.MentorInfo;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static org.mockito.Mockito.when;

/**
 * Created by benitosanchez on 12/4/16.
 */

public class MentorInfoTest {

    @Mock MentorInfo mMockMentorInfo;

    private Mentor mMentor;

    @Before
    public void setUp() {
        mMentor = new Mentor();

        MockitoAnnotations.initMocks(this);

        when(mMockMentorInfo.getMentor()).thenReturn(mMentor);
    }

    @Test
    public void getMentor_shouldReturnExpectedMentor() {
        assertEquals(mMockMentorInfo.getMentor(), mMentor);
    }

    @Test
    public void whenSetNewMentor_shouldNotBeEqual() {
        Mentor newMentor = new Mentor();

        assertNotSame(newMentor, mMockMentorInfo.getMentor());
    }
}
