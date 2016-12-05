package mentorme.csumb.edu.mentorme.addingMentor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.homeScreen.HomeActivity;
import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;

/**
 *  Adding Mentor Activity
 */

public class AddingMentorActivity extends MentorMeActivity {

    private AddingMentorController mAddingMentorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAddingMentorController = new AddingMentorController(this);
    }

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
