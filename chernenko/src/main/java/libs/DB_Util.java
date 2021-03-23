package libs;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DB_Util {
    private Database mySQL_DataBase;
    Logger logger = Logger.getLogger(getClass());

    public String getPassFowLogin(String login) throws SQLException, ClassNotFoundException {
        mySQL_DataBase = MySQL_Database.getDataBase();
        logger.info("______ Connected to DB __________");
        String pass = mySQL_DataBase.selectValue(
                String.format("SELECT passWord FROM seleniumTable WHERE login = '%s'", login)
        );
        mySQL_DataBase.quit();
        return pass;
    }
}
