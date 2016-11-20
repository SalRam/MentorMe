package mentorme.csumb.edu.mentorme.mentorScreen.mentorsLyoutAdapter;

import android.content.Context;
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
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentor;

/**
 * Mentors Adapter for the Recycler view.
 */


public class MentorsAdapter extends RecyclerView.Adapter<MentorsAdapter.ViewHolder> {

    private ArrayList<Mentor> mMentors;
    private Context mContext;

    public MentorsAdapter(Context context, ArrayList<Mentor> mentors) {
        mContext = context;
        mMentors = mentors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mentorView = inflater.inflate(R.layout.subject, parent, false);

        MentorsAdapter.ViewHolder viewHolder = new MentorsAdapter.ViewHolder(mentorView);

        return viewHolder;
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

    public static class ViewHolder extends RecyclerView.ViewHolder{

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
            Toast.makeText(context, button.getText(), Toast.LENGTH_SHORT).show();
            Log.d("View Holder", button.getText().toString());
        }
    }
}
