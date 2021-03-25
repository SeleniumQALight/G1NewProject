package api;

public interface Endpoints {
    String testAppBaseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String TEST_APP_POST_BY_USER = testAppBaseUrl + "/api/postsByAuthor/{1}";

    String pbBaseUrl = "https://api.privatbank.ua";
    String PB_GET_EXCH_RATES = pbBaseUrl + "/p24api/pubinfo?json&exchange&coursid=5";
}
