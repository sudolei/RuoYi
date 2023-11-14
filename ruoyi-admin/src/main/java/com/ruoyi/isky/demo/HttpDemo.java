package com.ruoyi.isky.demo;

import com.ruoyi.common.isky.HttpClientUtils;
import net.sf.json.JSONObject;

public class HttpDemo {
    public static void main(String args[]){
        String url="https://shankapi.ifeng.com/feedflow/getVideoAuthUrl/5c69fbe3-3b37-449d-ace4-1b2be6b4e26d/getVideoAuthPath?callback=getVideoAuthPath";
        String result = HttpClientUtils.get(url);
//        JSONObject jsonObject = JSONObject.fromObject(result.replaceAll("getVideoAuthUrl",""));
        String sbResult=result.replaceAll("getVideoAuthPath","");
        String enResult=sbResult.substring(1,sbResult.length()-1);
        JSONObject jsonObject = JSONObject.fromObject(enResult);
        System.out.println(enResult);
        System.out.println(jsonObject.getJSONObject("data").getString("authUrl"));
    }
}
