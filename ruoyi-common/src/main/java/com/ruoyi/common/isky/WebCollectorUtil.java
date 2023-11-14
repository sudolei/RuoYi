package com.ruoyi.common.isky;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

import java.io.File;
import java.util.regex.Pattern;

public class WebCollectorUtil extends BreadthCrawler {


    /**
     * 构造一个基于伯克利DB的爬虫
     * 伯克利DB文件夹为crawlPath，crawlPath中维护了历史URL等信息
     * 不同任务不要使用相同的crawlPath
     * 两个使用相同crawlPath的爬虫并行爬取会产生错误
     *
     * @param crawlPath 伯克利DB使用的文件夹
     * @param autoParse 是否根据设置的正则自动探测新URL
     */
    public WebCollectorUtil(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        if (!Pattern.matches(".*jpg$", page.url())) {
            return;
        }
        String hh = "D:/profile/upload/dpic/";
        String fileName = IskyUtil.uuid() + ".jpg";
        File f = new File(hh);
        if (!f.exists()) {
            f.mkdirs();
        }
        IskyUtil.readBin2Image(page.content(), hh, fileName);

    }

    public static void main(String[] args) throws Exception {
        WebCollectorUtil crawler = new WebCollectorUtil("crawl", true);
               /*URL信息存放路径*/
        crawler.addSeed("http://jandan.net/pic");
        crawler.addRegex("http://wx2.sinaimg.cn/large/.*");
        crawler.start(5);
    }


}
