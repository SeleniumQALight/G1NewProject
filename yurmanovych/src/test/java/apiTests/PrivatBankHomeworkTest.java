package apiTests;

import api.ApiHelperPrivatbank;
import api.Endpoints;
import api.ExchangeRateDTO;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Arrays;

public class PrivatBankHomeworkTest {

    final private Logger LOG = Logger.getLogger(getClass());

    @Test
    public void getPBExchangeRatesTest() {
        ExchangeRateDTO[] response = ApiHelperPrivatbank.getExchangeRatesPB();
        Arrays.stream(response).forEach(val -> LOG.info(String.format(
                "Exchange rate of %s to %s:->  buy: %s sell: %s",
                val.getCcy(),val.getBase_ccy(),val.getBuy(),val.getSale())));
    }
}
