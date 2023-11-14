package com.ruoyi.isky.controller;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cci")
public class CciController extends BaseController {
    private String prefix = "isky/cci";
    @GetMapping()
    public String index()
    {
        return prefix + "/index";
    }
}
