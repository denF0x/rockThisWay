package Learning.SQLearning.Example1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Денис on 26.02.2017.
 */
public class DataBaseHelper {
    private final static String SQL_INSERT = "INSERT INTO phonebook(idphonebook, lastaname, phone) VALUES (?,?,?)";
    private Connection connect;

    public DataBaseHelper() throws SQLException {
        connect = ConnectorDB.getConnection();
    }
    public PreparedStatement getPreparedStatement() {
        return null;
    }
}
