package mentorme.csumb.edu.mentorme.homeScreen;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.data.model.Subjects;
import rx.Subscriber;

/**
 * Contains the functionality for the result that need to be displayed
 */

class HomeLayout extends Subscriber<Subjects> {

    private final String TAG = "HomeLayout";

    private AppCompatActivity mActivity;
    private HomeModel mHomeModel;

    @BindView(R.id.subjects_list) ListView mSubjectsList;

    HomeLayout(AppCompatActivity activity) {

        mActivity = activity;
        mActivity.setContentView(R.layout.activity_home);
        mHomeModel = new HomeModel();

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
        Log.d(TAG, subjects.getItems().get(0).getMessage());

        ArrayList<HashMap<String, String>> list = mHomeModel.parseResponse(subjects.getItems());

        ListAdapter adapter = new SimpleAdapter(
                mActivity, list,
                R.layout.subjects_list, new String[] {HomeModel.MESSAGE, HomeModel.KIND},
                new int[]{R.id.message, R.id.kind});

        mSubjectsList.setAdapter(adapter);
    }
}
