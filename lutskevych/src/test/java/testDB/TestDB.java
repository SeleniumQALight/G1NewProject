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
    private Database mySqlDataBase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mySqlDataBase = MySQL_Database.getDataBase();
    }
    @After
    public void tearDown() throws SQLException {
        mySqlDataBase.quit();
    }
    @Test
    public void testDataFromDataBase() throws SQLException, ClassNotFoundException {
        List<Map<String,String>> dataFromSeleniumTable = mySqlDataBase.selectTableAsMap("select * from seleniumTable where login='G1Lutskevych'");
        logger.info(dataFromSeleniumTable);
//        int numbersOfRows = mySqlDataBase.changeTable("INSERT INTO seleniumTable VALUES(222,'G1Lutskevych','pass')");
//        logger.info(numbersOfRows);
        dataFromSeleniumTable = mySqlDataBase.selectTableAsMap("select * from seleniumTable where login='G1Lutskevych'");
        logger.info(dataFromSeleniumTable);

        DB_Util db_util = new DB_Util();
        logger.info(db_util.getPassForLogin("G2taras"));

    }
}
