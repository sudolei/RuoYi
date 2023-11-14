package com.ruoyi.isky.wx;

import java.sql.*;
import java.util.Set;

public class SqliteTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //dealWeiXinDataOfCircle
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:G:\\SnsMicroMsg.db");
        Statement state = conn.createStatement();
        ResultSet rs = state.executeQuery("select * from snsinfo;"); //查询数据

        String snsid = null;
        String userName = null;
        Long createTime = null;
        Integer type = null;
        String content = null;
        String attrBuf = null;
        int i = 0 ;
        while (rs.next()) { //将查询到的数据打印出来
            snsid = rs.getString("snsId");
            userName = rs.getString("userName");
            createTime = rs.getLong("createTime");
            type = rs.getInt("type");
            content = rs.getString("content");
            attrBuf = rs.getString("attrBuf");
            i++;
            if(i>3){
                break;
            }
            //dealWeiXinDataOfCircle
            WeiXinCircleContent cont = WeiXinUtils.dealWeiXinDataOfCircle(snsid, "iskylei", null, userName, null, null, type, createTime, content, attrBuf);
            System.out.println(cont.getContent());
            Set<String> s = cont.getImgNames();
            for (String str : s) {
                System.out.println("---------------");
                System.out.println(str);
                System.out.println("---------------");
            }
        }
        rs.close();
        conn.close();



    }
}
