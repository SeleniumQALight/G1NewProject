package libs;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DB_Util {
    private Database mySQL_Database;
    Logger logger = Logger.getLogger(getClass());

    public String getPassForLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_Database = MySQL_Database.getDataBase();
        logger.info("--- Connected to DB -----");

        String pass = mySQL_Database.selectValue(
                String.format("select passWord from seleniumTable where login = '%s'", login)
        );
        mySQL_Database.quit();
        return pass;
    }
}
