package com.ruoyi.common.isky;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 生成文字  daotup
 */
public class ImgWriteText {
    public static void exportImg(String filePath, String disPath, String myText, int x, int y, int size) {
        try {
            String headImg = "d:/logo.jpg";
            InputStream is = new FileInputStream(filePath);

            JPEGImageDecoder jpegDecoder = JPEGCodec.createJPEGDecoder(is);

            BufferedImage buffImg = jpegDecoder.decodeAsBufferedImage();

            Graphics g = buffImg.getGraphics();

            ImageIcon imgIcon = new ImageIcon(headImg);

            Image img = imgIcon.getImage();

            g.drawImage(img, 400, 15, null);

            g.setColor(Color.BLACK);

            Font f = new Font("Source Han Sans K", 0, size);

            Color mycolor = new Color(102, 115, 122);
            g.setColor(mycolor);
            g.setFont(f);

            g.drawString(myText, x, y);
            g.dispose();

            OutputStream os = new FileOutputStream(disPath);

            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
            en.encode(buffImg);
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ImageFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}