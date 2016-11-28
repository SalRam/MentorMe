
package mentorme.csumb.edu.mentorme.data.model.mentorInfo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class OfficeHour {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("hours")
    @Expose
    private String hours;

    /**
     * 
     * @return
     *     The day
     */
    public String getDay() {
        return day;
    }

    /**
     * 
     * @param day
     *     The day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * 
     * @return
     *     The hours
     */
    public String getHours() {
        return hours;
    }

    /**
     * 
     * @param hours
     *     The hours
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

}
