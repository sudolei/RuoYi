package com.ruoyi.isky.demo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageDemo {
    public static void main(String[] args) {
        String str = "D:\\profile\\upload\\origina\\2.jpg";


        String desc = "D:\\profile\\upload\\origina\\333_a.jpg";
        createJpg(str,desc);
//        CopyImg(new File(str),new File(desc));
//
//
        File f = new File(desc);
        //方法一
        ImageIcon imageIcon = new ImageIcon(desc);
        int iconWidth = imageIcon.getIconWidth();
        int iconHeight = imageIcon.getIconHeight();
        System.out.println(iconWidth + "," + iconHeight);
//
//        //方法二
//        Image image = imageIcon.getImage();
//        int imageWidth = image.getWidth(imageIcon.getImageObserver());
//        int imageHeight = image.getHeight(imageIcon.getImageObserver());
//        System.out.println(imageWidth + "," + imageHeight);
//
//        //方法三
//        try {
//            BufferedImage bufferedImage = ImageIO.read(new File(desc));
//            int width = bufferedImage.getWidth();
//            int height = bufferedImage.getHeight();
//            System.out.println(width + "," + height);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        try {
//            Iterator readers = ImageIO.getImageReadersByFormatName("jpg");
//            ImageReader reader = (ImageReader) readers.next();
//            ImageInputStream iis = ImageIO.createImageInputStream(f);
//            reader.setInput(iis, true);
//            System.out.println("width:" + reader.getWidth(0));
//            System.out.println("height:" + reader.getHeight(0));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//
//        try {
//            IskyUtil.printImageTags(f);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    public static void CopyImg(File source,File dest) {
        try {
            //读取源地址文件的字节流
            FileInputStream in=new FileInputStream(source);
            FileOutputStream out=new FileOutputStream(dest);
            byte[]bs=new byte[1026];
            int count=0;
            while ((count=in.read(bs,0,bs.length))!=-1) {
                //把读取到的字节流写入到目的地址的文件里面
                out.write(bs,0,count);

            }
            //刷新下输出流
            out.flush();
            // 关闭输入流和输出流
            out.close();
            out.close();
            System.out.println("复制成功！");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void createJpg(String pathName,String descName){
        BufferedImage bufferedImage;
        try {
            //1.读取图片
            bufferedImage = ImageIO.read(new File(pathName));
            //2.创建一个空白大小相同的RGB背景
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
            //3.再次写入的时候以jpeg图片格式
            ImageIO.write(newBufferedImage, "jpg", new File(descName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
