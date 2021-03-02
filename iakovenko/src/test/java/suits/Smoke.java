package suits;

import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import posts.CreateNewPostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                LoginTestWithPageObject.class,
                CreateNewPostTest.class
        }
)
public class Smoke {

}
