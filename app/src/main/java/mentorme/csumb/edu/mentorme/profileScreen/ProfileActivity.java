package mentorme.csumb.edu.mentorme.profileScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.R;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_layout) Toolbar mToolbar;
    @BindView(R.id.toolbar_title) TextView mTooblarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main_layout);

        ButterKnife.bind(this);

        mTooblarTitle.setText("Profile");
        setSupportActionBar(mToolbar);
    }
}
