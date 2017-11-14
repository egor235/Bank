package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 05.07.17.
 */
public class DbHelper {
    static Connection conn;
    private static DbHelper instance;
    private static String userName = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost/account";
    private Statement stmt = null;
    private  ResultSet rs = null;
    private  int sr;
    static Connection[] connections;
    List countpay = new ArrayList();
    Account account = new Account();

    public static DbHelper getInstance() {
        if (instance == null) {
            instance = new DbHelper();
        }
        return instance;
    }

    DbHelper() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connection established");
            stmt = conn.createStatement();

                  } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
            {
                if (conn != null) {
                    try {
                        conn.close();
                        System.out.println("Database connection terminated");
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    public Connection getConn() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
   public String readNumber(String number) throws Exception {
       rs = stmt.executeQuery("SELECT Name, Money FROM accountpay WHERE Number = " + number);
       while (rs.next()) {
           String name = rs.getString("Name");
           int money = rs.getInt("Money");
           account.setName(name);
           account.setMoney(money);
           return account.toString();
       }
       return account.toString();
   }

    public void updateNumber(String number1, String number2,  int count) throws Exception {
        int count1 =Integer.parseInt(String.valueOf(stmt.executeQuery("SELECT Money FROM accountpay WHERE Number="+number1)));
        int count2 =Integer.parseInt(String.valueOf(stmt.executeQuery("SELECT Money FROM accountpay WHERE Number="+number2)));
     //   rs = stmt.executeQuery("SELECT Id, Money FROM accountpay");
        sr = stmt.executeUpdate("UPDATE accountpay SET Money="+(count1-count)+ " WHERE Name="+number1+" AND "+ "SET Money="+(count2-count)+ " WHERE Name="+number2);

    }

        boolean isTablesExist() throws Exception {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM accountpay;");
        boolean result = true;
        int count = rs.getInt(1);
        if (count == 0) {
        result = false;
        }
        rs.close();
        stmt.close();
        return result;
        }
}

// String readResource(Class cpHolder, String path) throws Exception {
//   URL url = cpHolder.getResource(path);
// Path resPath = Paths.get(url.toURI());
//return new String(Files.readAllBytes(resPath), "UTF8");
// }


//   static void closeResource(AutoCloseable res) {
//     try {
//        if (res != null) {
//
//               res.close();
//              res = null;
//        }
//  } catch (Exception e) {
//    logger.warn("Failed to close resource: {}", res);
//}
//}
