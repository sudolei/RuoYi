package com.ruoyi.common.isky;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils
{
    public static String get(String url)
    {
        return get(url, "UTF-8");
    }

    public static String get(String url, String charset)
    {
        HttpGet httpGet = new HttpGet(url);
        return executeRequest(httpGet, charset);
    }

    public static String ajaxGet(String url)
    {
        return ajaxGet(url, "UTF-8");
    }

    public static String ajaxGet(String url, String charset)
    {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
        return executeRequest(httpGet, charset);
    }

    public static String post(String url, Map<String, String> dataMap)
    {
        return post(url, dataMap, "UTF-8");
    }

    public static String post(String url, Map<String, String> dataMap, String charset)
    {
        HttpPost httpPost = new HttpPost(url);
        try {
            if (dataMap != null) {
                List nvps = new ArrayList();
                for (Map.Entry entry : dataMap.entrySet()) {
                    nvps.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, charset);
                formEntity.setContentEncoding(charset);
                httpPost.setEntity(formEntity);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return executeRequest(httpPost, charset);
    }

    public static String ajaxPost(String url, Map<String, String> dataMap)
    {
        return ajaxPost(url, dataMap, "UTF-8");
    }

    public static String ajaxPost(String url, Map<String, String> dataMap, String charset)
    {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
        try {
            if (dataMap != null) {
                List nvps = new ArrayList();
                for (Map.Entry entry : dataMap.entrySet()) {
                    nvps.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, charset);
                formEntity.setContentEncoding(charset);
                httpPost.setEntity(formEntity);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return executeRequest(httpPost, charset);
    }

    public static String ajaxPostJson(String url, String jsonString)
    {
        return ajaxPostJson(url, jsonString, "UTF-8");
    }

    public static String ajaxPostJson(String url, String jsonString, String charset)
    {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");

        StringEntity stringEntity = new StringEntity(jsonString, charset);
        stringEntity.setContentEncoding(charset);
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);

        return executeRequest(httpPost, charset);
    }

    public static String executeRequest(HttpUriRequest httpRequest)
    {
        return executeRequest(httpRequest, "UTF-8");
    }

    public static String executeRequest(HttpUriRequest httpRequest, String charset)
    {
        CloseableHttpClient httpclient;
        if ("https".equals(httpRequest.getURI().getScheme()))
            httpclient = createSSLInsecureClient();
        else {
            httpclient = HttpClients.createDefault();
        }
        String result = "";
        try {
            try {
                CloseableHttpResponse response = httpclient.execute(httpRequest);
                HttpEntity entity = null;
                try {
                    entity = response.getEntity();
                    result = EntityUtils.toString(entity, charset);
                } finally {
                    EntityUtils.consume(entity);
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static CloseableHttpClient createSSLInsecureClient()
    {
        try
        {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(new TrustStrategy()
            {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier()
            {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
    }
}