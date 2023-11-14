package com.ruoyi.isky.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.isky.service.IFigureYzfromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/zzjs"})
public class YinZzjsController extends BaseController {
    private String prefix = "isky/yin";
    @Autowired
    private IFigureYzfromService iFigureYzfromService;

    @GetMapping({""})
    public String index() {
        return this.prefix + "/jisuan";
    }

}
