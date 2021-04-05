package suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import apiTests.ApiTests;
import loginTests.LoginTestWithPageObject;
import posts.CreateNewPostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                LoginTestWithPageObject.class,
                CreateNewPostTest.class,
                ApiTests.class
        }
)

public class Smoke {
}
