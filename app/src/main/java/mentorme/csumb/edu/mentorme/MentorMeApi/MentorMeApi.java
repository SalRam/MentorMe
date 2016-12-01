package mentorme.csumb.edu.mentorme.mentorMeApi;

import mentorme.csumb.edu.mentorme.data.model.mentorInfo.MentorInfo;
import mentorme.csumb.edu.mentorme.data.model.mentors.Mentors;
import mentorme.csumb.edu.mentorme.data.model.subjects.Subjects;
import mentorme.csumb.edu.mentorme.data.model.topics.Topics;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * MentorMe Api endpoints.
 */
public interface MentorMeApi {

    String CSUMB_ID = "fe40dcf2-15f8-49fd-864b-5048bc73f424";

    /**
     * Endpoint for getting a list of subjects.
     *
     * @return An Observable {@link Subjects}.
     */
    @GET(CSUMB_ID + "/list")
    rx.Observable<Subjects> getSubjects();

    /**
     * Endpoint for getting a list of topics.
     *
     * @param subjectId The subject id to display.
     *
     * @return A list of Topics with subject id.
     */
    @GET(CSUMB_ID + "/{subjectId}/list")
    rx.Observable<Topics> getTopics(@Path("subjectId") String subjectId);

    /**
     * Endpoint for getting alist of Mentors.
     *
     * @param subjectId The mentor's subject id.
     * @param topicId The mentor's topic id.
     *
     * @return A list of Mentors with matched values.
     */
    @GET(CSUMB_ID + "/{subjectId}/{topicId}/list")
    rx.Observable<Mentors> getMentors(
            @Path("subjectId") String subjectId,
            @Path("topicId") String topicId);

    /**
     * Endpoint for getting a mentor's information.
     *
     * @param subjectId The mentor's subject id.
     * @param topicId The mentor's topic id.
     * @param mentorId The mentor's id.
     *
     * @return The {@link MentorInfo} object with mentor's information.
     */
    @GET(CSUMB_ID + "/{subjectId}/{topicId}/{mentorId}")
    rx.Observable<MentorInfo> getMentorInformation(
            @Path("subjectId") String subjectId,
            @Path("topicId") String topicId,
            @Path("mentorId") String mentorId);

}
