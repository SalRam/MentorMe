package mentorme.csumb.edu.mentorme.data.model.mentorInfoTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mentorme.csumb.edu.mentorme.data.model.mentorInfo.OfficeHour;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Testing functionality for {@link OfficeHour}
 */
public class OfficeHourTest {

    private final static String DAY = "Monday";
    private final static String HOURS = "10 - 9";

    @Mock OfficeHour mMockOfficeHour;
    private OfficeHour mOfficeHour;

    @Before
    public void setUp() {
        mOfficeHour = new OfficeHour();
        MockitoAnnotations.initMocks(this);

        when(mMockOfficeHour.getDay()).thenReturn(DAY);
        when(mMockOfficeHour.getHours()).thenReturn(HOURS);
    }

    @Test
    public void getDay_shouldReturnExpectedValue() {
        // Given
        String day = mMockOfficeHour.getDay();

        // Then
        assertEquals(day, DAY);
    }

    @Test
    public void getHours_shouldReturnExpectedHours() {
        // Given
        String hours = mMockOfficeHour.getHours();

        // Then
        assertEquals(hours, HOURS);
    }

    @Test
    public void setHours_shouldChangeHours() {
        // Given
        mOfficeHour.setHours(HOURS);

        // Then
        assertEquals(HOURS, mOfficeHour.getHours());
    }

    @Test
    public void setDay_shouldChangeDay() {
        // Given
        mOfficeHour.setDay(DAY);

        // Then
        assertEquals(DAY, mOfficeHour.getDay());
    }
}
