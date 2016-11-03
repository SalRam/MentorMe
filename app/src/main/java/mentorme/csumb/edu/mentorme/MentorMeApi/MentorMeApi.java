package mentorme.csumb.edu.mentorme.mentorMeApi;

import java.util.Observable;

import mentorme.csumb.edu.mentorme.data.model.Subjects;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * MentorMe Api.
 */
public interface MentorMeApi {

    String BASE_URL = "https://mentorme-api.appspot.com/";

    @GET("_ah/api/helloworld/v1/hellogreeting/")
    rx.Observable<Subjects> getSubjects();

    class Factory {

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
}
