package com.ruoyi.isky.demo;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileDemo {
    public static void  main(String args[]){
        String path = "D:\\profile\\upload\\origina";
        ArrayList<String> fileNameList = new ArrayList<String>();
        try {
            getAllFileName(path,fileNameList,"20190901","20191101");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (String s : fileNameList){
            System.out.println(s);
        }
    }


    /**
     * 获取某个文件夹下的所有文件
     *
     * @param fileNameList 存放文件名称的list
     * @param path 文件夹的路径
     * @return
     */
    public static void getAllFileName(String path,ArrayList<String> fileNameList,String beginTime,String endTime) throws ParseException {
        //ArrayList<String> files = new ArrayList<String>();
        boolean flag = false;
        File file = new File(path);
        File[] tempList = file.listFiles();
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date s = sdf.parse(beginTime);
        Date e = sdf.parse(endTime);


        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {

                long time = tempList[i].lastModified();
                Date fileTimeDate = new Date(time);
                int ii  = fileTimeDate.compareTo(s);
                if(ii>0){
                    ii = fileTimeDate.compareTo(e);
                    if (ii<0){
                        fileNameList.add(tempList[i].getName());
                    }
                }
            }
            if (tempList[i].isDirectory()) {
//              System.out.println("文件夹：" + tempList[i]);
                getAllFileName(tempList[i].getAbsolutePath(),fileNameList,beginTime,endTime);
            }
        }
        return;
    }
}
