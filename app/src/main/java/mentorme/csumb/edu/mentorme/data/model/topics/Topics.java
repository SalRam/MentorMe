
package mentorme.csumb.edu.mentorme.data.model.topics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Pojo object that holds Topics. 
 */
public class Topics {

    @SerializedName("topics") @Expose private ArrayList<Topic> topics = new ArrayList<Topic>();

    /**
     * 
     * @return
     *     The topics
     */
    public ArrayList<Topic> getTopics() {
        return topics;
    }

    /**
     * 
     * @param topics
     *     The topics
     */
    public void setTopics(ArrayList<Topic> topics) {
        this.topics = topics;
    }

}
