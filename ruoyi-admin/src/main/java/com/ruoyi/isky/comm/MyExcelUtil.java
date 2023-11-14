package com.ruoyi.isky.comm;

import com.ruoyi.isky.domain.FigureYzfrom;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.OutputStream;
import java.util.List;

public class MyExcelUtil {

    public static void exportYzFromExcel(HSSFWorkbook workbook, List<FigureYzfrom> yzfromList, OutputStream out, String ctime, int sheetNum, String sheetTitle) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        HSSFRow titleRow = sheet.createRow(0);
        Cell cell0 = titleRow.createCell(0);
        cell0.setCellValue("用户");
        titleRow.createCell(1).setCellValue("4C+4C");
        titleRow.createCell(2).setCellValue("4C+1C");
        titleRow.createCell(3).setCellValue("4C+0");
        titleRow.createCell(4).setCellValue("1C+1C");
        titleRow.createCell(5).setCellValue("1C+0");
        titleRow.createCell(6).setCellValue("总数");
        titleRow.createCell(7).setCellValue(" ");

        titleRow.createCell(8).setCellValue("浪费4C+4C");
        titleRow.createCell(9).setCellValue("浪费4C+1C");
        titleRow.createCell(10).setCellValue("浪费4C+0");
        titleRow.createCell(11).setCellValue("浪费1C+1C");
        titleRow.createCell(12).setCellValue("浪费1C+0");
        titleRow.createCell(13).setCellValue("浪费总数");
        titleRow.createCell(14).setCellValue(" ");
        titleRow.createCell(15).setCellValue("白卡");
        titleRow.createCell(16).setCellValue("哑粉");
        titleRow.createCell(17).setCellValue("高超");
        titleRow.createCell(18).setCellValue("高米");
        titleRow.createCell(19).setCellValue("细格");
        titleRow.createCell(20).setCellValue("珠光");

        titleRow.createCell(21).setCellValue("亮卡");
        titleRow.createCell(22).setCellValue("雪卡");
        titleRow.createCell(23).setCellValue("铜板");
        titleRow.createCell(24).setCellValue("纯质");
        titleRow.createCell(25).setCellValue("满天星");

        int cellnum = titleRow.getLastCellNum();
        for (int a = 0; a < cellnum; a++) {
            titleRow.getCell(a).setCellStyle(style);
        }

        int i = 0;
        FigureYzfrom firstYzform = null;
        for (FigureYzfrom yzfrom : yzfromList) {
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            Integer userid = yzfrom.getUserId();
            String userName = getValue(userid);
            if (userid == 1) {
                firstYzform = yzfrom;
            }
            dataRow.createCell(0).setCellValue(userName);
            dataRow.createCell(1).setCellValue(yzfrom.getC8());
            dataRow.createCell(2).setCellValue(yzfrom.getC5());
            dataRow.createCell(3).setCellValue(yzfrom.getC4());
            dataRow.createCell(4).setCellValue(yzfrom.getC2());
            dataRow.createCell(5).setCellValue(yzfrom.getC1());
            dataRow.createCell(6).setCellValue(yzfrom.getCall());

            dataRow.createCell(7).setCellValue(" ");

            dataRow.createCell(8).setCellValue(yzfrom.getC8f());
            dataRow.createCell(9).setCellValue(yzfrom.getC5f());
            dataRow.createCell(10).setCellValue(yzfrom.getC4f());
            dataRow.createCell(11).setCellValue(yzfrom.getC2f());
            dataRow.createCell(12).setCellValue(yzfrom.getC1f());
            dataRow.createCell(13).setCellValue(yzfrom.getCallf());
            dataRow.createCell(14).setCellValue(" ");
            dataRow.createCell(15).setCellValue(yzfrom.getBk() == null ? 0 : yzfrom.getBk());
            dataRow.createCell(16).setCellValue(yzfrom.getYf() == null ? 0 : yzfrom.getYf());
            dataRow.createCell(17).setCellValue(yzfrom.getGc() == null ? 0 : yzfrom.getGc());
            dataRow.createCell(18).setCellValue(yzfrom.getGm() == null ? 0 : yzfrom.getGm());
            dataRow.createCell(19).setCellValue(yzfrom.getXg() == null ? 0 : yzfrom.getXg());
            dataRow.createCell(20).setCellValue(yzfrom.getZg() == null ? 0 : yzfrom.getZg());
            dataRow.createCell(21).setCellValue(yzfrom.getLk() == null ? 0 : yzfrom.getLk());
            dataRow.createCell(22).setCellValue(yzfrom.getXk() == null ? 0 : yzfrom.getXk());
            dataRow.createCell(23).setCellValue(yzfrom.getTb() == null ? 0 : yzfrom.getTb());
            dataRow.createCell(24).setCellValue(yzfrom.getCz() == null ? 0 : yzfrom.getCz());
            dataRow.createCell(25).setCellValue(yzfrom.getMtx() == null ? 0 : yzfrom.getMtx());
        }

        if (firstYzform != null) {
            HSSFRow titleRow1 = sheet.createRow(sheet.getLastRowNum() + 2);
            titleRow1.createCell(0).setCellValue("卡纸浪费4C+4C");
            titleRow1.createCell(1).setCellValue("卡纸浪费4C+1C");
            titleRow1.createCell(2).setCellValue("卡纸浪费4C+0");
            titleRow1.createCell(3).setCellValue("卡纸浪费1C+1C");
            titleRow1.createCell(4).setCellValue("卡纸浪费1C+0");
            titleRow1.createCell(5).setCellValue("卡纸浪费总数");

            titleRow1.createCell(6).setCellValue("清洁纸浪费4C+4C");
            titleRow1.createCell(7).setCellValue("清洁纸浪费4C+1C");
            titleRow1.createCell(8).setCellValue("清洁纸浪费4C+0");
            titleRow1.createCell(9).setCellValue("清洁纸浪费1C+1C");
            titleRow1.createCell(10).setCellValue("清洁纸浪费1C+0");
            titleRow1.createCell(11).setCellValue("清洁纸浪费总数");

            titleRow1.createCell(12).setCellValue("印刷浪费4C+4C");
            titleRow1.createCell(13).setCellValue("印刷浪费4C+1C");
            titleRow1.createCell(14).setCellValue("印刷浪费4C+0");
            titleRow1.createCell(15).setCellValue("印刷浪费1C+1C");
            titleRow1.createCell(16).setCellValue("印刷浪费1C+0");
            titleRow1.createCell(17).setCellValue("印刷浪费总数");

            int titleRow1cellnum = titleRow1.getLastCellNum();
            for (int a = 0; a < titleRow1cellnum; a++) {
                titleRow1.getCell(a).setCellStyle(style);
            }


            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(firstYzform.getC8kz() == null ? 0 : firstYzform.getC8kz());
            dataRow.createCell(1).setCellValue(firstYzform.getC5kz() == null ? 0 : firstYzform.getC5kz());
            dataRow.createCell(2).setCellValue(firstYzform.getC4kz() == null ? 0 : firstYzform.getC4kz());
            dataRow.createCell(3).setCellValue(firstYzform.getC2kz() == null ? 0 : firstYzform.getC2kz());
            dataRow.createCell(4).setCellValue(firstYzform.getC1kz() == null ? 0 : firstYzform.getC1kz());
            dataRow.createCell(5).setCellValue(firstYzform.getCallkz() == null ? 0 : firstYzform.getCallkz());
            dataRow.createCell(6).setCellValue(firstYzform.getC8qj() == null ? 0 : firstYzform.getC8qj());
            dataRow.createCell(7).setCellValue(firstYzform.getC5qj() == null ? 0 : firstYzform.getC5qj());
            dataRow.createCell(8).setCellValue(firstYzform.getC4qj() == null ? 0 : firstYzform.getC4qj());
            dataRow.createCell(9).setCellValue(firstYzform.getC2qj() == null ? 0 : firstYzform.getC2qj());
            dataRow.createCell(10).setCellValue(firstYzform.getC1qj() == null ? 0 : firstYzform.getC1qj());
            dataRow.createCell(11).setCellValue(firstYzform.getCallqj() == null ? 0 : firstYzform.getCallqj());
            dataRow.createCell(12).setCellValue(firstYzform.getC8ys() == null ? 0 : firstYzform.getC8ys());
            dataRow.createCell(13).setCellValue(firstYzform.getC5ys() == null ? 0 : firstYzform.getC5ys());
            dataRow.createCell(14).setCellValue(firstYzform.getC4ys() == null ? 0 : firstYzform.getC4ys());
            dataRow.createCell(15).setCellValue(firstYzform.getC2ys() == null ? 0 : firstYzform.getC2ys());
            dataRow.createCell(16).setCellValue(firstYzform.getC1ys() == null ? 0 : firstYzform.getC1ys());
            dataRow.createCell(17).setCellValue(firstYzform.getCallys() == null ? 0 : firstYzform.getCallys());


            HSSFRow titleRow2 = sheet.createRow(sheet.getLastRowNum() + 2);
            titleRow2.createCell(0).setCellValue("早印数总");
            titleRow2.createCell(1).setCellValue("双");
            titleRow2.createCell(2).setCellValue("单");
            titleRow2.createCell(3).setCellValue("晚印数总");
            titleRow2.createCell(4).setCellValue("双");
            titleRow2.createCell(5).setCellValue("单");
            int titleRow2cellnum = titleRow2.getLastCellNum();
            for (int a = 0; a < titleRow2cellnum; a++) {
                titleRow2.getCell(a).setCellStyle(style);
            }

            HSSFRow dataRow1 = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow1.createCell(0).setCellValue(firstYzform.getZys() == null ? 0 : firstYzform.getZys());
            dataRow1.createCell(1).setCellValue(firstYzform.getZyss() == null ? 0 : firstYzform.getZyss());
            dataRow1.createCell(2).setCellValue(firstYzform.getZysd() == null ? 0 : firstYzform.getZysd());
            dataRow1.createCell(3).setCellValue(firstYzform.getWys() == null ? 0 : firstYzform.getWys());
            dataRow1.createCell(4).setCellValue(firstYzform.getWyss() == null ? 0 : firstYzform.getWyss());
            dataRow1.createCell(5).setCellValue(firstYzform.getWysd() == null ? 0 : firstYzform.getWysd());

            HSSFRow titleRow3 = sheet.createRow(sheet.getLastRowNum() + 2);
            titleRow3.createCell(0).setCellValue("早电");
            titleRow3.createCell(1).setCellValue("晚电");
            int titleRow3cellnum = titleRow3.getLastCellNum();
            for (int a = 0; a < titleRow3cellnum; a++) {
                titleRow3.getCell(a).setCellStyle(style);
            }
            HSSFRow dataRow3 = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow3.createCell(0).setCellValue(firstYzform.getZds() == null ? 0 : firstYzform.getZds());
            dataRow3.createCell(1).setCellValue(firstYzform.getWds() == null ? 0 : firstYzform.getWds());


            HSSFRow titleRow4 = sheet.createRow(sheet.getLastRowNum() + 2);
            titleRow4.createCell(0).setCellValue("吕栋4C+4C");
            titleRow4.createCell(1).setCellValue("吕栋4C+1C");
            titleRow4.createCell(2).setCellValue("吕栋4C+1C");
            titleRow4.createCell(3).setCellValue("吕栋1C+1C");
            titleRow4.createCell(4).setCellValue("吕栋1C+0C");
            titleRow4.createCell(5).setCellValue("吕栋总");
            int titleRow4cellnum = titleRow4.getLastCellNum();
            for (int a = 0; a < titleRow4cellnum; a++) {
                titleRow4.getCell(a).setCellStyle(style);
            }
            HSSFRow dataRow4 = sheet.createRow(sheet.getLastRowNum() + 1);
            int lc8 = firstYzform.getLc8() == null ? 0 : firstYzform.getLc8();
            int lc5 = firstYzform.getLc5() == null ? 0 : firstYzform.getLc5();
            int lc4 = firstYzform.getLc4() == null ? 0 : firstYzform.getLc4();
            int lc2 = firstYzform.getLc2() == null ? 0 : firstYzform.getLc2();
            int lc1 = firstYzform.getLc1() == null ? 0 : firstYzform.getLc1();
            dataRow4.createCell(0).setCellValue(lc8);
            dataRow4.createCell(1).setCellValue(lc5);
            dataRow4.createCell(2).setCellValue(lc4);
            dataRow4.createCell(3).setCellValue(lc2);
            dataRow4.createCell(4).setCellValue(lc1);
            dataRow4.createCell(5).setCellValue(lc8 + lc5 + lc4 + lc2 + lc1);
        }
    }


    public static String getValue(Integer userid) {
        String userName = "";
        switch (userid) {
            case 1:
                userName = "杜";
                break;
            case 2:
                userName = "淘宝";
                break;
            case 3:
                userName = "吕栋";
                break;
            case 4:
                userName = "周泽";
                break;
            case 5:
                userName = "曹娜";
                break;
            case 6:
                userName = "孙磊";
                break;
            case 7:
                userName = "生产";
                break;
            case 8:
                userName = "发货";
                break;
            case 9:
                userName = "张楠";
                break;
            default:
                userName = null;
        }
        return userName;
    }


}
