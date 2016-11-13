package mentorme.csumb.edu.mentorme.data.model.subjects; ;

import java.util.ArrayList;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import mentorme.csumb.edu.mentorme.data.model.subjects.Subject;

@Generated("org.jsonschema2pojo")
public class Subjects {

    @SerializedName("subjects")
    @Expose
    private ArrayList<Subject> subjects = new ArrayList<Subject>();

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