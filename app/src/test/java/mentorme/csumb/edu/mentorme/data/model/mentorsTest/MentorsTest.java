package mentorme.csumb.edu.mentorme.data.model.mentorsTest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import mentorme.csumb.edu.mentorme.data.model.mentors.Mentor;
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentors;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Test functionality of {@link Mentor}
 */
public class MentorsTest {

    private Mentors mMentors;

    @Before
    public void setUp() {
        mMentors = new Mentors();
    }

    @Test
    public void getMentors_whenEmptyList_shouldReturnEmptyList() {
        ArrayList<Mentor> list = mMentors.getMentors();

        assertTrue(list.isEmpty());
    }

    @Test
    public void setMentor_shouldIncreaseSizeOfList() {
        ArrayList<Mentor> list = mMentors.getMentors();
        int size = list.size();

        ArrayList<Mentor> addList = new ArrayList<>();
        addList.add(new Mentor());

        mMentors.setMentors(addList);
        assertEquals(mMentors.getMentors().size(), addList.size());
    }
}
