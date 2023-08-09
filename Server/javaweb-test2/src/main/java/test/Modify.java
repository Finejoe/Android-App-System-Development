package test;

import UersAndPerson.JDBCmanager;
import UersAndPerson.TestDB;
import UersAndPerson.users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Modify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        PrintWriter write= resp.getWriter();

        req.setCharacterEncoding("utf-8");
        String tele=null,pass=null;
        if(req.getParameter("tele")!="")
            tele=req.getParameter("tele");

        if(req.getParameter("pass")!="")
            pass=req.getParameter("pass");

        System.out.println(tele);
        Connection con = null;
        Statement sta = null;
        con=new JDBCmanager().ClaimConnection();
        boolean flag=false;
        try {
            sta=con.createStatement();
            flag=TestDB.Find(tele, "tele", sta);
            System.out.println("找到的flag为:"+flag);
            if(flag){
                users u=TestDB.getByTele(tele,sta);
                u.setPass(pass);
                TestDB.insertMember(u,sta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new JDBCmanager().ConnectionDisposal(con,sta);
        if(flag)
            write.print("true");
        else
            write.print("false");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
