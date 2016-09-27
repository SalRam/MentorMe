package mentorme.csumb.edu.mentorme;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import mentorme.csumb.edu.mentorme.Endpoint.EndpointsAsyncTask;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ** Needed for Fabric **//
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked() {
        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "BenFallas"));
    }
}

