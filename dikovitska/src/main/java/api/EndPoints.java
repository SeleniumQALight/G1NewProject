package api;



public interface EndPoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{1}";

    String LOGIN = baseUrl + "/api/login";

    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{1}";
    String GET_CURRENCY_RATE = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
}
