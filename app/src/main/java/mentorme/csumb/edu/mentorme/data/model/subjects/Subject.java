package mentorme.csumb.edu.mentorme.data.model.subjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Pojo object to hold Subject values.
 */
public class Subject {

    @SerializedName("subject") @Expose private String subject;
    @SerializedName("id") @Expose private String id;

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

    /**
     * Returns subject id.
     *
     * @return The subject id.
     */
    public String getId() {
        return id;
    }

    /**
     * Set's subject id.
     *
     * @param id the new subject id.
     */
    public void setId(String id) {
        this.id = id;
    }

}
