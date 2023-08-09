package test;

import UersAndPerson.JDBCmanager;
import UersAndPerson.TestDB;
import UersAndPerson.users;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection con = null;
        Statement sta = null;
        con=new JDBCmanager().ClaimConnection();
        try {
            sta=con.createStatement();
            users u=null;
            if(TestDB.Find("cyx","username",sta)) {
                u = TestDB.getByUsername("cyx", sta);
                if (u.getPass().equals("cyx")) System.out.print("true");
            }
            else System.out.print("false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new JDBCmanager().ConnectionDisposal(con,sta);
    }
}
