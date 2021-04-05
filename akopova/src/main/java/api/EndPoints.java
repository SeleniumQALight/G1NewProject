package api;


public interface EndPoints {

    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{1}";

    // PrivatBank
    String basicPrivURL = "https://api.privatbank.ua/";
    String currencyExchangeCashJson = basicPrivURL + "/p24api/pubinfo?json&exchange&coursid=5";
    String currencyExchangeCashXML = basicPrivURL + "/p24api/pubinfo?exchange&coursid=5";

    // Feerie
    String baseFeerieURL = "https://feerie.com.ua/ua";
    String nepalURL = baseFeerieURL + "/tours/1779";


    String LOGIN = baseUrl + "/api/login";
    String CREATE_POST = baseUrl + "/api/create-post";

    String DELETE_POST = baseUrl + "/api/post/{1}";
}
