package mentorme.csumb.edu.mentorme;

import android.app.Application;

import mentorme.csumb.edu.mentorme.data.component.DaggerNetComponent;
import mentorme.csumb.edu.mentorme.data.component.NetComponent;
import mentorme.csumb.edu.mentorme.data.module.AppModule;
import mentorme.csumb.edu.mentorme.data.module.NetModule;

/**
 * Overrides default Application to create Net Components.
 */
public class MentorMeApp extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://mentorme-api-2.us-west-2.elasticbeanstalk.com/mentorme/"))
                .build();
    }

    /**
     * Returns build net component.
     *
     * @return the Net Component.
     */
    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
