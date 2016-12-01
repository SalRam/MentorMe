package mentorme.csumb.edu.mentorme.homeScreen.homeLayoutAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionProvider;
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
import mentorme.csumb.edu.mentorme.data.model.subjects.Subject;
import mentorme.csumb.edu.mentorme.topicScreen.TopicActivity;

/**
 * Subjects Adapter for the Recycler view.
 */

public class SubjectsAdapter
        extends RecyclerView.Adapter<SubjectsAdapter.ViewHolder> {

    private ArrayList<Subject> mSubjects;
    private Context mContext;
    private ViewHolderListener mListener;

    /**
     * Initialises Adapter
     *
     * @param context Context for the adater.
     * @param subjects A container of subjects to be displayed by the adapter.
     */
    public SubjectsAdapter(Context context, ArrayList<Subject> subjects, ViewHolderListener listener) {
        mContext = context;
        mSubjects = subjects;
        mListener = listener;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.subject, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView, mListener);

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
        ViewHolderListener mListener;

        public ViewHolder(View itemView, ViewHolderListener listener) {
            super(itemView);
            mListener = listener;

            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.subject_button)
        public void onButtonClick() {
            mListener.onButtonClicked(getAdapterPosition());
        }
    }

    public interface ViewHolderListener {
        void onButtonClicked(int position);
    }
}
