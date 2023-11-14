package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.isky.domain.ProcessBegin;
import com.ruoyi.isky.service.IProcessBeginService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * 流程订单 信息操作处理
 *
 * @author sunlei
 * @date 2019-09-30
 */
@Controller
@RequestMapping("/isky/processBegin")
public class ProcessBeginController extends BaseController {
    private String prefix = "isky/processBegin";

    @Autowired
    private IProcessBeginService processBeginService;

    @RequiresPermissions("isky:processBegin:view")
    @GetMapping()
    public String processBegin() {
        return prefix + "/processBegin";
    }

    /**
     * 查询流程订单列表
     */
    @RequiresPermissions("isky:processBegin:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProcessBegin processBegin) {
        startPage();
        List<ProcessBegin> list = processBeginService.selectProcessBeginList(processBegin);
        return getDataTable(list);
    }


    /**
     * 导出流程订单列表
     */
    @RequiresPermissions("isky:processBegin:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProcessBegin processBegin) {
        List<ProcessBegin> list = processBeginService.selectProcessBeginList(processBegin);
        ExcelUtil<ProcessBegin> util = new ExcelUtil<ProcessBegin>(ProcessBegin.class);
        return util.exportExcel(list, "processBegin");
    }

    /**
     * 新增流程订单
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存流程订单
     */
    @RequiresPermissions("isky:processBegin:add")
    @Log(title = "流程订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProcessBegin processBegin) {
        return toAjax(processBeginService.insertProcessBegin(processBegin));
    }

    /**
     * 修改流程订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ProcessBegin processBegin = processBeginService.selectProcessBeginById(id);
        mmap.put("processBegin", processBegin);
        return prefix + "/edit";
    }

    /**
     * 修改保存流程订单
     */
    @RequiresPermissions("isky:processBegin:edit")
    @Log(title = "流程订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProcessBegin processBegin) {
        return toAjax(processBeginService.updateProcessBegin(processBegin));
    }

    /**
     * 删除流程订单
     */
    @RequiresPermissions("isky:processBegin:remove")
    @Log(title = "流程订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(processBeginService.deleteProcessBeginByIds(ids));
    }


    @PostMapping("/exportXls")
    @ResponseBody
    public AjaxResult exportXls() {
        ProcessBegin v = new ProcessBegin();
        List<ProcessBegin> list = processBeginService.selectProcessBeginList(v);
        String xlsName = UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        String path = Global.getDownloadPath()+ xlsName;
        createXls(path, list);
        return success(xlsName);
    }


    public void createXls(String path, List<ProcessBegin> list) {
        File pathFile = new File(path);
        if (pathFile.exists()) {
            pathFile.delete();
        }
        //创建一个HSSF,对应一个excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = workbook.createSheet("数据表");
        //在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        //创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = workbook.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("订单名称");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("确认信息");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("货款金额");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("支付金额");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("交货日期");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("客户");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("订单状态");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("内页");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("内页制作时间");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("内页完成时间");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("底座");
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue("底座制作时间");
        cell.setCellStyle(style);
        cell = row.createCell(12);
        cell.setCellValue("底座完成时间");
        cell.setCellStyle(style);
        cell = row.createCell(13);
        cell.setCellValue("包装");
        cell.setCellStyle(style);
        cell = row.createCell(14);
        cell.setCellValue("包装制作时间");
        cell.setCellStyle(style);
        cell = row.createCell(15);
        cell.setCellValue("包装完成时间");
        cell.setCellStyle(style);
        cell = row.createCell(16);
        cell.setCellValue("烫印");
        cell.setCellStyle(style);
        cell = row.createCell(17);
        cell.setCellValue("烫印制作时间");
        cell.setCellStyle(style);
        cell = row.createCell(18);
        cell.setCellValue("烫印完成时间");
        cell.setCellStyle(style);
        cell = row.createCell(19);
        cell.setCellValue("雕刻");
        cell.setCellStyle(style);
        cell = row.createCell(20);
        cell.setCellValue("雕刻制作时间");
        cell.setCellStyle(style);
        cell = row.createCell(21);
        cell.setCellValue("雕刻完成时间");
        cell.setCellStyle(style);
        cell = row.createCell(22);
        cell.setCellValue("订单创建日期");
        cell.setCellStyle(style);
        int i = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (list != null && list.size() > 0) {
            for (ProcessBegin v : list) {
                String s = sdf.format(v.getCreateDate());
                //状态 0未开始 1制作中 2完成
                String state = "";
                switch (v.getState()) {
                    case "0":
                        state = "未开始";
                    case "1":
                        state = "制作中";
                    case "2":
                        state = "完成";
                    default:
                        state = "未开始";

                }
                row = sheet.createRow((int) i + 1);
                //     Student stu = (Student) list.get(i);
                //创建单元格，并设置值
                row.createCell(0).setCellValue(v.getOrderName());
                row.createCell(1).setCellValue(v.getConfirm());
                row.createCell(2).setCellValue(v.getLoanAmount());
                row.createCell(3).setCellValue(v.getPayAmout());
                row.createCell(4).setCellValue(v.getDeliveryTime());
                row.createCell(5).setCellValue(v.getCustomerInfo());
                row.createCell(6).setCellValue(state);
                row.createCell(7).setCellValue(v.getPages());
                row.createCell(8).setCellValue(v.getPagesZzDate());
                row.createCell(9).setCellValue(v.getPagesWcDate());
                row.createCell(10).setCellValue(v.getBase());
                row.createCell(11).setCellValue(v.getBaseZzDate());
                row.createCell(12).setCellValue(v.getBaseWcDate());
                row.createCell(13).setCellValue(v.getPack());
                row.createCell(14).setCellValue(v.getPackZzDate());
                row.createCell(15).setCellValue(v.getPackWcDate());
                row.createCell(16).setCellValue(v.getThermoprint());
                row.createCell(17).setCellValue(v.getThermoprintZzDate());
                row.createCell(18).setCellValue(v.getThermoprintWcDate());
                row.createCell(19).setCellValue(v.getCarving());
                row.createCell(20).setCellValue(v.getCarvingZzDate());
                row.createCell(21).setCellValue(v.getCarvingWcDate());
                row.createCell(22).setCellValue(s);
                i++;
            }
            try {
                FileOutputStream fout = new FileOutputStream(path);
                workbook.write(fout);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
