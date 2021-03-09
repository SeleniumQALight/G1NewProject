package suit;

import LoginTests.LoginTestWithPageObject;
import categories.SmokeTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import posts.CreateNewPostTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTests.class)
@Suite.SuiteClasses(
        {
                LoginTestWithPageObject.class,
                CreateNewPostTest.class
        }
)

public class SmokeWithCategories {
}
