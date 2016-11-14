package mentorme.csumb.edu.mentorme.homeScreen;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;

/**
 * Activity class for the HomeActivity.
 */
public  class HomeActivity extends MentorMeActivity {

    private static final String TAG = "HomeActivity";

    private HomeController mHomeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHomeController = new HomeController(this);
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

}
