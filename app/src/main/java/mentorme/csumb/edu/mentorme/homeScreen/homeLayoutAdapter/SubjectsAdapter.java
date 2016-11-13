package mentorme.csumb.edu.mentorme.homeScreen.homeLayoutAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.Subject;
import mentorme.csumb.edu.mentorme.topicScreen.TopicActivity;

/**
 * Subjects Adapter for the Recycler view.
 */

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder> {

    private ArrayList<Subject> mSubjects;
    private Context mContext;

    /**
     * Initialises Adapter
     *
     * @param context Context for the adater.
     * @param subjects A container of subjects to be displayed by the adapter.
     */
    public SubjectsAdapter(Context context, ArrayList<Subject> subjects) {
        mContext = context;
        mSubjects = subjects;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.subject, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    /**
     * Given a holder, fills holder view values from list of subjects.
     *
     * @param holder View holder.
     * @param position Value's position.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Subject subject = mSubjects.get(position);

        Button button = holder.subjectButton;
        button.setText(subject.getSubject());
    }

    /**
     * Provides the number of values in the subjects container.
     *
     * @return The number of values in the subjects container.
     */
    @Override
    public int getItemCount() {
        return mSubjects.size();
    }

    /**
     * View holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.subject_button) Button subjectButton;
        private final Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.subject_button)
        public void onButtonClick(Button button) {
            Intent intent = new Intent(context, TopicActivity.class);
            context.startActivity(intent);

            Toast.makeText(context, button.getText(), Toast.LENGTH_SHORT).show();
            Log.d("View Holder", button.getText().toString());
        }
    }
}
