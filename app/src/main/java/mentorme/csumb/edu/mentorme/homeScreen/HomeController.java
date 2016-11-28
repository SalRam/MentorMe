package mentorme.csumb.edu.mentorme.homeScreen;

import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.Toast;

import mentorme.csumb.edu.mentorme.R;
import mentorme.csumb.edu.mentorme.mentorMeApi.mentorMeImpl.Factory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Controller for the {@link HomeActivity}.
 */
public class HomeController implements HomeLayout.HomeLayoutListener {

    private HomeActivity mActivity;
    private HomeLayout mHomeLayout;

    public HomeController(@NonNull HomeActivity activity) {
        mActivity = activity;
        mHomeLayout = new HomeLayout(activity, this);
        onAttach();
    }
    private void onAttach()  {
        Factory.getInstance().getSubjects()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mHomeLayout);
    }
    @Override
    public void onNavigationMenuClick() { }

    /**
     * Runs when Menu item is selected.
     *
     * @param item The Item selected.
     *
     * @return {@true} if item is found. {@false} otherwise.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_mentor:
                Toast.makeText(mActivity.getApplicationContext(), "Add mentor", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}
