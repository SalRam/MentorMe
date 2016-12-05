
package mentorme.csumb.edu.mentorme.data.model.topics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Pojo object that holds Topic values.
 */
public class Topic {

    @SerializedName("topic") @Expose private String topic;
    @SerializedName("title") @Expose private String title;
    @SerializedName("id") @Expose private String id;

    /**
     * 
     * @return
     *     The topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 
     * @param topic
     *     The topic
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
