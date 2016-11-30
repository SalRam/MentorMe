package mentorme.csumb.edu.mentorme.data.component;

import javax.inject.Singleton;

import dagger.Component;
import mentorme.csumb.edu.mentorme.data.module.AppModule;
import mentorme.csumb.edu.mentorme.data.module.NetModule;
import retrofit2.Retrofit;

/**
 * Component for objects that will be used through out the app life cycle.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    /**
     * Should return a {@link Retrofit}
     *
     * @return a {@link Retrofit}
     */
    Retrofit retrofit();
}

