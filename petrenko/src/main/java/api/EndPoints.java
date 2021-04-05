package api;

import java.net.URI;

public interface EndPoints {
    String baseUrl = "https://qa-complex-app-for-testing.herokuapp.com";

    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{1}";
    String DELETE_POST = baseUrl + "/api/post/{1}";

    String LOGIN = baseUrl + "/api/login";
    String CREATE_POST = baseUrl+"/api/create-post";


    String CURRENCY = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid={1}";
}
