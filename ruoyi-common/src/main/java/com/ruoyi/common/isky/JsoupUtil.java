package com.ruoyi.common.isky;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JsoupUtil {
    public static void main(String args[]){
        JsoupUtil util = new JsoupUtil();
        util.demo1();
    }

    public void demo(){
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
        System.out.println(doc.body().getElementsByTag("p").text());
    }

    public void demo1(){
        Document doc = null;
        try {
            doc = Jsoup.connect("http://jandan.net/pic/page-130").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = doc.title();
        String body = doc.body().html();
        Element e= doc.body().getElementsByClass("commentlist").first();
        Elements elements = e.getElementsByTag("li");
        for(Element e2:elements){
            System.out.println(e2.html());
            break;
        }
//        Elements e = doc.body().getElementsByClass("commentlist");
//        System.out.println(e.html());
    }
}
