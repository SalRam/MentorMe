package mentorme.csumb.edu.mentorme.mentorMeApi.mentorMeImpl;

import mentorme.csumb.edu.mentorme.mentorMeApi.MentorMeApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by benitosanchez on 11/3/16.
 */

public class Factory {

    public final static String BASE_URL = "https://mentorme-api.appspot.com/_ah/api/helloworld/";

    private static MentorMeApi service;

    public static MentorMeApi getInstance() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();

            return retrofit.create(MentorMeApi.class);
        } else {
            return service;
        }
    }
}
