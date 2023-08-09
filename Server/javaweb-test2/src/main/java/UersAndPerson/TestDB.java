package UersAndPerson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDB {
    public static void updata(String sql,Statement sta) {
        try {
            sta.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static users getByUsername(String username,Statement sta){
        String sql = " select * from users where username='" + username + "'";
        ResultSet rs = null;
        users u= new users(null, null, null);
        try {
            rs = sta.executeQuery(sql);
            if(rs.next()) {
                u.setUsername(rs.getString("username"));
                u.setPass(rs.getString("pass"));
                u.setTele(rs.getString("tele"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    public static users getByTele(String tele,Statement sta){
        String sql = " select * from users where tele='" + tele + "'";
        ResultSet rs = null;
        users u= new users(null, null, null);
        try {
            rs = sta.executeQuery(sql);
            rs.next();
            u.setUsername(rs.getString("username"));
            u.setPass(rs.getString("pass"));
            u.setTele(rs.getString("tele"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
    public static boolean search(String sql,Statement sta) {
        ResultSet rs = null;
        try {
            rs = sta.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void insertMember(users user,Statement sta) {
        if (!Find(user.getUsername(), "username",sta)) {
            updata(user.getInsertSql(),sta);
        } else {
            String sql = user.getUpdateSql();
            updata(sql,sta);
        }

    }

    public static boolean Find(String value, String column,Statement sta) {
        String sql = " select * from users where " + column + "='" + value + "'";
        return search(sql,sta);
    }

    public static void Delete(String name, String table,Statement sta) {
        StringBuffer sb = (new StringBuffer()).append("delete from ");
        sb.append(table).append(" where username like '").append(name).append("'");
        updata(sb.toString(),sta);
    }

    public static void Drop(String w,Statement sta) {
        String sql = "drop table if exists " + w;
        updata(sql,sta);
    }

}

