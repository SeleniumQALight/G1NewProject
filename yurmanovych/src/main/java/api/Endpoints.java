package api;

public interface Endpoints {

    // Privatbank API
    String pbBaseUrl = "https://api.privatbank.ua/p24api";
    String PB_GET_EXCH_RATES = pbBaseUrl + "/pubinfo?json&exchange&coursid=5";
    String PB_GET_BRANCHES = pbBaseUrl + "/pboffice?json&city={1}&address={2}";

    // Testing app API
    String testAppBaseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String TEST_APP_POST_BY_USER = testAppBaseUrl + "/api/postsByAuthor/{1}";
    String LOGIN = testAppBaseUrl + "/api/login";
    String CREATE_POST = testAppBaseUrl + "/api/create-post";

}
