package mentorme.csumb.edu.mentorme.homeScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mentorme.csumb.edu.mentorme.data.model.Item;

/**
 * Model for the {@link HomeActivity}.
 */

class HomeModel {

    static final String MESSAGE = "message";
    public static final String KIND = "kind";

    HomeModel() { }

    /**
     * Returns a list of Hashed subject values
     *
     * @param json The json object to be parsed
     *
     * @return The list of hashed values.
     */
    ArrayList<HashMap<String, String>> parseResponse(List<Item> json) {

            ArrayList<HashMap<String, String>> studentList = new ArrayList<>();

            for (int i = 0; i < json.size(); i++) {

                HashMap<String, String> testSubjects = new HashMap<>();

                testSubjects.put(MESSAGE, json.get(i).getMessage());
                testSubjects.put(KIND, json.get(i).getKind());

                studentList.add(testSubjects);
            }
            return studentList;
    }
}
