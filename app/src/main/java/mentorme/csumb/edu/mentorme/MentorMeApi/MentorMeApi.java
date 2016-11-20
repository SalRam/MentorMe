package mentorme.csumb.edu.mentorme.mentorMeApi;

import mentorme.csumb.edu.mentorme.data.model.mentors.Mentors;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subjects;
import mentorme.csumb.edu.mentorme.data.model.topics.Topics;
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
    @GET("csumb/list")
    rx.Observable<Subjects> getSubjects();

    @GET("csumb/cst/list")
    rx.Observable<Topics> getTopics();

    @GET("csumb/cst/cst%20334/list")
    rx.Observable<Mentors> getMentors();

}
