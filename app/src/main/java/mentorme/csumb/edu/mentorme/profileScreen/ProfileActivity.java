package mentorme.csumb.edu.mentorme.profileScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ProfileController mProfileController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProfileController = new ProfileController(this);
    }

}
