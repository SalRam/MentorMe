package mentorme.csumb.edu.mentorme.data.model; ;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Subjects {

    @SerializedName("subjects")
    @Expose
    private List<Subject> subjects = new ArrayList<Subject>();

    /**
     *
     * @return
     *     The subjects
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     *
     * @param subjects
     *     The subjects
     */
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}