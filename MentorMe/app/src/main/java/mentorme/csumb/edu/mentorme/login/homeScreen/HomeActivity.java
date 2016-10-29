package mentorme.csumb.edu.mentorme.login.homeScreen;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.login.webRequest.WebRequest;

public class HomeActivity extends ListActivity {

    private static String url = "https://mentorme-api.appspot.com/_ah/api/helloworld/v1/hellogreeting";

    private static final String ITEMS = "items";
    private static final String MESSAGE = "message";
    private static final String KIND = "kind";

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new GetSubjects().execute();

    }

    private class GetSubjects extends AsyncTask<Void, Void, Void> {

        ArrayList<HashMap<String, String>> listOfSubjects;

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progressDialog = new ProgressDialog(HomeActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            WebRequest webRequest = new WebRequest();

            String jsonString = webRequest.makeWebServiceCall(url, WebRequest.GET);
            Log.d("Response: ", " > " + jsonString);

            listOfSubjects = ParseJSON(jsonString);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            ListAdapter adapter = new SimpleAdapter(
                    HomeActivity.this, listOfSubjects,
                    R.layout.subjects_list, new String[] {MESSAGE, KIND},
                    new int[]{R.id.message, R.id.kind});

            setListAdapter(adapter);
        }

        private ArrayList<HashMap<String, String>> ParseJSON(String json) {
            if (json != null) {
                try {
                    ArrayList<HashMap<String, String>> studentList = new ArrayList<>();

                    JSONObject jsonObject = new JSONObject(json);

                    JSONArray students = jsonObject.getJSONArray(ITEMS);

                    for (int i = 0; i < students.length(); i++) {
                        JSONObject c = students.getJSONObject(i);

                        String message = c.getString(MESSAGE);
                        String kind = c.getString(KIND);

                        HashMap<String, String> testSubjects = new HashMap<>();

                        testSubjects.put(MESSAGE, message);
                        testSubjects.put(KIND, kind);

                        studentList.add(testSubjects);
                    }
                    return studentList;
                } catch (JSONException e) {
                    e.printStackTrace();;
                    return null;
                }
            } else {
                Log.e("Service Handler", "Couldn't get any data from url");
                return null;
            }
        }


    }

}


