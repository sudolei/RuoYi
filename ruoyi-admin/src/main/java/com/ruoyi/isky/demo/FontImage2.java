package com.ruoyi.isky.demo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FontImage2 {

    public static void main(String[] args){
        try {
            //创建画布
            BufferedImage image = new BufferedImage(480,270,BufferedImage.TYPE_INT_RGB);
            //背景图（使用网络链接获取）
//            BufferedImage background = ImageIO.read(new URL("http://.../background.png"));
//            //头像（使用本地路径获取）
//            BufferedImage icon = ImageIO.read(new File("C:\\Desktop\\doctor.png"));
//            //字体背景图
//            BufferedImage fontBd = ImageIO.read(new URL("http://.../fontBd.png"));
            //开启画图
            Graphics2D graphics = image.createGraphics();
            //把背景图、头像添加到画布中
//            graphics.drawImage(background.getScaledInstance(480,270,Image.SCALE_DEFAULT),0,0,null);
//            graphics.drawImage(icon.getScaledInstance(180,227,Image.SCALE_DEFAULT),283,42,null);
            //写入标题，标题引入自定义字体
            Font font = getFont(1,40);
            graphics.setColor(Color.decode("#333333"));
            graphics.setFont(font);
            drawString("论标题的重要性",20,105,6,graphics,40);//将字符串按照自定义的间隔写入，防止字体全部写在同一个地方
            //添加字体背景
//            graphics.drawImage(fontBd.getScaledInstance(3*17,35,Image.SCALE_DEFAULT),20,136,null);
            //写入信息
            graphics.setFont(new Font("微软雅黑", Font.BOLD, 17));
            graphics.setColor(Color.white);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //去字体锐化
            graphics.drawString("陌陌 特级医师", 32, 159);

            //写入信息
            graphics.setFont(new Font("微软雅黑", Font.PLAIN, 17));
            graphics.setColor(Color.decode("#333333"));
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.drawString("中南第二医院 内外科", 20, 199);
            //关闭画布创作
            graphics.dispose();
            //根据画布内容生成图片
            ImageIO.write(image, "png", new File("d:\\j.png"));
            System.out.println("图片生成完毕");
        } catch (Exception ex) {
            System.out.println("error: "+ex.getMessage());
            ex.printStackTrace();
        }

    }

    /**
     *  引入自定义的字体
     * @param fontStyle 字体样式
     * @param fontSize  字体大小
     * @return
     */
    public static Font getFont(int fontStyle, float fontSize) {
        Font font = null;
        FileInputStream fileInputStream = null;
        String fontUrl = "";
        try {
            switch (fontStyle) {
                case 1:
                    //文悦新青年体
                    fontUrl = "C:\\youth.OTF";
                    break;
                default:
                    fontUrl = "C:\\vagrom.otf";
                    break;
            }
            fileInputStream = new FileInputStream(new File(fontUrl));
            Font tempFont = Font.createFont(Font.TRUETYPE_FONT,fileInputStream);
            font = tempFont.deriveFont(fontSize);
            GraphicsEnvironment ge = GraphicsEnvironment
                    .getLocalGraphicsEnvironment();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return font;
    }
    /**
     * 将字符串按照自定义的间隔写入
     * @param str   目标字符串
     * @param x     写入的位置的x轴
     * @param y     写入的位置的y轴
     * @param rate  写入间隔
     * @param g     画布
     * @param fontSize  字体的大小
     */
    public static void drawString(String str, int x, int y, int rate, Graphics2D g, int fontSize){
        String tempStr="";
        int tempx=x;
        int tempy=y;
        while (str.length()>0){
            tempStr=str.substring(0, 1);
            str=str.substring(1, str.length());
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawString(tempStr, tempx, tempy);
            tempx = tempx + fontSize - rate;
        }
    }

}
