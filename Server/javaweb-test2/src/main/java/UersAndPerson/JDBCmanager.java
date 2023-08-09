package UersAndPerson;

import java.sql.*;

public class JDBCmanager {


    public Connection ClaimConnection() {
        Statement sta = null;
        Connection con = null;
        String url = "jdbc:mysql://49.234.111.234/wrf?useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String passward = "MCmaocai123";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passward);
        } catch (ClassNotFoundException var6) {
            var6.printStackTrace();
        } catch (SQLException var7) {
            var7.printStackTrace();
        }

        return con;
    }

    public  void ConnectionDisposal(Connection con, Statement sta, ResultSet set) {
        if (set != null) {
            try {
                set.close();
            } catch (SQLException var6) {
                var6.printStackTrace();
            }
        }

        if (sta != null) {
            try {
                sta.close();
            } catch (SQLException var5) {
                var5.printStackTrace();
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException var4) {
                var4.printStackTrace();
            }
        }

    }

    public void ConnectionDisposal(Connection con, Statement sta) {
        if (sta != null) {
            try {
                sta.close();
            } catch (SQLException var4) {
                var4.printStackTrace();
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException var3) {
                var3.printStackTrace();
            }
        }

    }
}
