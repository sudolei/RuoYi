package com.ruoyi.common.isky;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 图片添加文字
 */
public class FontImageUtil {
    /**
     * 根据str,font的样式以及输出文件目录
     *
     * @param str     字符串
     * @param outFileUrl 输出文件位置
     * @param width   宽度
     * @param height  高度
     * @throws Exception
     */
    public static void createImage(String str, String outFileUrl,
                                   Integer width, Integer height) throws Exception {
        String configFile = "doc//test.ttf";
        System.out.println(new File(".").getAbsolutePath());
        // 创建图片
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, height);
        Color c =new Color(0,0,0,0);
        g.setColor(c);
        // 先用黑色填充整张图片,也就是背景
        g.fillRect(0, 0, width, height);
        // 在换成红色
        g.setColor(Color.red);
        // 设置画笔字体
        InputStream is = new FileInputStream(configFile);
        Font helvetica = Font.createFont(Font.TRUETYPE_FONT, is);
        Font actionJsonBase = helvetica.deriveFont(Font.BOLD, 36);//通过复制此 Font 对象并应用新样式和大小，创建一个新 Font 对
        g.setFont(actionJsonBase);
        /** 用于获得垂直居中y */
        Rectangle clip = g.getClipBounds();
        FontMetrics fm = g.getFontMetrics(actionJsonBase);
        int ascent = fm.getAscent();
        int descent = fm.getDescent();
        System.out.println("ascent:" + ascent);
        System.out.println("descent:" + descent);
        int y = (clip.height - (ascent + descent)) / 2 + ascent;
        // 256 340 0 680
        // 画出字符串
        g.drawString(str, 10, ascent + descent + 10);
        g.dispose();
        // 输出png图片
        File outFile = new File(outFileUrl);
        ImageIO.write(image, "png", outFile);
    }


    public static void main(String[] args) throws Exception {
        createImage("请A1003到3号窗口",  "e:/ff.png", 496, 264);
    }
}
