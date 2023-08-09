package test;

import UersAndPerson.JDBCmanager;
import UersAndPerson.TestDB;
import UersAndPerson.users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter write= resp.getWriter();
        req.setCharacterEncoding("utf-8");
        String username=null,pass= null;

        if(req.getParameter("username")!="")
            username = req.getParameter("username");

        if(req.getParameter("pass")!="")
            pass = req.getParameter("pass");

        System.out.println("username="+username+"     pass="+pass);

        Connection con = null;
        Statement sta = null;
        con=new JDBCmanager().ClaimConnection();
        try {
            sta=con.createStatement();
            users u=null;
            if(TestDB.Find(username,"username",sta)) {
                u = TestDB.getByUsername(username, sta);
                if (u.getPass().equals(pass)) write.print("true");
                else write.print("false");
            }
            else write.print("false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new JDBCmanager().ConnectionDisposal(con,sta);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
