package mentorme.csumb.edu.mentorme.mentorMeApi;

import mentorme.csumb.edu.mentorme.data.model.Subjects;
import retrofit2.http.GET;

/**
 * MentorMe Api.
 */
public interface MentorMeApi {

    /**
     * Endpoint for getting a list of subjects.
     *
     * @return An Observable {@link Subjects}.
     */
    @GET("v1/hellogreeting/")
    rx.Observable<Subjects> getSubjects();

}
