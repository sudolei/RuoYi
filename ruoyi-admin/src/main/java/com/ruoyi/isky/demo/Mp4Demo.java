package com.ruoyi.isky.demo;

import com.ruoyi.common.isky.HttpClientUtils;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Mp4Demo {
    public static void main(String[] args) throws Exception {
        String url = "https://v.ifeng.com/c/7ngRKUESeEy";
        Map m = getInfo(url);
        String guid = m.get("guid").toString();
        String mp4 = m.get("mp4").toString();
        String mp4Info = getMp4Info(guid);
        String mp4Url = mp4+"?" + mp4Info;
        downMp4(mp4Url);
    }


    public static void downMp4(String url) {
        HttpURLConnection con;
        FileOutputStream fs = null;
        InputStream is;
        BufferedInputStream bs = null;
        File file = new File("D:/MP4/" + new Date().getTime() + ".mp4");
        //创建文件
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
            //输入流
            is = con.getInputStream();
            bs = new BufferedInputStream(is);
            //outStream
            fs = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int line;
            while ((line = bs.read(bytes)) != -1) {
                fs.write(bytes, 0, line);
                fs.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //close
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bs != null) {
                try {
                    bs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Map getInfo(String url) {
        String str = HttpClientUtils.get(url);
        int intIndex = str.indexOf("videoPlayUrl");
        String guid = null, mp4String = null;
        if (intIndex == -1) {
            System.out.println("没有找到字符串 videoPlayUrl");
        } else {
            mp4String = str.substring(intIndex + 15, intIndex + 15 + 100);
            intIndex = mp4String.indexOf("mp4");
            mp4String = mp4String.substring(0, intIndex + 3);
            intIndex = str.indexOf("guid");
            guid = str.substring(intIndex + 7, intIndex + 100);
            intIndex = str.indexOf("\"");
            guid = guid.substring(0, intIndex);
            System.out.println(guid);
            System.out.println(mp4String);
        }
        Map m = new HashMap();
        m.put("guid", guid);
        m.put("mp4", mp4String);
        return m;
    }


    public static String getMp4Info(String vid) {
        String url = "https://shankapi.ifeng.com/feedflow/getVideoAuthUrl/" + vid + "/getVideoAuthPath?callback=getVideoAuthPath";
        String result = HttpClientUtils.get(url);
        String sbResult = result.replaceAll("getVideoAuthPath", "");
        String enResult = sbResult.substring(1, sbResult.length() - 1);
        JSONObject jsonObject = JSONObject.fromObject(enResult);
        System.out.println(enResult);
        System.out.println(jsonObject.getJSONObject("data").getString("authUrl"));
        String authUrl = jsonObject.getJSONObject("data").getString("authUrl");
        return authUrl;
    }
}
