package mentorme.csumb.edu.mentorme.loginTest;

import android.content.Intent;

import com.google.android.gms.common.api.GoogleApiClient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mentorme.csumb.edu.mentorme.login.LoginActivity;
import mentorme.csumb.edu.mentorme.login.LoginController;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;


public class LoginControllerTest {

    private static final int RC_SIGN_IN = 9001;

    @Mock LoginController mLoginController;
    @Mock GoogleApiClient mMockGoogleApiClient;
    @Mock LoginActivity mMockLoginActivity;

    private static final String NON_CSUMB = "bob@gmail.com";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void initialSubscriber_shouldCallInitialSignIn() {
        // when
        mLoginController.initialSignIn(mMockGoogleApiClient);

        // then
        verify(mLoginController).initialSignIn(mMockGoogleApiClient);
    }

}
