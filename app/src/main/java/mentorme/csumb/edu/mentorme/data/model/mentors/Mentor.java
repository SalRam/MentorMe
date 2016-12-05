package mentorme.csumb.edu.mentorme.data.model.mentors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Pojo object to hold Mentor values.
 */
public class Mentor {

    @SerializedName("id") @Expose private String id;
    @SerializedName("name") @Expose private String name;

    /**
     *
     *@returns mentor's id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * Sets the mentor's id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

}
