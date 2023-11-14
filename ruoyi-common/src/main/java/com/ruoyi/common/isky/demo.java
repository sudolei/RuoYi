package com.ruoyi.common.isky;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class demo extends BreadthCrawler {
    // 下载路径

    File downloadDir;
    AtomicInteger imageId;
    public static String dowloadPath = "D:/profile/upload/dpic/";

    /**
     * 构造一个基于RocksDB的爬虫
     * RocksDB文件夹为crawlPath，crawlPath中维护了历史URL等信息
     * 不同任务不要使用相同的crawlPath
     * 两个使用相同crawlPath的爬虫并行爬取会产生错误
     *
     * @param crawlPath RocksDB使用的文件夹
     * @param autoParse 是否根据设置的正则自动探测新URL
     */
    public demo(String crawlPath, String downloadPath, boolean autoParse) {
        super(crawlPath, autoParse);
        downloadDir = new File(downloadPath);
//    判断是否存在,如果不存在就,执行mkdirs方法
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }
    }

    public static void main(String[] args) throws IOException {
        demo d = new demo("crawlPath", "dowloadPath", false);
        d.addSeed("http://jandan.net/pic/page-132#comments");
        d.addRegex("http://wx2.sinaimg.cn/large/.*");
        d.setResumable(true);
        d.setThreads(30);
        try {
            d.start(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        // 根据http来判断当前的资源是图片还是html
        String contentType = page.contentType();
        // 在判断当前的资源是否为Html
        if (contentType == null) {
            return;
        } else if (contentType.contains("html")) {
            // 如果有图片的,我就拿图片那部分保存在文件中
            Elements imgs = page.select("img[src]");
            // / 然后在 遍历所有html中所有图片
            for (Element img : imgs) {
                String attr = img.attr("abs:src");
                next.add(attr);
            }
        }
        // 在判断如果是起始图片就直接下载
        else if (contentType.startsWith("image")) {
            //进行切割加成名称
            String extensionName = contentType.split("/")[1];
            String imageFileName = IskyUtil.uuid() + "." + extensionName;
            File imageFile = new File(downloadDir, imageFileName);
            try {
                FileUtils.write(imageFile, page.content());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
