package mentorme.csumb.edu.mentorme.data.model.mentorInfoTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import mentorme.csumb.edu.mentorme.data.model.mentorInfo.Mentor;
import mentorme.csumb.edu.mentorme.data.model.mentorInfo.OfficeHour;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test functionlity of {@link Mentor}
 */
public class MentorTest {

    private final static String MENTOR_NAME = "Benito";
    private final static String MENTOR_ID = "ID";
    private final static String MENTOR_PHONE_NUMBER = "7777";
    private final static String MENTOR_EMAIL = "benfallas@test.com";
    private final static String MENTOR_DESCRIPTION = "Hello";
    private final static ArrayList<OfficeHour> MENTOR_OFFICE_HOUR = new ArrayList<>();

    @Mock Mentor mMockMentor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mMockMentor.getId()).thenReturn(MENTOR_ID);
        when(mMockMentor.getName()).thenReturn(MENTOR_NAME);
        when(mMockMentor.getPhoneNumber()).thenReturn(MENTOR_PHONE_NUMBER);
        when(mMockMentor.getEmail()).thenReturn(MENTOR_EMAIL);
        when(mMockMentor.getDescription()).thenReturn(MENTOR_DESCRIPTION);
        when(mMockMentor.getOfficeHours()).thenReturn(MENTOR_OFFICE_HOUR);
    }

    @Test
    public void getId_shouldReturnExpectedId() {
        String Id = mMockMentor.getId();

        assertEquals(Id, MENTOR_ID);
    }

    @Test
    public void getName_shouldReturnExpectedName() {
        String name = mMockMentor.getName();

        assertEquals(name, MENTOR_NAME);
    }

    @Test
    public void getPhoneNumber_shouldReturnExpectedName() {
        String phoneNumber = mMockMentor.getPhoneNumber();

        assertEquals(phoneNumber, MENTOR_PHONE_NUMBER);
    }

    @Test
    public void getEmail_shouldReturnExpectedEmail() {
        String email = mMockMentor.getEmail();

        assertEquals(email, MENTOR_EMAIL);
    }

    @Test
    public void getDescription_shouldReturnDescription() {
        String description = mMockMentor.getDescription();

        assertEquals(description, MENTOR_DESCRIPTION);
    }

    @Test
    public void getOfficeHours_shouldReturnExpectedOfficeHours() {
        ArrayList<OfficeHour> officeHours = mMockMentor.getOfficeHours();

        assertEquals(officeHours, MENTOR_OFFICE_HOUR);
    }

}
