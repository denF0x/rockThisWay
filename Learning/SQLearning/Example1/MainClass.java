package Learning.SQLearning.Example1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Денис on 25.02.2017.
 */
public class MainClass {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/testphones";
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "Java4Everyone");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        Connection cn = null;
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        try { //1 блок
            cn = ConnectorDB.getConnection();
            Statement st = null;
            try { //2 блок
                st = cn.createStatement();
                ResultSet rs = null;
                try { //3 блок
                    rs = st.executeQuery("SELECT * FROM phonebook ORDER BY `IDPHONEBOOK`");
                    ArrayList<Abonent> lst = new ArrayList<>();
                        while (rs.next()) {
                            int id = rs.getInt(1);
                            int phone = rs.getInt(3);
                            String name = rs.getString(2);
                            lst.add(new Abonent(id, phone, name));
                        }
                        if (lst.size() > 0) {
                            System.out.println(lst);
                        } else {
                            System.out.println("Not found");
                        }
                } finally { // для 3 блока try
                    /*
                    * закрыть ResultSet, если он был открыт
                    * или ошибка произошла во время
                    * чтения из него данных
                    */
                    if (rs != null) { //был ли создан ResultSet
                        rs.close();
                    } else {
                        System.err.println("Ошибка во время чтения из БД");
                    }
                }
            } finally {
                /*
                * закрыть Statement, если он был открыт или ошибка
                * произошла во время создания Statement
                */
                if (st != null) { // для 2 блока try
                    st.close();
                } else {
                    System.err.println("Statement не создан");
                }
            }
        } catch (SQLException e) { //для 1 блока try
            System.err.println("Db connection error: " + e);
                                //вывод сообщения о всех SQLException
        } finally {
            //закрыть Connection, если он был открыт
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    System.err.println("Connection close error: " + e);
                }
            }
        }
    }
}