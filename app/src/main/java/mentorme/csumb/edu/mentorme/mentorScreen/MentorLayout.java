package mentorme.csumb.edu.mentorme.mentorScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentor;
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentors;
import mentorme.csumb.edu.mentorme.data.model.topics.Topic;
import mentorme.csumb.edu.mentorme.mentorInfoScreen.MentorInfoActivity;
import mentorme.csumb.edu.mentorme.mentorScreen.mentorsLyoutAdapter.MentorsAdapter;
import rx.Subscriber;

/**
 * Handles user Interface for Mentors.
 */

public class MentorLayout extends Subscriber<Mentors> implements MentorsAdapter.MentorsViewHolderListener {

    private final String TAG = "MentorLayout";
    private MentorActivity mActivity;
    private ArrayList<Mentor> mMentors;

    @BindView(R.id.toolbar_back_arrow) ImageView mBackArrow;
    @BindView(R.id.network_error_layout) LinearLayout mNetworkErrorLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_no_menu) Toolbar mToolbar;
    @BindView(R.id.toolbar_title) TextView mToolbarTitle;

    public MentorLayout(MentorActivity activity) {

        mActivity = activity;

        mActivity.setContentView(R.layout.topics_layout);

        ButterKnife.bind(this, mActivity);

        mToolbarTitle.setText(R.string.mentors);
        mActivity.setSupportActionBar(mToolbar);
    }

    @OnClick(R.id.toolbar_back_arrow)
    public void onBackArrowClicked() {
        mActivity.onBackPressed();
    }

    @Override
    public void onCompleted() { }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, e.getMessage());
        mNetworkErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNext(Mentors mentors) {
        mMentors = mentors.getMentors();

        MentorsAdapter adapter = new MentorsAdapter(
                mActivity.getApplicationContext(),
                mMentors,
                this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onButtonClicked(int position) {
        String subjectId = "";
        String topicId = "";

        Intent intent = new Intent(mActivity.getApplicationContext(), MentorInfoActivity.class);

        Bundle bundle = mActivity.getIntent().getExtras();
        if (bundle != null) {
            subjectId = bundle.getString("subjectId");
            topicId = bundle.getString("topicId");
        }

        intent.putExtra("subjectId", subjectId);
        intent.putExtra("topicId", topicId);
        intent.putExtra("mentorId", mMentors.get(position).getId());

        mActivity.startActivity(intent);
    }
}
