package mentorme.csumb.edu.mentorme.homeScreen;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mentorme.csumb.edu.mentorme.MentorMeApi.MentorMeApi;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.Item;
import mentorme.csumb.edu.mentorme.data.model.Subjects;
import mentorme.csumb.edu.mentorme.webRequest.WebRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends ListActivity {

    private static final String TAG = "HomeActivity";

    private static final String MESSAGE = "message";
    private static final String KIND = "kind";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MentorMeApi.Factory.getInstance().getSubjects().enqueue(new Callback<Subjects>() {
            @Override
            public void onResponse(Call<Subjects> call, Response<Subjects> response) {
                Log.d(TAG, response.body().getItems().get(0).getMessage());
                Log.d(TAG, response.body().getKind());

                ArrayList<HashMap<String, String>> list = parseResponse(response.body().getItems());

                ListAdapter adapter = new SimpleAdapter(
                    HomeActivity.this, list,
                    R.layout.subjects_list, new String[] {MESSAGE, KIND},
                    new int[]{R.id.message, R.id.kind});

                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Subjects> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private ArrayList<HashMap<String, String>> parseResponse(List<Item> json) {
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


