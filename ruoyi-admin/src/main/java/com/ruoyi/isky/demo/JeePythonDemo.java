package com.ruoyi.isky.demo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JeePythonDemo {
    //F:\pydir
    public static void main(String args[]) {
//        int a[][] = getImageGRBA("E:\\fenjie2\\image1.jpg");
        List list = getImageGRB("E:\\b.jpg");
        List listb = getImageGRB("E:\\a.jpg");

        for (int i = 0; i < list.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < listb.size(); j++) {
                if (list.get(i).equals(listb.get(j))) {
                    flag = true;
                    System.out.println("有重複");
                    break;
                }
            }
            if (flag){
                System.out.println("有重複");
                break;
            }
        }

    }


    public void callPy() {
        String[] arguments = new String[]{"python", "F://pydir/img.py"};
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            //java代码中的process.waitFor()返回值为0表示我们调用python脚本成功，
            //返回值为1表示调用python脚本失败，这和我们通常意义上见到的0与1定义正好相反
            int re = process.waitFor();
            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取图片RGB数组
     *
     * @param filePath
     * @return
     */
    public static int[][] getImageGRBA(String filePath) {
        File file = new File(filePath);
        int[][] result = null;
        if (!file.exists()) {
            return result;
        }
        try {
            BufferedImage bufImg = ImageIO.read(file);
            int height = bufImg.getHeight();
            int width = bufImg.getWidth();
            result = new int[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    result[i][j] = bufImg.getRGB(i, j) & 0xFFFFFF;
                    System.out.println(bufImg.getRGB(i, j) & 0xFFFFFF);
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取图片RGB数组
     *
     * @param filePath
     * @return
     */
    public static List getImageGRB(String filePath) {
        File file = new File(filePath);
        List<String> list = new ArrayList();
        if (!file.exists()) {
            return null;
        }
        try {
            BufferedImage bufImg = ImageIO.read(file);
            int height = bufImg.getHeight();
            int width = bufImg.getWidth();
            StringBuffer sb = null;
            for (int i = 0; i < width; i++) {
                sb = new StringBuffer();
                for (int j = 0; j < height; j++) {
                    sb.append(bufImg.getRGB(i, j) & 0xFFFFFF);
                }
                list.add(sb.toString());
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}
