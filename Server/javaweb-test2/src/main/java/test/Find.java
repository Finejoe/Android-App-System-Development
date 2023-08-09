package test;

import UersAndPerson.JDBCmanager;
import UersAndPerson.TestDB;
import UersAndPerson.users;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Find extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter write= resp.getWriter();
        req.setCharacterEncoding("utf-8");
        String username=null;

        if(req.getParameter("username")!="")
            username = req.getParameter("username");

        Connection con = null;
        Statement sta = null;
        con=new JDBCmanager().ClaimConnection();
        try {
            sta=con.createStatement();
            users u=null;
            u = TestDB.getByUsername(username, sta);
            Gson gson = new Gson();
            String userJson = gson.toJson(u);
            write.println(userJson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new JDBCmanager().ConnectionDisposal(con,sta);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
