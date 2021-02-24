package posts;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

/*1. Написать метод по работе с ДропДауном (найти елемент, клик, найти опшен в нем по тексту , клик)
        2. Тест по проверке еррор месседжей на форме регистрации - (тест
        вводит разные комбинации валидный и не валидных данныз и проверяет количество еррор месседжей)
        3. Задачка со звездочкой - Сделать дополнительно проверку текстов в
        месседжах с пункта два.  (Если есть желание и время, задание не обязательное)*/
public class PostDropDownTest extends BaseTest {
    final String POST_TITLE = "Yaroslav6 Title of Post" + Util.getDateAndTimeFormated();

    @Test
    public void checkDropDownPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectedOnCreatePostPage()
                .enterTitleToInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Post body")
                .selectValueInDropDown("Group Message")
                .clickOnButtonSaveNewPost()
                .checkIsRedirectToSinglePostPage()
                .checkIsSuccessMessageDisplayed()
        ;

    }
}
