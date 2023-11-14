package com.ruoyi.isky.demo;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;

public class ChangeSize {
    public static void main(String[] args) throws Exception {
        String imgsrc="D:\\profile\\upload\\origina\\123.jpg";
        String imgdist="E:/copy1.jpg";
        reduceImg(imgsrc,imgdist,906,617);

        File f = new File(imgsrc);
        resize(f,imgdist,906,0.9f);
    }

    public void ces() throws IOException {
        //读取图片
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("D:\\profile\\upload\\origina\\123.jpg"));
        //字节流转图片对象
        Image bi = ImageIO.read(in);
        //获取图像的高度，宽度
        int height=bi.getHeight(null);
        int width =bi.getWidth(null);
        //构建图片流
        BufferedImage tag = new BufferedImage(906, 617, BufferedImage.TYPE_INT_RGB);
        //绘制改变尺寸后的图
        tag.getGraphics().drawImage(bi, 0, 0,906, 617, null);
        //输出流
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("E:/copy.jpg"));
        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        //encoder.encode(tag);
        ImageIO.write(tag, "jpg",out);
        in.close();
        out.close();
    }

    public static void reduceImg(String imgsrc, String imgdist, int widthdist,
                                 int heightdist) {
        try {
            File srcfile = new File(imgsrc);
            if (!srcfile.exists()) {
                return;
            }
            Image src = javax.imageio.ImageIO.read(srcfile);

            BufferedImage tag= new BufferedImage((int) widthdist, (int) heightdist,
                    BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_SMOOTH), 0, 0,  null);
//        tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_AREA_AVERAGING), 0, 0,  null);

            FileOutputStream out = new FileOutputStream(imgdist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 改变图片尺寸
     * @param quality
     * @throws IOException
     */
    public static void resize(File originalFile, String resizedFile,
                              int newWidth, float quality) throws IOException {

        if (quality > 1) {
            throw new IllegalArgumentException(
                    "Quality has to be between 0 and 1");
        }

        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
        Image i = ii.getImage();
        Image resizedImage = null;

        int iWidth = i.getWidth(null);
        int iHeight = i.getHeight(null);

        if (iWidth > iHeight) {
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)
                    / iWidth, Image.SCALE_SMOOTH);
        } else {
            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,
                    newWidth, Image.SCALE_SMOOTH);
        }

        // This code ensures that all the pixels in the image are loaded.
        Image temp = new ImageIcon(resizedImage).getImage();

        // Create the buffered image.
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

        // Copy image to buffered image.
        Graphics g = bufferedImage.createGraphics();

        // Clear background and paint the image.
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        // Soften.
        float softenFactor = 0.05f;
        float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);
        String formatName = resizedFile.substring(resizedFile.lastIndexOf(".") + 1);
        ImageIO.write(bufferedImage, formatName, new File(resizedFile));
    }


}
