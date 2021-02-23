package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import loginTests.LoginTestWithPageObject;
import posts.CreateNewPostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                LoginTestWithPageObject.class,
                CreateNewPostTest.class,
        }
)
public class SmokeSuit {
}
