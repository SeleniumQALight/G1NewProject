package libs;

public class TestData {
    public final  static  String VALID_LOGIN = "cherchenko";
    public final static String  VALID_PASSWORD = "123456qwerty";
    public final static String VALID_UNIQUE_USERNAME = "Unique";
    public final static String INVALID_SHORT_USERNAME = "qa";
    public final static String INVALID_LONG_USERNAME = "123456qwerty123456qwerty123456qwerty123456qwerty";
    public final static String VALID_UNIQUE_USER_EMAIL = "test"+Util.getDateAndTimeFormated()+"@test.ua";
    public final static String INVALID_EMAIL = "test.tes.ua";
    public final static String INVALID_LONG_PASSWORD="123456qwerty123456qwerty123456qwerty123456qwerty123456qwerty123456qwerty";
    public final static String INVALID_SHORT_PASSWORD = "pass";
    public static String BUY_VALUE_API;
    public static String SALE_VALUE_API;
    public static String BUY_VALUE_UI;
    public static String SALE_VALUE_UI;
}
