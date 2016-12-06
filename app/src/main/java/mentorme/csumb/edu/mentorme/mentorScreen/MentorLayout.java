package mentorme.csumb.edu.mentorme.mentorScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentor;
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentors;
import mentorme.csumb.edu.mentorme.mentorInfoScreen.MentorInfoActivity;
import mentorme.csumb.edu.mentorme.mentorScreen.mentorsLyoutAdapter.MentorsAdapter;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.ToolbarSupport;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.backArrowToolbar.BackArrowToolbar;
import rx.Subscriber;

/**
 * Handles user Interface for Mentors.
 */

public class MentorLayout extends Subscriber<Mentors> implements MentorsAdapter.MentorsViewHolderListener {

    private final String TAG = "MentorLayout";
    private MentorActivity mActivity;
    private ArrayList<Mentor> mMentors;
    private ToolbarSupport mToolbar;
    private ArrayList<Mentor> mFilteredList;
    private MentorsAdapter mAdapter;

    private MentorController mController;

    @BindView(R.id.network_error_layout) LinearLayout mNetworkErrorLayout;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.search_text) EditText mSearchText;

    public MentorLayout(MentorActivity activity, MentorController controller) {

        mActivity = activity;
        mController = controller;
        mActivity.setContentView(R.layout.topics_layout);

        ButterKnife.bind(this, mActivity);

        mToolbar = new BackArrowToolbar(mActivity, R.string.mentors);
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
        mFilteredList = mMentors;

        initList();
        mSearchText.addTextChangedListener(mController);
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
        intent.putExtra("mentorId", mFilteredList.get(position).getId());

        mActivity.startActivity(intent);
    }

    void notifyTextChanged(ArrayList<Mentor> filteredList) {
        mFilteredList = filteredList;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mAdapter = new MentorsAdapter(mActivity.getApplicationContext(),
                mFilteredList,
                this);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Returns a list of mentors.
     */
    public ArrayList<Mentor> getMentors() {
        return mMentors;
    }

    private void initList() {
        mAdapter = new MentorsAdapter(
                mActivity.getApplicationContext(),
                mMentors,
                this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);
    }
}
