package mentorme.csumb.edu.mentorme.mentorMeApi.mentorMeImpl;

import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Implements {@link MentorMeApi}.
 */
public class Factory {

    public final static String BASE_URL = "https://mentorme-api.appspot.com/_ah/api/helloworld/";

    private static MentorMeApi service;

    /**
     * Creates {@link MentorMeApi} service.
     *
     * @return The {@link MentorMeApi}.
     */
    public static MentorMeApi getInstance() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();

            service = retrofit.create(MentorMeApi.class);

            return service;
        } else {
            return service;
        }
    }
}
