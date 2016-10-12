package mentorme.csumb.edu.mentorme.login;

/**
 * Created by sal on 10/10/16.
 */
public class StartActivityLogInModule {
    private static StartActivityLogInModule ourInstance = new StartActivityLogInModule();

    public static StartActivityLogInModule getInstance() {
        return ourInstance;
    }

    private StartActivityLogInModule() {
    }
}
