package suits;

import categories.SmokeTests;
import loginTests.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pages.CreatePostPage;
import posts.CreateNewPostTest;

@RunWith(Categories.class)
@Categories.ExcludeCategory(SmokeTests.class)
@Suite.SuiteClasses(
        {
                LoginTestWithPageObject.class,
                CreateNewPostTest.class

        }
)
public class SmokeWithCategories {






}
