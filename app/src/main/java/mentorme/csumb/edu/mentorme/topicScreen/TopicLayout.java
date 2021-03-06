package mentorme.csumb.edu.mentorme.topicScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subject;
import mentorme.csumb.edu.mentorme.data.model.topics.Topic;
import mentorme.csumb.edu.mentorme.data.model.topics.Topics;
import mentorme.csumb.edu.mentorme.homeScreen.HomeLayout;
import mentorme.csumb.edu.mentorme.homeScreen.homeLayoutAdapter.SubjectsAdapter;
import mentorme.csumb.edu.mentorme.mentorScreen.MentorActivity;
import mentorme.csumb.edu.mentorme.topicScreen.topicsLayoutAdapter.TopicsAdapter;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.ToolbarSupport;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.backArrowToolbar.BackArrowToolbar;
import rx.Subscriber;

/**
 * Handles user Interface for Topics.
 */
class TopicLayout
        extends Subscriber<Topics>
        implements TopicsAdapter.TopicsViewHolderListener {

    private final String TAG = "TopicLayout";

    private TopicActivity mActivity;
    private ArrayList<Topic> mTopics;
    private ToolbarSupport mToolbar;
    private TopicsAdapter mAdapter;
    private TopicController mController;
    private ArrayList<Topic> mFilteredList;

    @BindView(R.id.network_error_layout) LinearLayout mNetworkErrorLayout;
    @BindView(R.id.search_text) EditText mSearchText;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    TopicLayout(TopicActivity activity, TopicController controller) {
        mActivity = activity;
        mController = controller;
        mActivity.setContentView(R.layout.topics_layout);
        mToolbar = new BackArrowToolbar(mActivity, R.string.topics);

        ButterKnife.bind(this, mActivity);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, e.getMessage());
        mNetworkErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNext(@NonNull Topics topics) {
        mTopics = topics.getTopics();
        mFilteredList = mTopics;
        initList();
        mSearchText.addTextChangedListener(mController);
    }

    @Override
    public void onButtonClicked(int position) {
        String subjectId =  "";

        Intent intent = new Intent(mActivity.getApplicationContext(), MentorActivity.class);

        Bundle bundle =mActivity.getIntent().getExtras();
        if (bundle != null) {
            subjectId = bundle.getString("subjectId");
        }

        intent.putExtra("subjectId", subjectId);
        intent.putExtra("topicId", mFilteredList.get(position).getId());

        mActivity.startActivity(intent);
    }

    void notifyTextChanged(ArrayList<Topic> filteredList) {
        mFilteredList = filteredList;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mAdapter = new TopicsAdapter(mActivity.getApplicationContext(),
                mFilteredList,
                this);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Returns a list of topics.
     *
     * @return The list of mentors.
     */
    public ArrayList<Topic> getTopics() {
        return mTopics;
    }

    private void initList() {
        mAdapter = new TopicsAdapter(
                mActivity.getApplicationContext(),
                mFilteredList,
                this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);
    }
}
