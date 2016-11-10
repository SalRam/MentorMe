package mentorme.csumb.edu.mentorme.homeScreen;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.Subjects;
import mentorme.csumb.edu.mentorme.homeScreen.homeLayoutAdapter.SubjectsAdapter;
import rx.Subscriber;

/**
 * Contains the functionality for the result that need to be displayed
 */

class HomeLayout extends Subscriber<Subjects> {

    private final String TAG = "HomeLayout";

    private AppCompatActivity mActivity;

    @BindView(R.id.subjects) RecyclerView mRecyclerView;

    HomeLayout(AppCompatActivity activity) {

        mActivity = activity;
        mActivity.setContentView(R.layout.activity_home);

        ButterKnife.bind(this, mActivity);
    }

    @Override
    public void onCompleted() { }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, e.getMessage());
    }

    @Override
    public void onNext(Subjects subjects) {

        SubjectsAdapter adapter = new SubjectsAdapter(
                mActivity.getApplicationContext(),
                subjects.getSubjects());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity.getApplicationContext()));
        mRecyclerView.setAdapter(adapter);

    }
}
