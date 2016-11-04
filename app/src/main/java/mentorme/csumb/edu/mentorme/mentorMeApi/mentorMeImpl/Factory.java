package mentorme.csumb.edu.mentorme.mentorMeApi.mentorMeImpl;

import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Implements {@link MentorMeApi}.
 */
public class Factory {

    public final static String BASE_URL = "http://mentorme-api-2.us-west-2.elasticbeanstalk.com/mentorme/";

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
