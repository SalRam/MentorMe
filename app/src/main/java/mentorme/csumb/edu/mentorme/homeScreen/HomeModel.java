package mentorme.csumb.edu.mentorme.homeScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mentorme.csumb.edu.mentorme.data.model.Subject;

/**
 * Model for the {@link HomeActivity}.
 */

class HomeModel {

    static final String SUBJECT = "subject";

    HomeModel() { }

    /**
     * Returns a list of Hashed subject values
     *
     * @param json The json object to be parsed
     *
     * @return The list of hashed values.
     */
    ArrayList<HashMap<String, String>> parseResponse(List<Subject> json) {

            ArrayList<HashMap<String, String>> listOfSubjects = new ArrayList<>();

            for (int i = 0; i < json.size(); i++) {

                HashMap<String, String> subject = new HashMap<>();

                subject.put(SUBJECT, json.get(i).getSubject());

                listOfSubjects.add(subject);
            }
            return listOfSubjects;
    }
}
