package suits;

import apiTests.ApiTests;
import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pages.CreatePostPage;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                LoginTestWithPageObject.class,
                ApiTests.class




        }
)
public class Smoke {






}
