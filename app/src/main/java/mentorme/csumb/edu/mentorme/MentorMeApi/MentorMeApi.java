package mentorme.csumb.edu.mentorme.mentorMeApi;

import mentorme.csumb.edu.mentorme.data.model.mentorInfo.MentorInfo;
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentor;
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentors;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subjects;
import mentorme.csumb.edu.mentorme.data.model.topics.Topics;
import retrofit2.http.GET;

/**
 * MentorMe Api endpoints.
 */
public interface MentorMeApi {

    /**
     * Endpoint for getting a list of subjects.
     *
     * @return An Observable {@link Subjects}.
     */
    @GET("csumb/list")
    rx.Observable<Subjects> getSubjects();

    /**
     * Endpoint for getting a list of topics.
     *
     * @return An Observable {@link Topics}.
     */
    @GET("csumb/cst/list")
    rx.Observable<Topics> getTopics();

    /**
     * Endpoint for getting a list of mentors.
     *
     * @return An Observable {@link Mentors}.
     */
    @GET("csumb/cst/cst%20334/list")
    rx.Observable<Mentors> getMentors();

    /**
     * Endpoint for getting mentor's information.
     *
     * @return An Observable {@link MentorInfo} with mentor's information.
     */
    @GET("csumb/cst/cst%20334/34529058294")
    rx.Observable<MentorInfo> getMentorInformation();

}
