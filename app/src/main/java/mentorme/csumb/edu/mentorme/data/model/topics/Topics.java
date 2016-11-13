
package mentorme.csumb.edu.mentorme.data.model.topics;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Topics {

    @SerializedName("topics")
    @Expose
    private ArrayList<Topic> topics = new ArrayList<Topic>();

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
