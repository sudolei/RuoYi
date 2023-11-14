package com.ruoyi.isky.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.isky.domain.FigureOne;
import com.ruoyi.isky.service.IFigureOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/begin"})
public class BeginController extends BaseController {
    private String prefix = "isky/begin";
    @Autowired
    private IFigureOneService oneService;

//    @GetMapping({""})
//    public String index() {
//        return this.prefix + "/index";
//    }

    @RequestMapping({"/{qrcode}"})
    public String index(@PathVariable("qrcode") String qrcode, ModelMap mmap) {
        FigureOne one = new FigureOne();
        one.setCodeUuid(qrcode);
        List<FigureOne> ones = oneService.selectFigureOneList(one);
        if (ones != null && ones.size() > 0) {
            one = ones.get(0);
        }
        Integer typeid = Integer.parseInt(one.getType());
        String dir = null;
        switch (typeid) {
            case 1:
                dir = "isky/mxp/login_qr";
                break;
            case 2:
                dir = "isky/vmxp/login_qr";
                break;
            case 3:
                dir = "isky/poker/login_qr";
                break;
            case 4:
                dir = "isky/calendar/login_qr";
                break;
            case 7:
                dir = "isky/calendar_sb/login_qr";
                break;
            case 5:
                dir = "notebook/";
                break;
            case 6:
                dir = "isky/lomo/login_qr";
                break;
            case 8:
                dir = "isky/lomoh/login_qr";
        }
//        return this.prefix + "/index";
        return dir;
    }
}
