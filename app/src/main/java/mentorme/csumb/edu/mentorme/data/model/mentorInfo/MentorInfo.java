
package mentorme.csumb.edu.mentorme.data.model.mentorInfo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class MentorInfo {

    @SerializedName("mentor")
    @Expose
    private Mentor mentor;

    /**
     * 
     * @return
     *     The mentor
     */
    public Mentor getMentor() {
        return mentor;
    }

    /**
     * 
     * @param mentor
     *     The mentor
     */
    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

}
