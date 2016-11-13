package mentorme.csumb.edu.mentorme.data.model.subjects;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Subject {

    @SerializedName("subject")
    @Expose
    private String subject;

    /**
     *
     * @return
     *     The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     *     The subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

}
