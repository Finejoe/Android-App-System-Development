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

public class Disposal extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        //接收用户名和密码
            /*
            get会中文乱码，这是因为get（通过url）和post（通过数据包）传递参数的方式不一样
             */
        resp.setCharacterEncoding("utf-8");
//        resp.setContentType("text/html");
        PrintWriter write= resp.getWriter();


        req.setCharacterEncoding("utf-8");
        System.out.printf("进入到处理逻辑\n");
        String username=null,pass= null,tele=null;


        if(req.getParameter("username")!="")
        username = req.getParameter("username");

        if(req.getParameter("pass")!="")
        pass = req.getParameter("pass");

        if(req.getParameter("tele")!="")
        tele= req.getParameter("tele");
        printAll(username,pass,tele);

        Connection con = null;
        Statement sta = null;
        con=new JDBCmanager().ClaimConnection();
        try {
            sta=con.createStatement();
            if(!TestDB.Find(username,"username",sta)){
                users u = new users(username, pass, tele);
                TestDB.insertMember(u, sta);
                write.print("注册成功");
            }
            else{
                write.print("注册失败，用户名已存在");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new JDBCmanager().ConnectionDisposal(con,sta);
    }
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
    }
    public void printAll(String a,String b,String c){
        System.out.println(a+" "+b+" "+c);
    }

}
