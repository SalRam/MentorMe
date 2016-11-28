package mentorme.csumb.edu.mentorme.homeScreen;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subjects;
import mentorme.csumb.edu.mentorme.homeScreen.homeLayoutAdapter.SubjectsAdapter;
import rx.Subscriber;

/**
 * Contains the functionality for the result that need to be displayed
 */

public class HomeLayout extends Subscriber<Subjects> {

    private final String TAG = "HomeLayout";

    private HomeActivity mActivity;

    @BindView(R.id.drawer_layout) DrawerLayout mDrawer;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_layout) Toolbar mToolbar;
    @BindView(R.id.toolbar_title) TextView mToolbarTitle;
    @BindView(R.id.network_error_layout) LinearLayout mNetworkErrorLayout;

    private HomeLayoutListener mHomeListener;

    HomeLayout(HomeActivity activity, HomeLayoutListener listener) {

        mActivity = activity;
        mHomeListener = listener;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mActivity, mDrawer, mToolbar, R.string.open, R.string.close);
        mDrawer.addDrawerListener(toggle);

        ButterKnife.bind(this, mActivity);

        mActivity.setContentView(R.layout.app_main_layout);
        toggle.syncState();
        mToolbarTitle.setText(R.string.subjects);
        mActivity.setSupportActionBar(mToolbar);
        mNavigationView.setNavigationItemSelectedListener(new NavigationMenu(mActivity));
    }
    @OnClick(R.id.nav_view)
    public void onNavigationMenuClick(){

        mHomeListener.onNavigationMenuClick();
    }
    @Override
    public void onCompleted() { }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, e.getMessage());
        mNetworkErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNext(Subjects subjects) {

        SubjectsAdapter adapter = new SubjectsAdapter(
                mActivity.getApplicationContext(),
                subjects.getSubjects());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mRecyclerView.setAdapter(adapter);
    }
    /**
     * Listener for the {@link HomeLayout}
     */
    public interface HomeLayoutListener{

        void onNavigationMenuClick();
    }
}
