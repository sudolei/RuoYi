package com.ruoyi.common.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 图片处理
 *
 * @author sunlei
 */
public class ThumbUtil {
    /**
     * 图片裁剪
     *
     * @param fileName
     * @param photo
     * @param xsite
     * @param ysite
     * @param width
     * @param height
     * @param path
     */
    public static String cutPic(String fileName, String photo, float xsite, float ysite, float zoom, int width,
                                int height, String path) {
        long s = System.currentTimeMillis();

        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String result = path + fileName;// 输出的图片路径與名稱
        try {
            float xz = xsite * zoom;
            float yz = ysite * zoom;

            Thumbnails.of(photo).sourceRegion((int) xz, (int) yz, width + 1, height + 1).size(width + 1, height + 1)
                    .keepAspectRatio(false).toFile(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("裁切需要的时间：" + String.valueOf(System.currentTimeMillis() - s));
        return result;
    }

    public static String cutAsPic(String fileName, String photo, int xsite, int ysite, int width, int height,
                                  String path) {
        long s = System.currentTimeMillis();
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String result = path + fileName;// 输出的图片路径與名稱
        try {
            Thumbnails.of(photo).sourceRegion(xsite, ysite, width, height).size(width, height).keepAspectRatio(false)
                    .toFile(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("裁切需要的时间：" + String.valueOf(System.currentTimeMillis() - s));
        return result;
    }

    /**
     * 截取图片中心指定宽度高度的图片
     *
     * @param fileName：文件的名称
     * @param photo:要截取的图片
     * @param width:截取的宽度
     * @param height：截取的高度
     * @param path：截取后的文件路径
     * @return
     */
    public static String cutPicCenter(String fileName, String photo, int width, int height, String path) {
        long s = System.currentTimeMillis();
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String result = path + fileName;// 输出的图片路径與名稱
        try {
            Thumbnails.of(photo).sourceRegion(Positions.CENTER, width, height).size(width, height)
                    .keepAspectRatio(false).toFile(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("裁切中心点需要的时间：" + String.valueOf(System.currentTimeMillis() - s));
        return result;
    }

    /**
     * 旋转
     *
     * @param photo
     * @param width
     * @param height
     * @param r:旋转度数，正数：顺时针 负数：逆时针
     * @param path
     * @param fileName
     * @return
     */
    public static String rotate(String photo, int width, int height, int r, String path, String fileName) {
        long s = System.currentTimeMillis();
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String result = path + fileName;// 输出的图片路径與名稱
        try {
            Thumbnails.of(photo).size(width, height).rotate(r).toFile(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("旋转需要的时间：" + String.valueOf(System.currentTimeMillis() - s));
        return result;
    }

    /**
     * @param photo
     * @param r
     * @param path
     * @param fileName
     * @return
     */
    public static String rotateNotWH(String photo, int r, String path, String fileName) {
        long s = System.currentTimeMillis();
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String result = path + fileName;// 输出的图片路径與名稱
        try {
            Thumbnails.of(photo).rotate(r).scale(1.0f).toFile(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("旋转需要的时间：" + String.valueOf(System.currentTimeMillis() - s));

        return result;
    }

    /**
     * 图片缩放
     *
     * @param fileName
     * @param photo
     * @param zoom
     * @param path
     */
    public static String zoomPic(String fileName, String photo, float zoom, String path) {
        long s = System.currentTimeMillis();
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String result = path + fileName;// 输出的图片路径與名稱
        try {
            Thumbnails.of(photo).scale(zoom).outputQuality(0.99f).toFile(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("缩放需要的时间：" + String.valueOf(System.currentTimeMillis() - s));
        return result;
    }

    // Thumbnails.of(fromPic).size(100,
    // 100).keepAspectRatio(false).toFile(toPic);

    /**
     * 固定尺寸缩放
     *
     * @param fileName
     * @param photo
     * @param width
     * @param height
     * @param path
     * @return
     */
    public static String zoomGdccPic(String fileName, String photo, int width, int height, String path) {
        long s = System.currentTimeMillis();
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String result = path + fileName;// 输出的图片路径與名稱

        try {
            Thumbnails.of(photo).size(width + 1, height + 1).keepAspectRatio(false).outputQuality(0.99f).toFile(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("缩放固定尺寸需要的时间：" + String.valueOf(System.currentTimeMillis() - s));
        return result;
    }

    /**
     * 固定尺寸缩放不加像素
     *
     * @param fileName
     * @param photo
     * @param width
     * @param height
     * @param path
     * @return
     */
    public static String zoomGdccPicNotAdd(String fileName, String photo, int width, int height, String path) {
        long s = System.currentTimeMillis();
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String result = path + fileName;// 输出的图片路径與名稱

        try {
            Thumbnails.of(photo).size(width, height).keepAspectRatio(false).outputQuality(0.99f).toFile(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("缩放固定尺寸需要的时间：" + String.valueOf(System.currentTimeMillis() - s));
        return result;
    }

    /**
     * 合成图片
     *
     * @param x:x坐标
     * @param y:y坐标
     * @param mophoto:模板图片
     * @param photo:上传的图片
     * @param path:文件路径，如D:\\orderpic\\分类\\年\\月\\user\\
     * @return
     */
    public static String syntesis(final int x, final int y, String mophoto, String photo, String path,
                                  String fileName) {
        long s = System.currentTimeMillis();
        int width = 0;
        int height = 0;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        String result = path + fileName;// 输出的图片路径與名稱
        BufferedImage image;
        try {
            image = ImageIO.read(new FileInputStream(mophoto));
            width = image.getWidth();
            height = image.getHeight();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (mophoto == null) {
            System.out.println("mophoto图片为空");
        }

        if (photo == null) {
            System.out.println("photo图片为空");
        }

        System.out.println("-------------photo------------" + photo);
        try {
            Thumbnails.of(mophoto).size(width, height).watermark(new Position() {

                @Override
                public Point calculate(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
                    // TODO Auto-generated method stub
                    return new Point(x, y);
                }

            }, ImageIO.read(new File(photo)), 1f).outputQuality(1f).toFile(result);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("合成需要的时间：" + String.valueOf(System.currentTimeMillis() - s));
        return result;
    }

    public static void main(String[] args) {
        String imgsrc = "D:\\profile\\upload\\origina\\123.jpg";
        ThumbUtil.zoomGdccPic("copy3.jpg", imgsrc, 906, 617, "e:\\");
    }

}
