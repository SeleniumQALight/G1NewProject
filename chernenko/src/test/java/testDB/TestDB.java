package testDB;

import baseTest.BaseTest;
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

public class TestDB  {
    private Database mySqlDatabase;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        mySqlDatabase = MySQL_Database.getDataBase();
    }

    @After
    public void tearDown() throws SQLException {
        mySqlDatabase.quit();
    }


    @Test
    public void testDataFromDataBase() throws SQLException, ClassNotFoundException {
        List<Map<String, String>> dataFromSeleniumTable = mySqlDatabase.selectTableAsMap
                ("SELECT * FROM seleniumTable WHERE login = 'G1LenaChe'");
        logger.info(dataFromSeleniumTable);
//        int numbersOfRows = mySqlDatabase.changeTable("INSERT INTO seleniumTable VALUE (777,'G1LenaChe1','PASS')");
//        logger.info(numbersOfRows);
        dataFromSeleniumTable = mySqlDatabase.selectTableAsMap
                ("SELECT * FROM seleniumTable WHERE login = 'G1LenaChe'");
        logger.info(dataFromSeleniumTable);
        dataFromSeleniumTable = mySqlDatabase.selectTableAsMap
                ("SELECT * FROM seleniumTable WHERE login LIKE 'G1%' ");
        logger.info(dataFromSeleniumTable);

        DB_Util db_util = new DB_Util();
        logger.info(db_util.getPassFowLogin("G1LenaChe"));
    }


}
