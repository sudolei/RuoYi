package com.ruoyi.isky.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.isky.domain.FigureOrderImage;
import com.ruoyi.isky.service.IFigureOrderImageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/share")
public class ShareController extends BaseController {
    private String s_prefix = "isky/share";


    @Autowired
    private IFigureOrderImageService orderImageService;

    @GetMapping({""})
    public String share(HttpServletRequest request, Integer orderId, ModelMap mmap) {

        mmap.put("orderId", orderId);
        return this.s_prefix + "/share";
    }


    @PostMapping({"/getImages"})
    @ResponseBody
    public AjaxResult getImages(HttpServletRequest request, Integer orderId) {
        FigureOrderImage image = new FigureOrderImage();
        image.setOrderId(orderId);
        List<FigureOrderImage> list = orderImageService.selectFigureOrderImageList(image);
        JSONArray jsonArray = new JSONArray();
        for (FigureOrderImage image1 : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", "../profile/upload/rs/" + image1.getImageUrl());
//            jsonObject.put("width",200);
//            jsonObject.put("height",200);
            jsonArray.add(jsonObject);
        }
        return success(jsonArray.toString());
    }
}
