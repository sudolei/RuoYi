package com.ruoyi.isky.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo2 {
    public static void main(String args[]){
//        Date date = new Date(1556164445);
        SimpleDateFormat dd=new SimpleDateFormat("yyyyMMdd");
        String d=dd.format(new Date());
        System.out.print(d);
        int ddd=1556164445;
        try {
            Date date=dd.parse(ddd+"");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
