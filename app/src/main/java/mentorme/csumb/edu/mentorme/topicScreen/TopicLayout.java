package mentorme.csumb.edu.mentorme.topicScreen;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.topics.Topics;
import mentorme.csumb.edu.mentorme.topicScreen.topicsLayoutAdapter.TopicsAdapter;
import rx.Subscriber;

/**
 * Handles user Interface for Topics.
 */
public class TopicLayout extends Subscriber<Topics> {

    private final String TAG = "TopicLayout";

    private TopicActivity mActivity;
    private TopicLayoutListener mListener;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar_no_menu) Toolbar mToolbar;
    @BindView(R.id.toolbar_title) TextView mToolbarTitle;
    @BindView(R.id.toolbar_back_arrow) ImageView mBackArrow;
    @BindView(R.id.network_error_layout) LinearLayout mNetworkErrorLayout;

    TopicLayout(TopicActivity activity, TopicLayoutListener listener) {
        mActivity = activity;
        mListener = listener;

        mActivity.setContentView(R.layout.topics_layout);

        ButterKnife.bind(this, mActivity);

        mToolbarTitle.setText(R.string.topics);
        mActivity.setSupportActionBar(mToolbar);
    }

    @OnClick(R.id.toolbar_back_arrow)
    public void onBackArrowClicked() {
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
    public void onNext(Topics topics) {
        TopicsAdapter adapter = new TopicsAdapter(
                mActivity.getApplicationContext(),
                topics.getTopics());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * Listener for {@link TopicLayout}
     */
    public interface TopicLayoutListener {
        void onNavigationMenuClick();
    }
}
