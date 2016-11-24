package mentorme.csumb.edu.mentorme.homeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.login.LoginActivity;
import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;
import mentorme.csumb.edu.mentorme.user.local.UserLocalStorage;

/**
 * Activity class for the HomeActivity.
 */
public  class HomeActivity extends MentorMeActivity {

    private UserLocalStorage userLocalStorage;

    private static final String TAG = "HomeActivity";

    private HomeController mHomeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHomeController = new HomeController(this);
        userLocalStorage = new UserLocalStorage(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {

        return mHomeController.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate()) {
            Log.d(TAG, "Logged in Successfully");
        }
    }

    private boolean authenticate() {
        if (userLocalStorage.getLoggedInUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    /*
     * Returns the information of the user currently signed in
     */
    public UserLocalStorage getUserLocalStorage(){

        return userLocalStorage;
    }
}
