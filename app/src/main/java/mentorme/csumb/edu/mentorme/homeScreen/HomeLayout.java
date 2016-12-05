package mentorme.csumb.edu.mentorme.homeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subject;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subjects;
import mentorme.csumb.edu.mentorme.homeScreen.homeLayoutAdapter.SubjectsAdapter;
import mentorme.csumb.edu.mentorme.topicScreen.TopicActivity;
import rx.Subscriber;

/**
 * Contains the functionality for the result that need to be displayed
 */

public class HomeLayout extends Subscriber<Subjects> implements SubjectsAdapter.ViewHolderListener {

    private final String TAG = "HomeLayout";

    private HomeActivity mActivity;

    @BindView(R.id.drawer_layout) DrawerLayout mDrawer;
    @BindView(R.id.nav_view) NavigationView mNavigationView;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_layout) Toolbar mToolbar;
    @BindView(R.id.toolbar_title) TextView mToolbarTitle;
    @BindView(R.id.network_error_layout) LinearLayout mNetworkErrorLayout;

    @BindView(R.id.search_text) EditText mSearchText;

    private HomeLayoutListener mHomeListener;
    private HomeController mController;
    private ArrayList<Subject> mSubjects;
    private ArrayList<Subject> mFilteredList;
    private SubjectsAdapter mAdapter;

    HomeLayout(HomeActivity activity, HomeLayoutListener listener, HomeController controller) {

        mActivity = activity;

        mController = controller;
        mActivity.setContentView(R.layout.app_main_layout);

        mHomeListener = listener;

        ButterKnife.bind(this, mActivity);
        mToolbarTitle.setText("Subjects");
        mToolbar.setTitle("");
        mActivity.setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mActivity, mDrawer, mToolbar, R.string.open, R.string.close);
        mDrawer.addDrawerListener(toggle);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(new NavigationMenu(mActivity));

    }

    /**
     * Initializes adapter.
     */
    public void initList() {
        mFilteredList = mSubjects;
        mAdapter = new SubjectsAdapter(
                mActivity.getApplicationContext(),
                mSubjects,
                this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);

    }

    @OnClick(R.id.nav_view)
    void onNavigationMenuClick(){
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
        mSubjects = subjects.getSubjects();
        initList();

        mSearchText.addTextChangedListener(mController);
    }

    public void notifyTextChanged(ArrayList<Subject> filteredList) {
        mFilteredList = filteredList;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mAdapter = new SubjectsAdapter(
                mActivity.getApplicationContext(),
                filteredList,
                HomeLayout.this);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public ArrayList<Subject> getSubjects() {return mSubjects;}

    @Override
    public void onButtonClicked(int position) {
        Intent intent = new Intent(mActivity.getApplicationContext(), TopicActivity.class);
        Subject subject = mFilteredList.get(position);
        intent.putExtra("subjectId",subject.getId());

        mActivity.startActivity(intent);
    }

    /**
     * Listener for the {@link HomeLayout}
     */
    public interface HomeLayoutListener{

        void onNavigationMenuClick();
    }
}
