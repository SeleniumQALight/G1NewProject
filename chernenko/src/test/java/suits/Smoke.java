package suits;

import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import post.CreateNewPostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                LoginTestWithPageObject.class,
                CreateNewPostTest.class
        }
)

public class Smoke {

}
