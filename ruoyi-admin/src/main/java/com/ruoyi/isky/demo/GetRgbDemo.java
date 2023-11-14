package com.ruoyi.isky.demo;

import com.ruoyi.common.isky.Block;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GetRgbDemo {

    public static void main(String args[]) {

        String filePath = "E:\\fenjie2\\image1.jpg";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在！");
            return;
        }
        BufferedImage bi = null;
        try {
            bi = (BufferedImage) ImageIO.read(new File(filePath));
            int width = bi.getWidth();
            int height = bi.getHeight();
            ArrayList<Block> block = new ArrayList<Block>();
            int count = 0;
            int updip = 0;
            int rightdip = 0;
            int downdip = 0;
            int leftdip = 0;
            for (int i = 0; i < height; i++) {
                System.out.println("-----------"+i+"---------------");
                for (int j = 0; j < width; j++) {// 行扫描
                    int dip = bi.getRGB(j, i);
                    System.out.println(dip);
                }
                if (i>=4){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
