package mentorme.csumb.edu.mentorme.topicScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.topics.Topic;
import mentorme.csumb.edu.mentorme.data.model.topics.Topics;
import mentorme.csumb.edu.mentorme.mentorScreen.MentorActivity;
import mentorme.csumb.edu.mentorme.topicScreen.topicsLayoutAdapter.TopicsAdapter;
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

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_no_menu) Toolbar mToolbar;
    @BindView(R.id.toolbar_title) TextView mToolbarTitle;
    @BindView(R.id.toolbar_back_arrow) ImageView mBackArrow;
    @BindView(R.id.network_error_layout) LinearLayout mNetworkErrorLayout;

    TopicLayout(TopicActivity activity) {
        mActivity = activity;
        mActivity.setContentView(R.layout.topics_layout);

        ButterKnife.bind(this, mActivity);

        mToolbarTitle.setText(R.string.topics);
        mActivity.setSupportActionBar(mToolbar);
    }

    @OnClick(R.id.toolbar_back_arrow)
    void onBackArrowClicked() {
        mActivity.onBackPressed();
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
        TopicsAdapter adapter = new TopicsAdapter(
                mActivity.getApplicationContext(),
                mTopics,
                this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mRecyclerView.setAdapter(adapter);
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
        intent.putExtra("topicId", mTopics.get(position).getId());

        mActivity.startActivity(intent);
    }
}
