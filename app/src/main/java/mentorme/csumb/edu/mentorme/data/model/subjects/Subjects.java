package mentorme.csumb.edu.mentorme.data.model.subjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Pojo object to hold Subjects values.
 */
public class Subjects {

    @SerializedName("subjects") @Expose private ArrayList<Subject> subjects = new ArrayList<Subject>();

    /**
     *
     * @return
     *     The subjects
     */
    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    /**
     *
     * @param subjects
     *     The subjects
     */
    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

}