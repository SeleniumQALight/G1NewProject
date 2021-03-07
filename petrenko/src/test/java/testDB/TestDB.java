package testDB;


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








    @After
    public void tearDown() throws SQLException {
        mySqlDatabase.quit();
    }

    @Test
    public void testDataBase() throws SQLException {
        List<Map<String , String>>  dataFromSeleniumTable = mySqlDatabase.selectTableAsMap("select * from seleniumTable");
        logger.info(dataFromSeleniumTable);
    }



}



