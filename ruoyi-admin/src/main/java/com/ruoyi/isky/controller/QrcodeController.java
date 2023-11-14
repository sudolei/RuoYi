package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.isky.IskyUtil;
import com.ruoyi.common.isky.MyFileUtils;
import com.ruoyi.common.utils.ThumbUtil;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.isky.domain.FigureQrcode;
import com.ruoyi.isky.domain.FigureQrcodeWork;
import com.ruoyi.isky.service.IFigureQrcodeService;
import com.ruoyi.isky.service.IFigureQrcodeWorkService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping({"/qrcode"})
public class QrcodeController extends BaseController {
    private String prefix = "isky/qrcode";

    @Autowired
    private IFigureQrcodeService figureQrcodeService;

    @Autowired
    private IFigureQrcodeWorkService figureQrcodeWorkService;

    @RequestMapping({"/{qrcode}"})
    public String index(@PathVariable("qrcode") String qrcode, ModelMap mmap) {
        FigureQrcode code = new FigureQrcode();
        code.setCodeStr(qrcode);
        List qrcodes = this.figureQrcodeService.selectFigureQrcodeList(code);
        mmap.put("qrcode", qrcode);
        if ((qrcodes != null) && (qrcodes.size() > 0)) {
            code = (FigureQrcode) qrcodes.get(0);
            String codeType = code.getCodeType();
            if (codeType.equals("1")) {
                String codeGenre = code.getCodeGenre();
                FigureQrcodeWork work = new FigureQrcodeWork();
                work.setCodeStr(qrcode);
                List list = this.figureQrcodeWorkService.selectFigureQrcodeWorkList(work);
                mmap.put("code", code);
                mmap.put("works", list);
                if (codeGenre.equals("0")) {
                    return this.prefix + "/qrcode";
                }
                return this.prefix + "/vedio";
            } else if (codeType.equals("3")) {
                mmap.put("code", code);
                return this.prefix + "/vedioDy";
            }
            return this.prefix + "/index";
        }
        return this.prefix + "/nofound";
    }

    @RequestMapping("/hello")
    public String hello() {
        return prefix + "/hllo";
    }


    @Log(title = "订单上传", businessType = BusinessType.OTHER)
    @PostMapping({"/order"})
    @ResponseBody
    public AjaxResult order(String mydatas, String qrcode, String text) {
        mydatas = StringEscapeUtils.unescapeHtml(mydatas);
        mydatas = mydatas.replaceAll("\\\\", "");
        JSONArray jsonArray = JSONArray.fromObject(mydatas);
        FigureQrcodeWork work = null;
        JSONObject o = jsonArray.getJSONObject(0);
        String codeGenre = o.getString("workType");
        int len = jsonArray.size();
        if (codeGenre.equals("1")) {
            len = 1;
        }

        for (int i = 0; i < len; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String fileName = jsonObject.getString("fileName");
            String workType = jsonObject.getString("workType");
            work = new FigureQrcodeWork();
            work.setCodeStr(qrcode);
            work.setWorkType(workType);
            work.setWorkUrl(fileName);
//            work.setWorkUrl("/yxtk" + fileName);
            this.figureQrcodeWorkService.insertFigureQrcodeWork(work);
        }

        FigureQrcode figureQrcode = new FigureQrcode();
        figureQrcode.setCodeStr(qrcode);

        List l = this.figureQrcodeService.selectFigureQrcodeList(figureQrcode);
        figureQrcode = (FigureQrcode) l.get(0);
        figureQrcode.setCodeType("1");
        figureQrcode.setIsUse("1");
        figureQrcode.setCodeGenre(codeGenre);
        figureQrcode.setTextInfo(text);
        this.figureQrcodeService.updateFigureQrcode(figureQrcode);
        return success();
    }

    @GetMapping({"/main"})
    public String main() {
        return this.prefix + "/main";
    }


    @Log(title = "模板下载", businessType = BusinessType.OTHER)
    @GetMapping({"/downZip"})
    public void downZip(String ids, HttpServletRequest request, HttpServletResponse response) {

        String[] idsArray = ids.split(",");
        String uuid = IskyUtil.uuid();
        List fileNameList = new ArrayList();
        for (String str : idsArray) {
            int id = Integer.parseInt(str);
            FigureQrcode qrcode = figureQrcodeService.selectFigureQrcodeById(id);
            String fileName = qrcode.getCodeStr() + ".jpg";
            fileNameList.add(fileName);
            qrcode.setIsPrint("1");
            figureQrcodeService.updateFigureQrcode(qrcode);
        }
        String picturePath = Global.getUploadPath() + "qrcode/";
        String zipDir = Global.getUploadPath() + "zip/";
        File f = new File(zipDir);
        if (!f.exists()) {
            f.mkdirs();
        }
        String zipFileName = uuid + ".zip";
        FileUtils.zipToFiles(picturePath, fileNameList, zipFileName, zipDir);

        String pdfPath = Global.getUploadPath() + "zip/";
        String fileName = pdfPath + uuid + ".zip";
        MyFileUtils.downFile(new File(fileName), request, response);
    }


    @Log(title = "模板下载", businessType = BusinessType.OTHER)
    @GetMapping({"/downPdf"})
    public void downPdf(String ids, HttpServletRequest request, HttpServletResponse response) {
        String oid = newPdf(ids);
        String pdfPath = Global.getUploadPath() + "pdf/";
        String fileName = pdfPath + oid + ".pdf";
        MyFileUtils.downFile(new File(fileName), request, response);
    }


    public String newPdf(String ids) {
        String pdfPath = Global.getUploadPath() + "pdf/";
        String orderPath = Global.getUploadPath();
        String[] osArray = ids.split(",");
        String sstr = "";
        for (String id : osArray) {
            FigureQrcode qrcode = figureQrcodeService.selectFigureQrcodeById(Integer.parseInt(id));
            sstr = sstr + qrcode.getCodeImg() + ",";
        }
        String[] os = sstr.split(",");
        String oid = UUID.randomUUID().toString();
        ToPdf.tcode(pdfPath, oid + ".pdf", orderPath, os);
        return oid;
    }


    @Log(title = "图片上传", businessType = BusinessType.OTHER)
    @PostMapping({"/uploadPicture"})
    @ResponseBody
    public AjaxResult uploadPicture(@RequestParam("myfile") MultipartFile file, String typeid) {
        long start = System.currentTimeMillis();
        String filePath = Global.getUploadPath() + "codework/";
        String contextType = file.getContentType();
        String[] strs = contextType.split("/");
        contextType = strs[0];
        String extension = file.getOriginalFilename().split("\\.")[1];
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();
        }
        String fileName = null;
        String t = "0";
        if (contextType.equals("video")) {
            try {
                fileName = FileUploadUtils.uploadDiy(filePath, file, extension);
                t = "1";
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fileName = FileUploadUtils.upload(filePath, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map zoomProperty = null;
            try {
                zoomProperty = IskyUtil.printImageTags(new File(filePath + fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            int zoomPicWidth = ((Integer) zoomProperty.get("width")).intValue();
            int zoomPicHeight = ((Integer) zoomProperty.get("height")).intValue();
            if (zoomPicWidth > 1000) {
                float zoom = 1000.0F / zoomPicWidth;
                zoomPicWidth = 1000;
                zoomPicHeight = (int) (zoom * zoomPicHeight);
            }
//            Thread thread = new Thread(new editRunble(fileName, filePath, zoomPicWidth, zoomPicHeight));
//            thread.start();
        }


        AjaxResult ajax = AjaxResult.success();
        ajax.put("filePath", "/profile/upload/codework/" + fileName);
        ajax.put("workType", t);
        return ajax;
    }


    class editRunble
            implements Runnable {
        private String fileName;
        private String filePath;
        private int width;
        private int height;

        public editRunble(String fileName, String filePath, int width, int height) {
            this.fileName = fileName;
            this.filePath = filePath;
            this.width = width;
            this.height = height;
        }

        public void run() {
            ThumbUtil.zoomGdccPic(this.fileName, this.filePath + this.fileName, this.width, this.height, this.filePath);
        }
    }
}