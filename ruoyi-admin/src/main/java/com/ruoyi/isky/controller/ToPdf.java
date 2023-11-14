package com.ruoyi.isky.controller;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.ruoyi.common.config.Global;
import com.ruoyi.isky.domain.FigureOrderImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ToPdf
{
    public static void t()
    {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("d:/hello1.pdf"));
            Image jpg = Image.getInstance("d:/1.jpg");
            float fff = 0.24F;
            float w = jpg.getWidth() * fff;
            float h = jpg.getHeight() * fff;
            Rectangle pageSize = new Rectangle(w, h);
            doc.setPageSize(pageSize);
            doc.open();
            for (int i = 1; i < 4; i++) {
                doc.newPage();
                Image jpg1 = Image.getInstance("d:/" + i + ".jpg");
                jpg1.setAlignment(1);
                jpg1.setAbsolutePosition(0.0F, 0.0F);
                float scalePercentage = 24.0F;
                jpg1.scalePercent(scalePercentage);

                doc.add(jpg1);
            }
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void t(String pdfPath, String pdfName, String orderPath, String[] imagePathList, List<FigureOrderImage> list)
    {
        String filePath = Global.getUploadPath() + "pdf/";
        Document doc = new Document();
        try
        {
            String fileurl = orderPath + imagePathList[0].toString();
            File f = new File(orderPath + imagePathList[0].toString());

            if (!f.exists()) {
                fileurl = filePath + imagePathList[0].toString();
            }

            PdfWriter.getInstance(doc, new FileOutputStream(pdfPath + pdfName));
            Image jpg = Image.getInstance(fileurl);

            float fff = 0.24F;

            float w = jpg.getWidth() * fff;
            float h = jpg.getHeight() * fff;
            Rectangle pageSize = new Rectangle(w, h);
            doc.setPageSize(pageSize);
            doc.open();
            for (FigureOrderImage imgs : list) {
                int imgNum = Integer.parseInt(imgs.getImageNum());
                for (int i = 0; i < imgNum; i++) {
                    doc.newPage();
                    Image jpg1 = Image.getInstance(orderPath + imgs.getImageUrl());
                    jpg1.setAlignment(1);
                    jpg1.setAbsolutePosition(0.0F, 0.0F);
                    float scalePercentage = 24.0F;
                    jpg1.scalePercent(scalePercentage);
                    doc.add(jpg1);
                }
            }
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tBook(String pdfPath, String pdfName, String orderPath, String[] imagePathList, List<FigureOrderImage> list)
    {
        String filePath = Global.getUploadPath() + "pdf/";
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        Document doc = new Document();
        try
        {
            String fileurl = orderPath + imagePathList[0].toString();
            File f = new File(orderPath + imagePathList[0].toString());

            if (!f.exists()) {
                fileurl = filePath + imagePathList[0].toString();
            }

            PdfWriter.getInstance(doc, new FileOutputStream(pdfPath + pdfName));
            Image jpg = Image.getInstance(fileurl);

            float fff = 0.24F;

            float w = jpg.getWidth() * fff;
            float h = jpg.getHeight() * fff;
            Rectangle pageSize = new Rectangle(w, h);
            doc.setPageSize(pageSize);
            doc.open();
            for (FigureOrderImage imgs : list) {
                doc.newPage();
                Image jpg1 = Image.getInstance(orderPath + imgs.getImageUrl());
                jpg1.setAlignment(1);
                jpg1.setAbsolutePosition(0.0F, 0.0F);
                float scalePercentage = 24.0F;
                jpg1.scalePercent(scalePercentage);
                doc.add(jpg1);
            }
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tcode(String pdfPath, String pdfName, String orderPath, String[] imagePathList )
    {
        String filePath = Global.getUploadPath() + "pdf/";
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        Document doc = new Document();
        try
        {
            String fileurl = orderPath + imagePathList[0].toString();
            File f = new File(orderPath + imagePathList[0].toString());

            if (!f.exists()) {
                fileurl = filePath + imagePathList[0].toString();
            }

            PdfWriter.getInstance(doc, new FileOutputStream(pdfPath + pdfName));
            Image jpg = Image.getInstance(fileurl);

            float fff = 0.24F;

            float w = jpg.getWidth() * fff;
            float h = jpg.getHeight() * fff;
            Rectangle pageSize = new Rectangle(w, h);
            doc.setPageSize(pageSize);
            doc.open();
            for (String imgs : imagePathList) {
                doc.newPage();
                Image jpg1 = Image.getInstance(orderPath + imgs);
                jpg1.setAlignment(1);
                jpg1.setAbsolutePosition(0.0F, 0.0F);
                float scalePercentage = 24.0F;
                jpg1.scalePercent(scalePercentage);
                doc.add(jpg1);
            }
            doc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPercent(float h, float w)
    {
        int p = 0;
        float p2 = 0.0F;
        if (h > w)
            p2 = 297.0F / h * 100.0F;
        else {
            p2 = 210.0F / w * 100.0F;
        }
        p = Math.round(p2);
        return p;
    }

    public int getPercent2(float h, float w)
    {
        int p = 0;
        float p2 = 0.0F;
        p2 = 530.0F / w * 100.0F;
        p = Math.round(p2);
        return p;
    }

    public static void main(String[] args)
    {
        t();
    }
}