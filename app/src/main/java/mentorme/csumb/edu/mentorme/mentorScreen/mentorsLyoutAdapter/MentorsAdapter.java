package mentorme.csumb.edu.mentorme.mentorScreen.mentorsLyoutAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentor;

/**
 * Mentors Adapter for the Recycler view.
 */
public class MentorsAdapter extends RecyclerView.Adapter<MentorsAdapter.ViewHolder> {

    private ArrayList<Mentor> mMentors;
    private Context mContext;
    private MentorsViewHolderListener mListener;

    public MentorsAdapter(
            Context context,
            ArrayList<Mentor> mentors,
            MentorsViewHolderListener listener) {
        mContext = context;
        mMentors = mentors;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mentorView = inflater.inflate(R.layout.subject, parent, false);

        return new ViewHolder(mentorView, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mentor mentor = mMentors.get(position);

        Button button = holder.subjectButton;
        button.setText(mentor.getName());

    }

    @Override
    public int getItemCount() {
        return mMentors.size();
    }


    public Context getContext() {
        return mContext;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.subject_button) Button subjectButton;
        private MentorsViewHolderListener mListener;

        ViewHolder(@NonNull View itemView, @NonNull MentorsViewHolderListener listener) {
            super(itemView);
            mListener = listener;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.subject_button)
        void onButtonClick() {
            mListener.onButtonClicked(getAdapterPosition());
        }
    }

    public interface MentorsViewHolderListener {
        void onButtonClicked(int position);
    }
}
