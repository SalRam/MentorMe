package mentorme.csumb.edu.mentorme.homeScreen;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.data.model.Subjects;
import mentorme.csumb.edu.mentorme.R;
import rx.Subscriber;

/**
 * Contains the functionality for the result that need to be displayed
 */

class HomeLayout extends Subscriber<Subjects> {

    private final String TAG = "HomeLayout";

    private AppCompatActivity mActivity;
    private HomeModel mHomeModel;

    private HomeLayoutListener mHomeListener;

    @BindView(R.id.subjects_list) ListView mSubjectsList;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawer;
    @BindView(R.id.nav_view) NavigationView mNavigationView;

    HomeLayout(AppCompatActivity activity, HomeLayoutListener listener) {

        mActivity = activity;
        mActivity.setContentView(R.layout.activity_home);
        mHomeModel = new HomeModel();
        mHomeListener = listener;

        ButterKnife.bind(this, mActivity);

        mActivity.setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mActivity, mDrawer, mToolbar, R.string.open, R.string.close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
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
    }

    @Override
    public void onNext(Subjects subjects) {

        ArrayList<HashMap<String, String>> list = mHomeModel.parseResponse(subjects.getSubjects());

        ListAdapter adapter = new SimpleAdapter(
                mActivity, list,
                R.layout.subjects_list,
                new String[] {HomeModel.SUBJECT},
                new int[]{R.id.subject}
        );

        mSubjectsList.setAdapter(adapter);
    }

    public interface HomeLayoutListener{

        public void onNavigationMenuClick();
    }
}
