package mentorme.csumb.edu.mentorme.homeScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import butterknife.BindView;
import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.login.LoginActivity;
import mentorme.csumb.edu.mentorme.profileScreen.ProfileActivity;

/**
 * Activity class for Navigation Menu
 */

public class NavigationMenu implements NavigationView.OnNavigationItemSelectedListener {

    private HomeActivity mActivity;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;

    NavigationMenu(HomeActivity activity) {
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
            case R.id.sign_out:
                signOut();
                break;
            default:
                Toast.makeText(mActivity.getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        final Intent  intent = new Intent(mActivity, LoginActivity.class);

        Auth.GoogleSignInApi.revokeAccess(mActivity.getGoogleApiClient()).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        mActivity.getUserLocalStorage().clearUserData();
                        mActivity.startActivity(intent);
                    }
                });
    }
}
