package mentorme.csumb.edu.mentorme.homeScreen;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mentorme.csumb.edu.mentorme.data.model.Item;

/**
 * Model for the {@link HomeActivity}.
 */

public class HomeModel {

    static final String MESSAGE = "message";
    public static final String KIND = "kind";

    HomeModel() { }

    ArrayList<HashMap<String, String>> parseResponse(List<Item> json) {
        if (json != null) {
            ArrayList<HashMap<String, String>> studentList = new ArrayList<>();


            for (int i = 0; i < json.size(); i++) {

                String message = json.get(i).getMessage();
                String kind = json.get(i).getKind();

                HashMap<String, String> testSubjects = new HashMap<>();

                testSubjects.put(MESSAGE, message);
                testSubjects.put(KIND, kind);

                studentList.add(testSubjects);
            }
            return studentList;
        } else {
            Log.e("Service Handler", "Couldn't get any data from url");
            return null;
        }
    }

}
