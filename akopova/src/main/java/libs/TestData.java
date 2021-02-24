package libs;

public class TestData {

    // Correct Credentials to log in
    public final static String VALID_LOGIN = "auto";
    public final static String VALID_PASSWORD = "123456qwerty";


    // Correct Credentials to sign up
    public final static String VALID_SIGNUP_LOGIN = "newAuto";
    public final static String VALID_SIGNUP_EMAIL = "mymail@gmail.com";
    public final static String VALID_SIGNUP_PASSWORD = "098765poiuyt";
    // Incorrect Credentials to sign up
    public final static String INVALID_SIGNUP_LOGIN_SIGN = "@auto";
    public final static String INVALID_SIGNUP_LOGIN_SHORT = "au";
    public final static String INVALID_SIGNUP_EMAIL = "No_Mail";
    public final static String INVALID_SIGNUP_PASSWORD = "@#$1a";

    public static String signUpErrorMessageLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

}
