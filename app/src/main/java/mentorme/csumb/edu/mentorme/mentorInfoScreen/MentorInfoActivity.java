package mentorme.csumb.edu.mentorme.mentorInfoScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.homeScreen.HomeActivity;

/**
 * Activity for the Mentor's information screen.
 */
public class MentorInfoActivity extends AppCompatActivity {

    private MentorInfoController mMentorInfoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMentorInfoController = new MentorInfoController(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topic_mentor_toolbar, menu);

        return true;
    }

    /*
     * Options for Menu buttons on the MentorInfo Screen
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
