package mentorme.csumb.edu.mentorme.topicScreen.topicsLayoutAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.topics.Topic;

/**
 * Created by benitosanchez on 11/13/16.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    private ArrayList<Topic> mTopics;
    private Context mContext;
    private TopicsViewHolderListener mListener;

    public TopicsAdapter(
            Context context,
            ArrayList<Topic> topics,
            TopicsViewHolderListener listener) {

        mContext = context;
        mTopics = topics;
        mListener = listener;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View topicView = inflater.inflate(R.layout.subject, parent, false);

        ViewHolder viewHolder = new ViewHolder(topicView, mListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Topic topic = mTopics.get(position);

        Button button = holder.subjectButton;
        button.setText(topic.getTopic() + "\n" + topic.getTitle());
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.subject_button) Button subjectButton;
        private TopicsViewHolderListener mListener;

        public ViewHolder(View itemView, TopicsViewHolderListener listener) {
            super(itemView);
            mListener = listener;

            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.subject_button)
        public void onButtonClick() {
            mListener.onButtonClicked(getAdapterPosition());
        }
    }

    /**
     * Interface listener that provides button position.
     */
    public interface TopicsViewHolderListener {
        void onButtonClicked(int position);
    }
}
