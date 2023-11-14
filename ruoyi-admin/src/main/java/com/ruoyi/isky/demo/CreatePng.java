package com.ruoyi.isky.demo;

import com.ruoyi.common.utils.ThumbUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CreatePng {
    public static void main(String[] args) throws Exception{
        int width = 600;
        int height = 100;
        // 创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();
        // ----------  增加下面的代码使得背景透明  -----------------
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
        // ----------  背景透明代码结束  -----------------
        // 画图
        g2d.setColor(new Color(255, 0, 0));
        g2d.setStroke(new BasicStroke(1));
        //g2d.draw();
        g2d.setColor(Color.GRAY);
        // 设置画笔字体
        InputStream is = new FileInputStream("E:\\fonts\\aa.ttf");
        Font helvetica = Font.createFont(Font.TRUETYPE_FONT, is);
        Font actionJsonBase = helvetica.deriveFont(Font.BOLD, 36);//通过复制此 Font 对象并应用新样式和大小，创建一个新 Font 对
        g2d.setFont(actionJsonBase);
        //获取真实宽度
        /** 用于获得垂直居中y */
        Rectangle clip = g2d.getClipBounds();
        FontMetrics fm = g2d.getFontMetrics(new Font("宋体", Font.BOLD, 25));
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        // 画出字符串
        g2d.drawString("知善知恶，为善去恶",  10, ascent+descent+10);
        g2d.drawString("无善无恶，有善有恶",  20, ascent+descent+50);
        //释放对象
        g2d.dispose();
        // 保存文件
        try {
            ImageIO.write(image, "png", new File("c:/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ThumbUtil.rotateNotWH("c:/test.png",-40,"c:/","hello.png");
        ThumbUtil.syntesis(380,510,"c:/bkk.png","c:/hello.png","c:/","hell.png");
        //ThumbUtil.syntesis(580,800,"c:/hello.png","c:/","hell.png");
    }
}
