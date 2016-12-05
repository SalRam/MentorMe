
package mentorme.csumb.edu.mentorme.data.model.mentorInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Pojo object to hold Mentor values.
 */
public class Mentor {

    @SerializedName("id") @Expose private String id;
    @SerializedName("name") @Expose private String name;
    @SerializedName("phone_number") @Expose private String phoneNumber;
    @SerializedName("email") @Expose private String email;
    @SerializedName("description") @Expose private String description;
    @SerializedName("office_hours") @Expose private ArrayList<OfficeHour> officeHours = new ArrayList<OfficeHour>();

    /**
     * Returns the mentor's id.
     *
     * @return The mentors id.
     */
    public String getId() {
        return id;
    }

    /**
     * Set's the mentors id.
     *
     * @param id The mentor's id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the mentor's name.
     *
     * @return The mentor's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set's the mentor's name.
     *
     * @param name The mentor's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the mentor's phone number.
     *
     * @return The mentor's phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set's the mentor's phone number.
     *
     * @param phoneNumber The mentor's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the mentor's email address.
     *
     * @return The mentor's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set's the mentor's email address.
     *
     * @param email The mentor's email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the mentor's description.
     *
     * @return The mentor's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set's mentor's description.
     *
     * @param description The mentor's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return's the mentor's office hours.
     *
     * @return The mentor's officeHours
     */
    public ArrayList<OfficeHour> getOfficeHours() {
        return officeHours;
    }

    /**
     * Set's the mentor's office hours.
     *
     * @param officeHours The mentor's office_hours
     */
    public void setOfficeHours(ArrayList<OfficeHour> officeHours) {
        this.officeHours = officeHours;
    }

}
