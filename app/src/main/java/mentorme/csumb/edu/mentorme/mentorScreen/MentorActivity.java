package mentorme.csumb.edu.mentorme.mentorScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.homeScreen.HomeActivity;
import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;

/**
 * Activity class for the Mentors screen.
 */
public class MentorActivity  extends MentorMeActivity {

    private MentorController mMentorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMentorController = new MentorController(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topic_mentor_toolbar, menu);

        return true;
    }

    /*
     * Options for Menu buttons on the Mentor Screen
     */
    public boolean onOptionsItemSelected(MenuItem item)  {

        switch (item.getItemId()) {
            case R.id.go_home:
            {
                Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            }
            default:
                return false;
        }
    }

}
