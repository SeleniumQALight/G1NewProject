package api;

import io.restassured.http.ContentType;
import libs.TestData;
import org.apache.log4j.Logger;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiPrivatHelper {
    Logger logger = Logger.getLogger(getClass());


    public NBUCurrencyDTO[] nduCurrencyByAPI(){
        NBUCurrencyDTO[] bodyResponse =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5")
                        .then()
                        .statusCode(200).log().all()
                        .extract()
                        .response().as(NBUCurrencyDTO[].class);
        return bodyResponse;
    }

    public void getBuyValueApi(String currencyName) {
        NBUCurrencyDTO[] bodyResponse = nduCurrencyByAPI();
        for (NBUCurrencyDTO nbuCurrencyDTO : bodyResponse) {
            if (currencyName.equals(nbuCurrencyDTO.getCcy())) {
                TestData.BUY_VALUE_API = nbuCurrencyDTO.getBuy();
                logger.info("Currency form API response " + TestData.BUY_VALUE_API + "to BUY");
            }
        }
    }

    public void getSaleValueApi(String currencyName) {
        NBUCurrencyDTO[] bodyResponse = nduCurrencyByAPI();
        for (NBUCurrencyDTO nbuCurrencyDTO : bodyResponse) {
            if (currencyName.equals(nbuCurrencyDTO.getCcy())) {
                TestData.SALE_VALUE_API = nbuCurrencyDTO.getSale();
                logger.info("Currency form API response " + TestData.SALE_VALUE_API + "to SALE");
            }
        }
    }

    public  void getBuySaleValueApi (String currencyName){
        NBUCurrencyDTO[] bodyResponse = nduCurrencyByAPI();
        for (NBUCurrencyDTO nbuCurrencyTDO : bodyResponse) {
            if (currencyName.equals(nbuCurrencyTDO.getCcy())){
            TestData.BUY_VALUE_API = nbuCurrencyTDO.getBuy();
            logger.info("Buy currency rate from API response " + TestData.BUY_VALUE_API);
            TestData.SALE_VALUE_API =nbuCurrencyTDO.getSale();
            logger.info("Sale currency rate from API response " + TestData.SALE_VALUE_API);
            }
        }
    }

}
