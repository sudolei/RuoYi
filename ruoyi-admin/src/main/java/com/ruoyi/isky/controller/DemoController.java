package com.ruoyi.isky.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.isky.IskyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URL;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {
    private String prefix = "isky/demo";


    @GetMapping()
    public String index() {
        return prefix + "/index";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public static void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedInputStream dis = null;
        BufferedOutputStream fos = null;
        String urlString = request.getParameter("url");
        String fileName = IskyUtil.uuid()+".jpg";
        try {
            URL url = new URL(urlString);
            response.reset();//避免空行
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(url.openConnection().getContentLength()));
            dis = new BufferedInputStream(url.openStream());
            fos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = dis.read(buff, 0, buff.length))) {
                fos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (dis != null)
                dis.close();
            if (fos != null)
                fos.close();

        }
    }
}
