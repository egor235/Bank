package server;

/**
 * Created by admin on 05.07.17.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static server.DbHelper.conn;

/**
 * Created by igor on 17.12.14.
 */
public class TestDB {
    static DbHelper dbHelper = DbHelper.getInstance();

    public static void main(String[] args) throws SQLException {
        selectExample();
        //  preparedStatementExample();
    }

    static void selectExample() throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder result = new StringBuilder();
        try {
         //   stmt = conn.createStatement();
          //  rs = stmt.executeQuery("SELECT Id, Name, Number, Money FROM accountpay");

          //  while(rs.next()){

                int id  = rs.getInt("Id");
                String name = rs.getString("Name");
                int number = rs.getInt("Number");
                int money = rs.getInt("Money");


                System.out.print("ID: " + id);
                System.out.print(", Age: " + name);
                System.out.print(", First: " + number);
                System.out.println(", Last: " + money);
            System.out.println(rs.first());
                    }
        } catch (SQLException e) {
            e.printStackTrace();
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
}

