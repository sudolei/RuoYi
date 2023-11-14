package com.ruoyi.common.isky;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.ruoyi.common.config.Global;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QrcodeUtil {
    private static final int BLACK = -16777216;
    private static final int WHITE = -1;

    public static void main(String[] args) {
        String url = "http://192.168.249.103:8080/jeesite/f/segface/mqravicode?c=20180423225802800";
        String path = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "testQrcode";
        System.out.println(path);
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
        createQrCode(url, path, fileName);
    }

    public static String createQrCode(String url, String path, String fileName) {
        try {
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 400, 400, hints);
            File file = new File(path, fileName);
            if ((file.exists()) || (
                    ((file
                            .getParentFile().exists()) || (file.getParentFile().mkdirs())) && (file.createNewFile()))) {
                writeToFile(bitMatrix, "jpg", file);
                System.out.println("搞定：" + file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file))
            throw new IOException("Could not write an image of format " + format + " to " + file);
    }

    static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream))
            throw new IOException("Could not write an image of format " + format);
    }

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, 1);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? -16777216 : -1);
            }
        }
        return image;
    }


    public static String createQC(String oid) {
//        String path = "http://10.168.1.244/share?orderId=" + oid;
        String path = "http://yishuyin.com/share?orderId=" + oid;
        String uploadPath = Global.getUploadPath() + "qrcode/";
        String uuid = oid.toString();
        String fileName = uuid + ".jpg";
        try {
            createQrCode(path, uploadPath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "../profile/upload/qrcode/" + fileName;
    }
}