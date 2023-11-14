package com.ruoyi.isky.demo;

import java.io.UnsupportedEncodingException;
import java.sql.*;


public class SQLiteDemo {


    private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:G:\\SnsMicroMsg.db";

    public static void main(String args[]) {
        Connection connection = null;
        try {
            connection = createConnection();

            Statement state = connection.createStatement();
            ResultSet rs = state.executeQuery("select * from company;"); //查询数据
            while (rs.next()) { //将查询到的数据打印出来
                System.out.print("snsId = " + rs.getString("snsId") + " "); //列属性一
                System.out.println("userName = " + rs.getString("userName")); //列属性二
                System.out.println("createTime = " + rs.getString("createTime")); //列属性二
                System.out.println("type = " + rs.getString("type")); //列属性二
                System.out.println("content = " + rs.getString("content")); //列属性二
                System.out.println("attrBuf = " + rs.getString("attrBuf")); //列属性二
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    // 创建Sqlite数据库连接
    public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName(Class_Name);
        return DriverManager.getConnection(DB_URL);
    }

    public static void func1(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        // 执行查询语句
        ResultSet rs = statement.executeQuery("select * from snsinfo");
        int i=0;
        while (rs.next()) {
            String userName =  rs.getString("userName");
            byte[] b = rs.getBytes("content");
            try {
                String blobString = new String(b ,"utf-8");
                System.out.println(blobString);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;;
//            if(i>14){
//                break;
//            }
        }
    }
}
