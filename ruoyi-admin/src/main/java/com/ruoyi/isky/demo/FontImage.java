package com.ruoyi.isky.demo;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author huang(jy)
 * @Date 2019/3/12 14:04
 */
public class FontImage {
    public static void main(String[] args) throws Exception {
        createImage("请A1003到3号窗口22", new Font("宋体", Font.BOLD, 30), new File(
                "e:/d.png"), 200, 50);
//        createImage("请A1002到2号窗口", new Font("黑体", Font.BOLD, 35), new File(
//                "e:/a1.png"), 4096, 2064);
//        createImage("请A1001到1号窗口", new Font("黑体", Font.PLAIN, 40), new File(
//                "e:/a2.png"), 4096, 2064);

    }

    /**
     * 根据str,font的样式以及输出文件目录
     *
     * @param str     字符串
     * @param font    字体
     * @param outFile 输出文件位置
     * @param width   宽度
     * @param height  高度
     * @throws Exception
     */
    public static void createImage(String str, Font font, File outFile,
                                   Integer width, Integer height) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
//        Graphics g = image.getGraphics();
        Graphics2D g = image.createGraphics();
        image = g.getDeviceConfiguration()
                .createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g.dispose();
        g = image.createGraphics();
        g.setClip(0, 0, width, height);
//        g.setColor(Color.white);
        // 先用黑色填充整张图片,也就是背景
        g.fillRect(0, 0, width, height);
        // 在换成红色
        g.setColor(Color.red);
        // 设置画笔字体
        InputStream is = new FileInputStream("E:\\fonts\\aa.ttf");
        Font helvetica = Font.createFont(Font.TRUETYPE_FONT, is);
        Font actionJsonBase = helvetica.deriveFont(Font.BOLD, 36);//通过复制此 Font 对象并应用新样式和大小，创建一个新 Font 对
        g.setFont(actionJsonBase);
        //获取真实宽度
        /** 用于获得垂直居中y */
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(font);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        // 256 340 0 680
        // 画出字符串
        g.drawString(str,  10, ascent+descent+10);
        g.dispose();
        // 输出png图片
        ImageIO.write(image, "png", outFile);
    }
}

