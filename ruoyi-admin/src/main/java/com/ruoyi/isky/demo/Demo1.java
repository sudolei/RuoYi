package com.ruoyi.isky.demo;

import com.ruoyi.common.isky.HttpClientUtils;

public class Demo1 {
    public static void main(String args[]) {
        String str = HttpClientUtils.get("https://v.ifeng.com/c/7o20GgWfgrG");
        //videoPlayUrl
        int intIndex = str.indexOf("videoPlayUrl");
        if (intIndex == -1) {
            System.out.println("没有找到字符串 Runoob");
        } else {
            String mp4String = str.substring(intIndex + 15, intIndex + 15 + 100);
            intIndex = mp4String.indexOf("mp4");
            mp4String = mp4String.substring(0, intIndex + 3);
            intIndex = str.indexOf("guid");
            String guid = str.substring(intIndex+7, intIndex + 100);
            intIndex = str.indexOf("\"");
            guid = guid.substring(0, intIndex);
            System.out.println(guid);
            System.out.println(mp4String);
        }
    }
}
