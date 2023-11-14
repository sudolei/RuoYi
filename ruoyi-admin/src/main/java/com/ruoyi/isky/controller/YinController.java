package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.isky.domain.FigureYzfrom;
import com.ruoyi.isky.service.IFigureYzfromService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping({"/yin"})
public class YinController extends BaseController {
    private String prefix = "isky/yin";
    @Autowired
    private IFigureYzfromService iFigureYzfromService;

    @GetMapping({"/index"})
    public String index(Integer userid, ModelMap mmap) {
        System.out.println(userid);
        mmap.put("userid", userid);
        if (userid == 1) {
            return this.prefix + "/duForm";
        } else {
            return this.prefix + "/yinForm";
        }
    }

    @GetMapping({"/yinResult"})
    public String yinResult() {
        return this.prefix + "/suucess";
    }

    @Log(title = "保存印数", businessType = BusinessType.OTHER)
    @PostMapping({"/saveYinDu"})
    @ResponseBody
    public AjaxResult saveYinDu(HttpServletRequest request, String mydatas, Integer userid) {
        mydatas = StringEscapeUtils.unescapeHtml(mydatas);
        mydatas = mydatas.replaceAll("\\\\", "");
        JSONObject jsonObject = JSONObject.fromObject(mydatas);
        Integer c8 = jsonObject.getInt("c8");
        Integer c5 = jsonObject.getInt("c5");
        Integer c4 = jsonObject.getInt("c4");
        Integer c2 = jsonObject.getInt("c2");
        Integer c1 = jsonObject.getInt("c1");

        Integer lc8 = jsonObject.getInt("lc8");
        Integer lc5 = jsonObject.getInt("lc5");
        Integer lc4 = jsonObject.getInt("lc4");
        Integer lc2 = jsonObject.getInt("lc2");
        Integer lc1 = jsonObject.getInt("lc1");


        Integer c8qj = jsonObject.getInt("c8qj");
        Integer c5qj = jsonObject.getInt("c5qj");
        Integer c4qj = jsonObject.getInt("c4qj");
        Integer c2qj = jsonObject.getInt("c2qj");
        Integer c1qj = jsonObject.getInt("c1qj");

        Integer c8kz = jsonObject.getInt("c8kz");
        Integer c5kz = jsonObject.getInt("c5kz");
        Integer c4kz = jsonObject.getInt("c4kz");
        Integer c2kz = jsonObject.getInt("c2kz");
        Integer c1kz = jsonObject.getInt("c1kz");

        Integer c8ys = jsonObject.getInt("c8ys");
        Integer c5ys = jsonObject.getInt("c5ys");
        Integer c4ys = jsonObject.getInt("c4ys");
        Integer c2ys = jsonObject.getInt("c2ys");
        Integer c1ys = jsonObject.getInt("c1ys");


        Integer zys = jsonObject.getInt("zys");
        Integer zysd = jsonObject.getInt("zysd");
        Integer zyss = jsonObject.getInt("zyss");
        Integer wys = jsonObject.getInt("wys");
        Integer wysd = jsonObject.getInt("wysd");
        Integer wyss = jsonObject.getInt("wyss");
        Integer zds = jsonObject.getInt("zds");
        Integer wds = jsonObject.getInt("wds");

        Integer bk = jsonObject.getInt("bk");
        Integer xk= jsonObject.getInt("xk");
        Integer lk = jsonObject.getInt("lk");
        Integer xg = jsonObject.getInt("xg");
        Integer gc = jsonObject.getInt("gc");
        Integer gm = jsonObject.getInt("gm");
        Integer yf= jsonObject.getInt("yf");
        Integer tb = jsonObject.getInt("tb");
        Integer zg = jsonObject.getInt("zg");
        Integer cz = jsonObject.getInt("cz");
        Integer mtx = jsonObject.getInt("mtx");

        FigureYzfrom yzfrom = new FigureYzfrom();
        yzfrom.setUserId(userid);
        yzfrom.setC1(c1);
        yzfrom.setC2(c2);
        yzfrom.setC4(c4);
        yzfrom.setC5(c5);
        yzfrom.setC8(c8);
        yzfrom.setCall(c1 + c2 + c4 + c5 + c8);


        yzfrom.setLc1(lc1);
        yzfrom.setLc2(lc2);
        yzfrom.setLc4(lc4);
        yzfrom.setLc5(lc5);
        yzfrom.setLc8(lc8);

        yzfrom.setC8qj(c8qj);
        yzfrom.setC5qj(c5qj);
        yzfrom.setC4qj(c4qj);
        yzfrom.setC2qj(c2qj);
        yzfrom.setC1qj(c1qj);
        yzfrom.setCallqj(c1qj + c2qj + c4qj + c5qj + c8qj);

        yzfrom.setC8kz(c8kz);
        yzfrom.setC5kz(c5kz);
        yzfrom.setC4kz(c4kz);
        yzfrom.setC2kz(c2kz);
        yzfrom.setC1kz(c1kz);
        yzfrom.setCallkz(c1kz + c2kz + c4kz + c5kz + c8kz);

        yzfrom.setC8ys(c8ys);
        yzfrom.setC5ys(c5ys);
        yzfrom.setC4ys(c4ys);
        yzfrom.setC2ys(c2ys);
        yzfrom.setC1ys(c1ys);
        yzfrom.setCallys(c1ys + c2ys + c4ys + c5ys + c8ys);


        yzfrom.setC8f(c8qj + c8kz + c8ys);
        yzfrom.setC5f(c5qj + c5kz + c5ys);
        yzfrom.setC4f(c4qj + c4kz + c4ys);
        yzfrom.setC2f(c2qj + c2kz + c2ys);
        yzfrom.setC1f(c1qj + c1kz + c1ys);
        yzfrom.setCallf(yzfrom.getCallqj() + yzfrom.getCallkz() +   yzfrom.getCallys());

        yzfrom.setZys(zys);
        yzfrom.setZysd(zysd);
        yzfrom.setZyss(zyss);
        yzfrom.setWys(wys);
        yzfrom.setWysd(wysd);
        yzfrom.setWyss(wyss);
        yzfrom.setZds(zds);
        yzfrom.setWds(wds);


        yzfrom.setBk(bk);
        yzfrom.setXk(xk);
        yzfrom.setLk(lk);
        yzfrom.setZg(zg);
        yzfrom.setXg(xg);
        yzfrom.setGc(gc);
        yzfrom.setGm(gm);
        yzfrom.setYf(yf);
        yzfrom.setTb(tb);
        yzfrom.setMtx(mtx);
        yzfrom.setCz(cz);
        yzfrom.setCreateDate(new Date());
        iFigureYzfromService.insertFigureYzfrom(yzfrom);
        return success("1");
    }

    @Log(title = "保存印数", businessType = BusinessType.OTHER)
    @PostMapping({"/saveYin"})
    @ResponseBody
    public AjaxResult saveYin(HttpServletRequest request, String mydatas, Integer userid) {
        mydatas = StringEscapeUtils.unescapeHtml(mydatas);
        mydatas = mydatas.replaceAll("\\\\", "");
        JSONObject jsonObject = JSONObject.fromObject(mydatas);
        Integer c8 = jsonObject.getInt("c8");
        Integer c5 = jsonObject.getInt("c5");
        Integer c4 = jsonObject.getInt("c4");
        Integer c2 = jsonObject.getInt("c2");
        Integer c1 = jsonObject.getInt("c1");

        Integer c8f = jsonObject.getInt("c8f");
        Integer c5f= jsonObject.getInt("c5f");
        Integer c4f = jsonObject.getInt("c4f");
        Integer c2f = jsonObject.getInt("c2f");
        Integer c1f = jsonObject.getInt("c1f");


        Integer bk = jsonObject.getInt("bk");
        Integer xk= jsonObject.getInt("xk");
        Integer lk = jsonObject.getInt("lk");
        Integer xg = jsonObject.getInt("xg");
        Integer gc = jsonObject.getInt("gc");
        Integer gm = jsonObject.getInt("gm");
        Integer yf= jsonObject.getInt("yf");
        Integer tb = jsonObject.getInt("tb");
        Integer zg = jsonObject.getInt("zg");
        Integer cz = jsonObject.getInt("cz");
        Integer mtx = jsonObject.getInt("mtx");


        FigureYzfrom yzfrom = new FigureYzfrom();
        yzfrom.setUserId(userid);
        yzfrom.setC1(c1);
        yzfrom.setC2(c2);
        yzfrom.setC4(c4);
        yzfrom.setC5(c5);
        yzfrom.setC8(c8);
        yzfrom.setCall(c1+c2+c4+c5+c8);

        yzfrom.setC8f(c8f);
        yzfrom.setC5f(c5f);
        yzfrom.setC4f(c4f);
        yzfrom.setC2f(c2f);
        yzfrom.setC1f(c1f);
        yzfrom.setCallf(c8f+c5f+c4f+c2f+c1f);


        yzfrom.setBk(bk);
        yzfrom.setXk(xk);
        yzfrom.setLk(lk);
        yzfrom.setZg(zg);
        yzfrom.setXg(xg);
        yzfrom.setGc(gc);
        yzfrom.setGm(gm);
        yzfrom.setYf(yf);
        yzfrom.setTb(tb);
        yzfrom.setCz(cz);
        yzfrom.setMtx(mtx);
        yzfrom.setCreateDate(new Date());



        iFigureYzfromService.insertFigureYzfrom(yzfrom);
        return success("1");
    }


    @Log(title = "查询验证", businessType = BusinessType.OTHER)
    @PostMapping({"/queryValidate"})
    @ResponseBody
    public AjaxResult queryValidate(String userid) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        FigureYzfrom yzfrom = new FigureYzfrom();
        yzfrom.setUserId(Integer.parseInt(userid));
        yzfrom.setCtime(sdf.format(new Date()));
        boolean flag = false;
        List<FigureYzfrom> list = iFigureYzfromService.selectFigureYzfromList(yzfrom);
        if (list != null && list.size() > 0) {
            flag = false;
        } else {
            flag = true;
        }

        AjaxResult ajax = AjaxResult.success();
        ajax.put("isflag", flag);
        return ajax;
    }
}
