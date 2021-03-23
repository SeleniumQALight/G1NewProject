package suits;

import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pages.CreatePostPage;
import regestrationTests.ValidRegistrationByClickSingUpForOurAppButton;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ValidRegistrationByClickSingUpForOurAppButton.class

        }
)
public class Smoke {



}
