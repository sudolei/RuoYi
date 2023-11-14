package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.isky.domain.WarehouseGoods;
import com.ruoyi.isky.service.IWarehouseGoodsService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/jm"})
public class CkqdController extends BaseController {

    private String prefix = "isky/qd";


    @Autowired
    private IWarehouseGoodsService warehouseGoodsService;


    @GetMapping({""})
    public String index(ModelMap mmap) {
        WarehouseGoods goods = new WarehouseGoods();
        goods.setCkIds("qd");
        goods.setParentId(1L);
        List<WarehouseGoods> list = warehouseGoodsService.selectWarehouseGoodsList(goods);

        List<WarehouseGoods> erList = new ArrayList<WarehouseGoods>();
        for (WarehouseGoods g : list) {
            String mobileHref = g.getMobileHref();
            g.setMobileHrefCl("#" + mobileHref);
            WarehouseGoods goodsChild = new WarehouseGoods();
            goodsChild.setParentId(g.getId());
            goodsChild.setCkIds("qd");
            List<WarehouseGoods> glist = warehouseGoodsService.selectWarehouseGoodsList(goodsChild);
            g.setGoodsList(glist);
            erList.addAll(glist);
        }

        for (WarehouseGoods erg : erList) {
            WarehouseGoods goodsChild = new WarehouseGoods();
            goodsChild.setParentId(erg.getId());
            goodsChild.setCkIds("qd");
            List<WarehouseGoods> glist = warehouseGoodsService.selectWarehouseGoodsList(goodsChild);
            erg.setGoodsList(glist);
        }

        mmap.put("goodsList", list);
        mmap.put("erList", erList);
        return this.prefix + "/index";
    }

    @GetMapping({"/add"})
    public String add(ModelMap mmap) {
        WarehouseGoods goods = new WarehouseGoods();
        goods.setParentId(1L);
        goods.setCkIds("qd");
        List<WarehouseGoods> list = warehouseGoodsService.selectWarehouseGoodsList(goods);

        goods = list.get(0);
        WarehouseGoods ergoods = new WarehouseGoods();
        ergoods.setParentId(goods.getId());
        ergoods.setCkIds("qd");
        List<WarehouseGoods> erlist = warehouseGoodsService.selectWarehouseGoodsList(ergoods);


        List<WarehouseGoods> erList = new ArrayList<WarehouseGoods>();
        for (WarehouseGoods g : list) {
            String mobileHref = g.getMobileHref();
            g.setMobileHrefCl("#" + mobileHref);
            WarehouseGoods goodsChild = new WarehouseGoods();
            goodsChild.setParentId(g.getId());
            goodsChild.setCkIds("qd");
            List<WarehouseGoods> glist = warehouseGoodsService.selectWarehouseGoodsList(goodsChild);
            g.setGoodsList(glist);
            erList.addAll(glist);
        }

        for (WarehouseGoods erg : erList) {
            WarehouseGoods goodsChild = new WarehouseGoods();
            goodsChild.setParentId(erg.getParentId());
            goodsChild.setCkIds("qd");
            List<WarehouseGoods> glist = warehouseGoodsService.selectWarehouseGoodsList(goodsChild);
            erg.setGoodsList(glist);
        }


        mmap.put("goodsList", list);
        mmap.put("erlist", erlist);
        mmap.put("erjList", erList);
        return this.prefix + "/add";
    }


    @GetMapping("/editMethod/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        WarehouseGoods goods = new WarehouseGoods();
        goods = warehouseGoodsService.selectWarehouseGoodsById(id);
        mmap.put("goods", goods);
        return this.prefix + "/edit";
    }


    @Log(title = "订单上传", businessType = BusinessType.OTHER)
    @PostMapping({"/editCk"})
    @ResponseBody
    public AjaxResult editCk(String mydatas) {
        try {
            mydatas = StringEscapeUtils.unescapeHtml(mydatas);
            mydatas = mydatas.replaceAll("\\\\", "");
            JSONObject jsonObject = JSONObject.fromObject(mydatas);
            String id = jsonObject.getString("goodId");
            String goodsName = jsonObject.getString("goodsName");
            String warehouseA = "0";
            if (jsonObject.has("warehouseA")) {
                warehouseA = jsonObject.getString("warehouseA");
            }
            String warehouseB = "0";
            if (jsonObject.has("warehouseB")) {
                warehouseB = jsonObject.getString("warehouseB");
            }

            String warehouseC= "0";
            if (jsonObject.has("warehouseC")) {
                warehouseC = jsonObject.getString("warehouseC");
            }

            String warehouseD = "0";
            if (jsonObject.has("warehouseD")) {
                warehouseD = jsonObject.getString("warehouseD");
            }
            Long pid = Long.parseLong(id);
            WarehouseGoods goods =warehouseGoodsService.selectWarehouseGoodsById(pid);
            goods.setGoodsName(goodsName);
            goods.setWarehouseA(Integer.parseInt(warehouseA));
            goods.setWarehouseB(Integer.parseInt(warehouseB));
            goods.setWarehouseC(Integer.parseInt(warehouseC));
            goods.setWarehouseD(Integer.parseInt(warehouseD));
            warehouseGoodsService.updateWarehouseGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success("1");
    }

    @Log(title = "订单上传", businessType = BusinessType.OTHER)
    @PostMapping({"/saveCk"})
    @ResponseBody
    public AjaxResult saveCk(HttpServletRequest request, String mydatas) {
        try {
            String d = request.getParameter("mydatas");
            System.out.println(d);
            mydatas = StringEscapeUtils.unescapeHtml(mydatas);
            mydatas = mydatas.replaceAll("\\\\", "");
            JSONObject jsonObject = JSONObject.fromObject(mydatas);
            String ppid = jsonObject.getString("ppid");
            String parentId = jsonObject.getString("parentId");
            String goodsName = jsonObject.getString("goodsName");


            String warehouseC = "0";
            if (jsonObject.has("warehouseC")) {
                warehouseC = jsonObject.getString("warehouseC");
            }

            Long pid = Long.parseLong(parentId);
            WarehouseGoods goods = new WarehouseGoods();
            goods.setGoodsName(goodsName);
            goods.setWarehouseC(Integer.parseInt(warehouseC));
            goods.setParentId(pid);
            goods.setCkIds("qd");
            goods.setCkNames("青岛,");
            warehouseGoodsService.insertWarehouseGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success("1");
    }
}
