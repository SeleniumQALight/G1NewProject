package testDB;

import libs.DB_Util;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import java.sql.SQLException;

public class TestDB {

    private Database mySqlDataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {

        mySqlDataBase = MySQL_Database.getDataBase();

    }



    @After
    public void  testDown() throws SQLException {
        mySqlDataBase.quit();
    }

    @Test
    public void testDataFromDataBase() throws SQLException, ClassNotFoundException {
        List<Map<String, String>> dataFromSeleniumTable =
                mySqlDataBase.selectTableAsMap("select * from seleniumTable where login='G1Inga'");
                logger.info(dataFromSeleniumTable);
                /**
                int numberOfRows = mySqlDataBase.changeTable("insert into seleniumTable values(919, 'G1Inga', 'IngaPass')");
                 logger.info(numberOfRows + " rows in table");
                 */

        dataFromSeleniumTable =
                mySqlDataBase.selectTableAsMap("select * from seleniumTable where login='G1Inga'");
        logger.info(dataFromSeleniumTable);

        DB_Util db_Util = new DB_Util();
        logger.info(db_Util.getPassForLogin("G2taras"));

    }

}
