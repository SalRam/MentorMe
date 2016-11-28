package mentorme.csumb.edu.mentorme.topicScreen.topicsLayoutAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import mentorme.csumb.edu.mentorme.mentorScreen.MentorActivity;

/**
 * Created by benitosanchez on 11/13/16.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {

    private ArrayList<Topic> mTopics;
    private Context mContext;

    public TopicsAdapter(Context context, ArrayList<Topic> topics) {
        mContext = context;
        mTopics = topics;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View topicView = inflater.inflate(R.layout.subject, parent, false);

        ViewHolder viewHolder = new ViewHolder(topicView);

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
        @BindView(R.id.subject_button)
        Button subjectButton;
        private final Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.subject_button)
        public void onButtonClick(Button button) {
            Intent intent = new Intent(context, MentorActivity.class);
            context.startActivity(intent);
        }
    }
}
