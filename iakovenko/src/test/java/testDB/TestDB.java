package testDB;

import libs.DB_Util;
import libs.Database;
import libs.MySQL_Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestDB {

    private Database mySqlDatabase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mySqlDatabase = MySQL_Database.getDataBase();
    }

    @Test
    public void testDataFromDataBase () throws SQLException, ClassNotFoundException {
        List<Map<String,String>> dataFromSeleniumTable =
                mySqlDatabase.selectTableAsMap("select * from seleniumTable where login = 'G1Roman'");
        logger.info(dataFromSeleniumTable);
//        int numberOfRows = mySqlDatabase.changeTable("insert into seleniumTable VALUES(701, 'G1Roman','PASS')");
//        logger.info(numberOfRows);

        dataFromSeleniumTable =
                mySqlDatabase.selectTableAsMap("select * from seleniumTable where login = 'G1Roman'");
        logger.info(dataFromSeleniumTable);

        DB_Util db_util = new DB_Util();
        logger.info(db_util.getPassForLogin("G1Roman"));

    }


    @After
    public void tearDown () throws SQLException {
        mySqlDatabase.quit();
    }
}
