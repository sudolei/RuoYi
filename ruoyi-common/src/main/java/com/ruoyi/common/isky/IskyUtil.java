package com.ruoyi.common.isky;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IskyUtil {
//    public static void main(String args[]) throws IOException {
//        String result = "src/main/resources/images/";
//        Thumbnails.of(result + "sijili.jpg").size(200, 300).toFile(result + "image_200x300.jpg");
//    }

    public static Map printImageTags2(File img) {
        Metadata metadata = null;
        try {
            metadata = JpegMetadataReader.readMetadata(img);
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //输出所有附加属性数据
        String width = "";
        String height = "";
        for (Directory directory : metadata.getDirectories()) {
            System.out.println("******\t" + directory.getName() + "\t******");
            for (Tag tag : directory.getTags()) {
                System.out.println(tag.getTagName() + ":" + tag.getDescription());
                if (tag.equals("Image Height")) {
                    height = tag.getDescription().split("")[0];
                }
                if (tag.equals("Image Width")) {
                    width = tag.getDescription().split("")[0];
                }
            }
        }
        Map m = new HashMap();
        m.put("width", width);
        m.put("height", height);
        return m;
    }


    public static Map<String, Integer> printImageTags(File file)
            throws ImageProcessingException, Exception {
        long s = System.currentTimeMillis();
        Map m = new HashMap();
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        int height = 0;
        int width = 0;
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String desc = tag.getDescription();
                if (tagName.equals("Image Height")) {
                    height = StringToInt(desc);
                    System.out.println("图片高度: " + desc);
                } else if (tagName.equals("Image Width")) {
                    width = StringToInt(desc);
                    System.out.println("图片宽度: " + desc);
                } else if (tagName.equals("Date/Time Original")) {
                    System.out.println("拍摄时间: " + desc);
                } else if (tagName.equals("GPS Latitude")) {
                    System.err.println("纬度 : " + desc);
                } else if (tagName.equals("GPS Longitude")) {
                    System.err.println("经度: " + desc);
                }
            }
        }

        m.put("width", Integer.valueOf(width));
        m.put("height", Integer.valueOf(height));
        long e = System.currentTimeMillis();
        System.out.println(e - s);
        return m;
    }


    public static int StringToInt(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String r = m.replaceAll("").trim();
        return Integer.parseInt(r);
    }


    public static void createJpg(String pathName,String descName){
        BufferedImage bufferedImage;
        try {
            //1.读取图片
            bufferedImage = ImageIO.read(new File(pathName));
            //2.创建一个空白大小相同的RGB背景
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                    bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
            //3.再次写入的时候以jpeg图片格式
            ImageIO.write(newBufferedImage, "jpg", new File(descName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    //判断是否为手机浏览器
//    public static boolean JudgeIsMoblie(HttpServletRequest request) {
//        boolean isMoblie = false;
//        String[] mobileAgents = { "iphone", "android","ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
//                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
//                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
//                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
//                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
//                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
//                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
//                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
//                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
//                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
//                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
//                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
//                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
//                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
//                "Googlebot-Mobile" };
//        if (request.getHeader("User-Agent") != null) {
//            String agent=request.getHeader("User-Agent");
//            for (String mobileAgent : mobileAgents) {
//                if (agent.toLowerCase().indexOf(mobileAgent) >= 0&&agent.toLowerCase().indexOf("windows nt")<=0 &&agent.toLowerCase().indexOf("macintosh")<=0) {
//                    isMoblie = true;
//                    break;
//                }
//            }
//        }
//        return isMoblie;
//    }

    /**
     * 获取图片宽度
     *
     * @param file 图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getWidth(null); // 得到源图宽
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }


    /**
     * 获取图片高度
     *
     * @param file 图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static boolean JudgeIsMoblie(HttpServletRequest request) {
        boolean isMoblie = false;
        String[] mobileAgents = {"iphone", "android", "ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi", "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod", "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma", "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos", "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos", "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320", "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-", "Googlebot-Mobile"};

        if (request.getHeader("User-Agent") != null) {
            String agent = request.getHeader("User-Agent");
            for (String mobileAgent : mobileAgents) {
                if ((agent.toLowerCase().indexOf(mobileAgent) >= 0) && (agent.toLowerCase().indexOf("windows nt") <= 0) && (agent.toLowerCase().indexOf("macintosh") <= 0)) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }

    public static void MkDir(String fileDir) {
        File f = new File(fileDir);
        if (!f.exists())
            f.mkdirs();
    }

    public static String uuid() {
        String s = UUID.randomUUID().toString();

        return s.replace("-", "");
    }

    public static void MkDirs(String[] fileDir) {
        for (String s : fileDir) {
            File f = new File(s);
            if (!f.exists())
                f.mkdirs();
        }
    }

    public static void readBin2Image(byte[] byteArray, String targetPath,String fileName) {
        File file = new File(targetPath+fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(byteArray, 0, byteArray.length);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
