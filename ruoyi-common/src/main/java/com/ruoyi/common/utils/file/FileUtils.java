package com.ruoyi.common.utils.file;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件处理工具类
 * 
 * @author ruoyi
 */
public class FileUtils
{
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    /**
     * 输出指定文件的byte数组
     * 
     * @param filePath 文件路径
     * @param os 输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException
    {
        FileInputStream fis = null;
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     * 
     * @param filePath 文件
     * @return
     */
    public static boolean deleteFile(String filePath)
    {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
        {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 文件名称验证
     * 
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename)
    {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * 下载文件名重新编码
     * 
     * @param request 请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
            throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }


    public static void zipToFiles(String srcDirName, List<String> fileNames, String descFileName, String descFileDir)
    {
        if (srcDirName == null)
        {
            System.out.println("文件压缩失败，目录 " + srcDirName + " 不存在!");
            return;
        }
        File fileDir = new File(srcDirName);
        if ((!fileDir.exists()) || (!fileDir.isDirectory()))
        {
            System.out.println("文件压缩失败，目录 " + srcDirName + " 不存在!");
            return;
        }
        String dirPath = fileDir.getAbsolutePath();
        File descFile = new File(descFileName);
        try {
            ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(descFileDir + descFile));

            for (String str : fileNames) {
                File file = new File(fileDir, str);
                if (file.isFile())
                    zipFilesToZipFile(dirPath, file, zouts);
                else {
                    zipDirectoryToZipFile(dirPath, file, zouts);
                }
            }
            zouts.close();

            System.out.println(descFileName + " 文件压缩成功!");
        }
        catch (Exception e) {
            System.out.println("文件压缩失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void zipFilesToZipFile(String dirPath, File file, ZipOutputStream zouts)
    {
        FileInputStream fin = null;
        ZipEntry entry = null;

        byte[] buf = new byte[4096];
        int readByte = 0;
        if (file.isFile())
            try
            {
                fin = new FileInputStream(file);

                entry = new ZipEntry(getEntryName(dirPath, file));

                zouts.putNextEntry(entry);

                while ((readByte = fin.read(buf)) != -1) {
                    zouts.write(buf, 0, readByte);
                }
                zouts.closeEntry();
                fin.close();
                System.out.println("添加文件 " + file.getAbsolutePath() + " 到zip文件中!");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void zipDirectoryToZipFile(String dirPath, File fileDir, ZipOutputStream zouts)
    {
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();

            if (files.length == 0)
            {
                ZipEntry entry = new ZipEntry(getEntryName(dirPath, fileDir));
                try {
                    zouts.putNextEntry(entry);
                    zouts.closeEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }

            for (int i = 0; i < files.length; i++)
                if (files[i].isFile())
                {
                    zipFilesToZipFile(dirPath, files[i], zouts);
                }
                else
                    zipDirectoryToZipFile(dirPath, files[i], zouts);
        }
    }

    private static String getEntryName(String dirPath, File file)
    {
        String dirPaths = dirPath;
        if (!dirPaths.endsWith(File.separator)) {
            dirPaths = dirPaths + File.separator;
        }
        String filePath = file.getAbsolutePath();

        if (file.isDirectory()) {
            filePath = filePath + "/";
        }
        int index = filePath.indexOf(dirPaths);

        return filePath.substring(index + dirPaths.length());
    }

}
