package com.ruoyi.isky.demo;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class IOdemo {
    public static void main(String args[]){
        String url="http://shmmsns.qpic.cn/mmsns/BuHHI7wEppvTicTHyEHE5ZumhmXR7Fb6ia01oygl1iaSVicpE49SRyepsR1yxWicaclzhtNcVWb7kaOc/0";
        String url2 = "http%3A%2F%2Fshmmsns.qpic.cn%2Fmmsns%2FBuHHI7wEppvTicTHyEHE5ZumhmXR7Fb6ia01oygl1iaSVicpE49SRyepsR1yxWicaclzhtNcVWb7kaOc%2F0";
        InputStream is =  getInputStreamByUrl(url2);
        byte[] buffer = new byte[0];
        try {
            buffer = new byte[is.available()];
            is.read(buffer);
            File targetFile = new File("d://aaa.jpg");
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InputStream getInputStreamByUrl(String strUrl){
        HttpURLConnection conn = null;
        try {
            URL url = new URL(strUrl);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20 * 1000);
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            IOUtils.copy(conn.getInputStream(),output);
            return  new ByteArrayInputStream(output.toByteArray());
        } catch (Exception e) {
        }finally {
            try{
                if (conn != null) {
                    conn.disconnect();
                }
            }catch (Exception e){
            }
        }
        return null;
    }
}
