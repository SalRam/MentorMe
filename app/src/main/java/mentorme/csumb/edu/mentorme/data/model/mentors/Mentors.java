package mentorme.csumb.edu.mentorme.data.model.mentors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Pojo object to hold Values of Mentors.
 */
public class Mentors {

    @SerializedName("mentors") @Expose private ArrayList<Mentor> mentors = new ArrayList<Mentor>();

    /**
     *
     * @return
     * The mentors
     */
    public ArrayList<Mentor> getMentors() {
        return mentors;
    }

    /**
     *
     * @param mentors
     * The mentors
     */
    public void setMentors(ArrayList<Mentor> mentors) {
        this.mentors = mentors;
    }

}
