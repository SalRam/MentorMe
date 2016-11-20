package mentorme.csumb.edu.mentorme.mentorScreen;

import android.os.Bundle;

import mentorme.csumb.edu.mentorme.mentorMe.MentorMeActivity;

/**
 * Created by Donkey on 11/16/16.
 */

public class MentorActivity  extends MentorMeActivity{

    private MentorController mMentorController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMentorController = new MentorController(this);
    }
}
