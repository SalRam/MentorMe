package mentorme.csumb.edu.mentorme.homeScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.profileScreen.ProfileActivity;

/**
 * Created by benitosanchez on 11/13/16.
 */

public class NavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

    private AppCompatActivity mActivity;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

    NavigationMenu(AppCompatActivity activity) {
        mActivity = activity;
        ButterKnife.bind(this, mActivity);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(mActivity, ProfileActivity.class);
                mActivity.startActivity(intent);
                break;
            default:
                Toast.makeText(mActivity.getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
