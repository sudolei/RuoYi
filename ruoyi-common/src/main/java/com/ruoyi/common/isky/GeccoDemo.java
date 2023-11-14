package com.ruoyi.common.isky;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.spider.HtmlBean;
@Gecco(matchUrl = "http://jandan.net/pic/page-{page}",pipelines = "GeccoDemo")
public class GeccoDemo  implements HtmlBean{

}
