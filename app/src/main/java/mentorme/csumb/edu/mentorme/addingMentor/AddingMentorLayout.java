package mentorme.csumb.edu.mentorme.addingMentor;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subject;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subjects;
import mentorme.csumb.edu.mentorme.homeScreen.HomeLayout;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.ToolbarSupport;
import mentorme.csumb.edu.mentorme.views.layout.toolbar.backArrowToolbar.BackArrowToolbar;
import rx.Subscriber;

/**
 * Handles user Interface for AddingMentors.
 */

public class AddingMentorLayout extends Subscriber<Subjects> {

    private final String TAG = "AddingMentorLayout";
    private AddingMentorActivity mActivity;
    private ToolbarSupport mToolbar;

    @BindView(R.id.subject_spinner) Spinner mSubjectSpinner;
    @BindView(R.id.network_error_layout) LinearLayout mNetworkErrorLayout;

    ArrayList<Subject> subjectSpinnerArray = new ArrayList<Subject>();

    public AddingMentorLayout(AddingMentorActivity activity) {

        mActivity = activity;

        mActivity.setContentView(R.layout.add_mentor_layout);

        ButterKnife.bind(this, mActivity);

        mToolbar = new BackArrowToolbar(mActivity, R.string.add_mentor);
    }

    @Override
    public void onCompleted() {}

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, e.getMessage());
        mNetworkErrorLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNext(Subjects subjects) {
        subjectSpinnerArray = subjects.getSubjects();

        ArrayAdapter<Subject> spinnerArrayAdapter = new ArrayAdapter<Subject>(mActivity,
                android.R.layout.simple_spinner_dropdown_item,
                subjectSpinnerArray);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSubjectSpinner.setAdapter(spinnerArrayAdapter);
    }

    /**
     * Listener for the {@link HomeLayout}
     */
    public interface AddingMentorListener{

        void onNavigationMenuClick();
    }

}