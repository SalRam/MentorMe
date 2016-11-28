
package mentorme.csumb.edu.mentorme.data.model.mentorInfo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class OfficeHour {

    @SerializedName("day") @Expose private String day;
    @SerializedName("hours") @Expose private String hours;

    /**
     * Returns the day that the mentor is available.
     *
     * @return The dat the mentor is available.
     */
    public String getDay() {
        return day;
    }

    /**
     * Set's the day the mentor is available.
     *
     * @param day The day the mentor is available.
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * Returns the hours that the mentor is available.
     *
     * @return The hours that the mentor is available.
     */
    public String getHours() {
        return hours;
    }

    /**
     * Sets the hours that the mentor is available.
     *
     * @param hours The hours that the mentor is available.
     */
    public void setHours(String hours) {
        this.hours = hours;
    }

}
