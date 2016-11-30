package mentorme.csumb.edu.mentorme.data.component;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import mentorme.csumb.edu.mentorme.data.module.AppModule;
import mentorme.csumb.edu.mentorme.data.module.NetModule;
import retrofit2.Retrofit;

/**
 * Created by benitosanchez on 11/29/16.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter
    Retrofit retrofit();
}

