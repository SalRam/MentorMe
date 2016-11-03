package mentorme.csumb.edu.mentorme.mentorMeApi;

import mentorme.csumb.edu.mentorme.data.model.Subjects;
import retrofit2.http.GET;

/**
 * MentorMe Api.
 */
public interface MentorMeApi {

    @GET("v1/hellogreeting/")
    rx.Observable<Subjects> getSubjects();

}
