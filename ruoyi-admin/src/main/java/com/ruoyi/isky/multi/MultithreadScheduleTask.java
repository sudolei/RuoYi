package com.ruoyi.isky.multi;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.isky.domain.FigureLog;
import com.ruoyi.isky.domain.FigureOrderImage;
import com.ruoyi.isky.service.IFigureLogService;
import com.ruoyi.isky.service.IFigureOrderImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync
public class MultithreadScheduleTask {

    @Autowired
    private IFigureOrderImageService orderImageService;
    @Autowired
    private IFigureLogService logService;

    /**
     * 定時任務1，刪除訂單的圖片
     * @throws InterruptedException
     */
    @Async
    @Scheduled(fixedDelay = 10000)  //间隔1秒
    public void first() throws InterruptedException {
//        System.out.println("第一个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        Thread.sleep(1000);
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -50);//30天前
        String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfh = new SimpleDateFormat("HHmm");
        String hours = sdfh.format(new Date());
//        System.out.println("********************************");
//        System.out.println(hours);
//        System.out.println("********************************");
        //如果是凌晨3點01分，開始執行定時任務
        if (hours.equals("0301")) {
//        查询订单图片数据
            FigureOrderImage image = new FigureOrderImage();
//            System.out.println("**************endDate******************");
//            System.out.println(endDate);
            try {
                image.setCreateDate(sdf.parse(endDate));
                image.setTagint(1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<FigureOrderImage> list = orderImageService.selectFigureOrderImageList(image);
//            System.out.println("**************list SIZE ******************");
//            System.out.println(list.size());
            for (FigureOrderImage img : list) {
                String fp[] = {"cut/", "edit/", "jpg/", "origina/", "zcut/", "zedit/", "zoom/", "zzoom/", "zrs/", "rs/"};
                for (String s : fp) {
                    String filePath = Global.getUploadPath() + s + img.getImageUrl();
                    System.out.println(filePath);
                    File f = new File(filePath);
                    if (f.exists()) {
                        f.delete();
                    }
                }
                img.setTagint(1);
                orderImageService.updateFigureOrderImage(img);
            }
        }
    }

    /**
     * 定時任務2，刪除日志的圖片
     * @throws ParseException
     */
    @Async
    @Scheduled(fixedDelay = 10000)
    public void second() throws ParseException {
//        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -50);  //2個月前的
        String endDate = new SimpleDateFormat("yyyy-MM-dd ").format(now.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfh = new SimpleDateFormat("HHmm");
        String hours = sdfh.format(new Date());
        if (hours.equals("0401")) {
            // 查询订单图片数据
            FigureLog log = new FigureLog();
            try {
                log.setCreateDate(sdf.parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<FigureLog> list = logService.selectFigureLogList(log);
            for (FigureLog logs : list) {
                String fp[] = {"cut/", "edit/", "jpg/", "zcut/", "zedit/", "zoom/", "zzoom/", "zrs/", "rs/"};
                for (String s : fp) {
                    String filePath = Global.getUploadPath() + s + logs.getFileName();
                    System.out.println(filePath);
                    File f = new File(filePath);
                    if (f.exists()) {
                        f.delete();
                    }
                }
            }
        }
    }

    /**
     * 刪除日志
     * @throws ParseException
     */
    @Async
    @Scheduled(fixedDelay = 10000)
    public void three() throws ParseException {
//        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -90);  //2個月前的
        String endDate = new SimpleDateFormat("yyyy-MM-dd ").format(now.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfh = new SimpleDateFormat("HHmm");
        String hours = sdfh.format(new Date());
        if (hours.equals("0201")) {
            // 查询订单图片数据
            FigureLog log = new FigureLog();
            try {
                log.setCreateDate(sdf.parse(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            List<FigureLog> list = logService.selectFigureLogList(log);
            for (FigureLog logs : list) {
                String fp[] = {"zrs/", "origina/", "rs/"};
                if (StringUtils.isEmpty(logs.getFileName())) {
                    continue;
                }
                for (String s : fp) {
                    String filePath = Global.getUploadPath() + s + logs.getNewFileName();
                    System.out.println(filePath);
                    File f = new File(filePath);
                    if (f.exists()) {
                        f.delete();
                    }
                }
            }
        }
    }
}
