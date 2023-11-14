package com.ruoyi.isky.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.isky.domain.ProcessBegin;
import com.ruoyi.isky.domain.ProcessUser;
import com.ruoyi.isky.service.IProcessBeginService;
import com.ruoyi.isky.service.IProcessUserService;
import com.ruoyi.system.service.ISysDictDataService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping({"/zz"})
public class LiuChengController extends BaseController {

    private String prefix = "isky/a";

    @Autowired
    private IProcessBeginService processBeginService;

    @Autowired
    private IProcessUserService processUserService;


    @Autowired
    private ISysDictDataService dictDataService;


    @GetMapping({""})
    public String index(ModelMap mmap) {
        ProcessUser user = new ProcessUser();
        user.setGroup("1");
        List<ProcessUser> list = processUserService.selectProcessUserList(user);
        mmap.put("userList", list);
        user.setGroup("2");
        List<ProcessUser> scList = processUserService.selectProcessUserList(user);
        mmap.put("scUserList", scList);
        return this.prefix + "/login";
    }

    @GetMapping({"/login"})
    public String login(ModelMap mmap) {
        ProcessUser user = new ProcessUser();
        List<ProcessUser> list = processUserService.selectProcessUserList(user);
        mmap.put("userList", list);
        return this.prefix + "/login";
    }

    @Log(title = "登陆方法", businessType = BusinessType.OTHER)
    @PostMapping({"/loginSession"})
    @ResponseBody
    public AjaxResult loginSession(HttpServletRequest request, Integer userId) {
        ProcessUser user = processUserService.selectProcessUserById(userId);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return success();
    }

    @GetMapping({"/addOrder"})
    public String addOrder(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        if(user==null){
            return index(mmap);
        }
        return this.prefix + "/index";
    }

    /**
     * 流程开始
     *
     * @param request
     * @param mmap
     * @return
     */
    @GetMapping({"/pstart"})
    public String pstart(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        if(user==null){
            return index(mmap);
        }
        return this.prefix + "/perfect";
    }

    @GetMapping({"/preview"})
    public String preview(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        if(user==null){
            return index(mmap);
        }
        return this.prefix + "/preview";
    }


    @GetMapping({"/main"})
    public String main() {
        return this.prefix + "/main";
    }

    @GetMapping({"/detail"})
    public String detail(HttpServletRequest request, Integer id, ModelMap mmap) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        if(user==null){
            return index(mmap);
        }
        String userGroup = user.getGroup();
        String path = "../profile/upload/liucheng/";
        ProcessBegin begin = processBeginService.selectProcessBeginById(id);
        begin.setOrderName("订单名称：" + begin.getOrderName());
        begin.setDeliveryTime("发货日期：" + begin.getDeliveryTime());
        begin.setLoanAmount("订单金额：" + begin.getLoanAmount());
        begin.setPayAmout("支付金额:" + begin.getPayAmout());
        begin.setConfirm("样品确认：" + begin.getConfirm());

        begin.setCarvingImg(path + begin.getCarvingImg());
        begin.setThermoprintImg(path + begin.getThermoprintImg());
        begin.setPackImg(path + begin.getPackImg());
        begin.setBaseImg(path + begin.getBaseImg());
        begin.setPagesImg(path + begin.getPagesImg());

        ProcessUser zzUser = new ProcessUser();
        if (!StringUtils.isEmpty(begin.getPagesZzUser())) {
            zzUser = processUserService.selectProcessUserById(Integer.parseInt(begin.getPagesZzUser()));
            begin.setPageZzInfo(zzUser.getUserName() + ":" + begin.getPagesZzDate());
        }

        if (!StringUtils.isEmpty(begin.getPagesWcUser())) {
            zzUser = processUserService.selectProcessUserById(Integer.parseInt(begin.getPagesWcUser()));
            begin.setPagesWcInfo(zzUser.getUserName() + ":" + begin.getPagesWcDate());
        }

        if (!StringUtils.isEmpty(begin.getBaseZzUser())) {
            zzUser = processUserService.selectProcessUserById(Integer.parseInt(begin.getBaseZzUser()));
            begin.setBaseZzInfo(zzUser.getUserName() + ":" + begin.getBaseZzDate());
        }
        if (!StringUtils.isEmpty(begin.getBaseWcUser())) {
            zzUser = processUserService.selectProcessUserById(Integer.parseInt(begin.getBaseWcUser()));
            begin.setBaseWcInfo(zzUser.getUserName() + ":" + begin.getBaseWcDate());
        }

        if (!StringUtils.isEmpty(begin.getPackZzUser())) {
            zzUser = processUserService.selectProcessUserById(Integer.parseInt(begin.getPackZzUser()));
            begin.setPackZzInfo(zzUser.getUserName() + ":" + begin.getPackZzDate());
        }
        if (!StringUtils.isEmpty(begin.getPackWcUser())) {
            zzUser = processUserService.selectProcessUserById(Integer.parseInt(begin.getPackWcUser()));
            begin.setPackWcInfo(zzUser.getUserName() + ":" + begin.getPackWcDate());
        }

        if (!StringUtils.isEmpty(begin.getThermoprintZzUser())) {
            zzUser = processUserService.selectProcessUserById(Integer.parseInt(begin.getThermoprintZzUser()));
            begin.setThermoprintZzInfo(zzUser.getUserName() + ":" + begin.getThermoprintZzDate());
        }

        if (!StringUtils.isEmpty(begin.getThermoprintWcUser())) {
            zzUser = processUserService.selectProcessUserById(Integer.parseInt(begin.getThermoprintWcUser()));
            begin.setThermoprintWcInfo(zzUser.getUserName() + ":" + begin.getThermoprintWcDate());
        }

        if (!StringUtils.isEmpty(begin.getCarvingZzUser())) {
            zzUser = processUserService.selectProcessUserById(Integer.parseInt(begin.getCarvingZzUser()));
            begin.setCarvingZzInfo(zzUser.getUserName() + ":" + begin.getCarvingZzDate());
        }

        if (!StringUtils.isEmpty(begin.getCarvingWcUser())) {
            zzUser = processUserService.selectProcessUserById(Integer.parseInt(begin.getCarvingWcUser()));
            begin.setCarvingWcInfo(zzUser.getUserName() + ":" + begin.getCarvingWcDate());
        }

        ProcessUser processUser =  processUserService.selectProcessUserById(Integer.parseInt(begin.getSales()));
        begin.setSalesName(processUser.getUserName());
        mmap.put("begin", begin);
        mmap.put("userGroup", userGroup);
        return this.prefix + "/detail";
    }


    @GetMapping({"/grid"})
    public String grid(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");

        if(user==null){
           return index(mmap);
        }else{
            String userGroup = user.getGroup();
            mmap.put("userGroup", userGroup);
            return this.prefix + "/grid";
        }

    }


    @GetMapping({"/list"})
    public String list(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        if(user==null){
            return index(mmap);
        }else {
            String userGroup = user.getGroup();
            String sales = String.valueOf(user.getId());
            PageDomain pageDomain = new PageDomain();
            pageDomain.setPageNum(1);
            pageDomain.setPageSize(10);
            startPage(pageDomain);
            ProcessBegin begin = new ProcessBegin();
            //如果是生产的，可以看全部数据
            if (!userGroup.equals("2")) {
                begin.setSales(sales);
            }
            List<ProcessBegin> list = processBeginService.selectProcessBeginList(begin);
            TableDataInfo datainfo = getDataTable(list);
            long total = datainfo.getTotal(); //总数
            mmap.put("total", total);
            mmap.put("userGroup", userGroup);
            return this.prefix + "/list";
        }
    }


    @GetMapping({"/unfinishList"})
    public String unfinishList(HttpServletRequest request, ModelMap mmap) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        if(user==null){
            return index(mmap);
        }else {
            String sales = String.valueOf(user.getId());
            String userGroup = user.getGroup();
            PageDomain pageDomain = new PageDomain();
            pageDomain.setPageNum(1);
            pageDomain.setPageSize(10);
            startPage(pageDomain);
            ProcessBegin begin = new ProcessBegin();
            //如果是生产的，可以看全部数据
            if (!userGroup.equals("2")) {
                begin.setSales(sales);
            }
            begin.setState("0");
            List<ProcessBegin> list = processBeginService.selectProcessBeginList(begin);
            TableDataInfo datainfo = getDataTable(list);
            long total = datainfo.getTotal(); //总数
            mmap.put("total", total);
            mmap.put("userGroup", userGroup);
            return this.prefix + "/unfinishList";
        }
    }


    @GetMapping({"/finishList"})
    public String finish(HttpServletRequest request, String state, ModelMap mmap) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        if(user==null){
            return index(mmap);
        }else {
            String sales = String.valueOf(user.getId());
            String userGroup = user.getGroup();
            PageDomain pageDomain = new PageDomain();
            pageDomain.setPageNum(1);
            pageDomain.setPageSize(10);
            startPage(pageDomain);
            ProcessBegin begin = new ProcessBegin();
            //如果是生产的，可以看全部数据
            if (!userGroup.equals("2")) {
                begin.setSales(sales);
            }
            begin.setState("2");
            List<ProcessBegin> list = processBeginService.selectProcessBeginList(begin);
            TableDataInfo datainfo = getDataTable(list);
            long total = datainfo.getTotal(); //总数
            mmap.put("total", total);
            mmap.put("userGroup", userGroup);
            return this.prefix + "/finishList";
        }
    }

    @PostMapping({"/startZz"})
    @ResponseBody
    public AjaxResult startZz(HttpServletRequest request, String keys, Integer id ) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        String userId = String.valueOf(user.getId());
        ProcessBegin begin = processBeginService.selectProcessBeginById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH");
        String date = sdf.format(new Date());
        if (keys.equals("pages")) {
            begin.setPagesZzDate(date);
            begin.setPagesZzUser(userId);
            begin.setPagesState("1");
        } else if (keys.equals("pack")) {
            begin.setPackZzDate(date);
            begin.setPackZzUser(userId);
            begin.setPackState("1");
        } else if (keys.equals("base")) {
            begin.setBaseZzDate(date);
            begin.setBaseZzUser(userId);
            begin.setBaseState("1");
        } else if (keys.equals("thermoprint")) {
            begin.setThermoprintZzDate(date);
            begin.setThermoprintZzUser(userId);
            begin.setThermoprintState("1");
        } else if (keys.equals("carving")) {
            begin.setCarvingZzDate(date);
            begin.setCarvingZzUser(userId);
            begin.setCarvingState("1");
        }
//        begin.setState("1");
        processBeginService.updateProcessBegin(begin);
        return success();
    }


    @PostMapping({"/startWc"})
    @ResponseBody
    public AjaxResult startWc(HttpServletRequest request, String keys, Integer id) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        String userId = String.valueOf(user.getId());
        ProcessBegin begin = processBeginService.selectProcessBeginById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH");
        String date = sdf.format(new Date());
        if (keys.equals("pages")) {
            begin.setPagesWcDate(date);
            begin.setPagesWcUser(userId);
            begin.setPagesState("2");
        } else if (keys.equals("pack")) {
            begin.setPackWcDate(date);
            begin.setPackWcUser(userId);
            begin.setPackState("2");
        } else if (keys.equals("base")) {
            begin.setBaseWcDate(date);
            begin.setBaseWcUser(userId);
            begin.setBaseState("2");
        } else if (keys.equals("thermoprint")) {
            begin.setThermoprintWcDate(date);
            begin.setThermoprintWcUser(userId);
            begin.setThermoprintState("2");
        } else if (keys.equals("carving")) {
            begin.setCarvingWcDate(date);
            begin.setCarvingWcUser(userId);
            begin.setCarvingState("2");
        }


        boolean flag = true;
        if (begin.getPages() != null && !begin.getPages().equals("")) {
            String pagesState = begin.getPagesState();
            if (pagesState.equals("2")) {
                flag = true;
            } else {
                flag = false;
            }
        }

        if (flag && begin.getBase() != null && !begin.getBase().equals("")) {
            String baseState = begin.getBaseState();
            if (baseState.equals("2")) {
                flag = true;
            } else {
                flag = false;
            }
        }

        if (flag && begin.getThermoprint() != null && !begin.getThermoprint().equals("")) {
            String tState = begin.getThermoprintState();
            if (tState.equals("2")) {
                flag = true;
            } else {
                flag = false;
            }
        }

        if (flag && begin.getCarving() != null && !begin.getCarving().equals("")) {
            String carvingState = begin.getCarvingState();
            if (carvingState.equals("2")) {
                flag = true;
            } else {
                flag = false;
            }
        }

        if (flag) {
            begin.setState("2");
        }


        processBeginService.updateProcessBegin(begin);
        return success();
    }


    @Log(title = "图片上传", businessType = BusinessType.OTHER)
    @PostMapping({"/pageList"})
    @ResponseBody
    public AjaxResult pageList(HttpServletRequest request, String state, int pageNum, int pageSize) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        String sales = String.valueOf(user.getId());
        String userGroup = user.getGroup();
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(pageNum);
        pageDomain.setPageSize(pageSize);
        startPage(pageDomain);
        ProcessBegin begin = new ProcessBegin();
        //如果是生产的，可以看全部数据
        if (!userGroup.equals("2")) {
            begin.setSales(sales);
        }
        if (!StringUtils.isEmpty(state)) {
            begin.setState(state);
        }
        List<ProcessBegin> list = processBeginService.selectProcessBeginList(begin);
        TableDataInfo datainfo = getDataTable(list);
        long total = datainfo.getTotal(); //总数
        JSONArray jarray = JSONArray.fromObject(list);
        return success(jarray.toString());
    }


    @Log(title = "图片上传", businessType = BusinessType.OTHER)
    @PostMapping({"/fileUpload"})
    @ResponseBody
    public AjaxResult fileUpload(@RequestParam("myfile") MultipartFile file) {
        String fileName = null;
        String filePath = Global.getUploadPath() + "liucheng/";
        try {
            fileName = FileUploadUtils.upload(filePath, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String files = filePath + fileName;
        return success(fileName);
    }


    @Log(title = "订单上传", businessType = BusinessType.OTHER)
    @PostMapping({"/saveLC"})
    @ResponseBody
    public AjaxResult saveLC(HttpServletRequest request, String mydatas) {
        HttpSession session = request.getSession();
        ProcessUser user = (ProcessUser) session.getAttribute("user");
        String sales = String.valueOf(user.getId());
        String result = "1";
        if (StringUtils.isEmpty(sales)) {
            result = "2";
        }
        try {
            mydatas = StringEscapeUtils.unescapeHtml(mydatas);
            mydatas = mydatas.replaceAll("\\\\", "");
            JSONObject jsonObject = JSONObject.fromObject(mydatas);
            String orderName = jsonObject.getString("orderName");
            String confirmInfo = jsonObject.getString("confirmInfo");
            String loanAmount = jsonObject.getString("loanAmount");
            String payAmount = jsonObject.getString("payAmount");
            String deliveryTime = jsonObject.getString("deliveryTime");
            String customerInfo = jsonObject.getString("customerInfo");
            String pages = jsonObject.getString("pages");
            String base = jsonObject.getString("base");
            String pack = jsonObject.getString("pack");
            String thermoprint = jsonObject.getString("thermoprint");
            String carving = jsonObject.getString("carving");
            String pagesImg = jsonObject.getString("pagesImg");
            String baseImg = jsonObject.getString("baseImg");
            String packImg = jsonObject.getString("packImg");
            String thermoprintImg = jsonObject.getString("thermoprintImg");
            String carvingImg = jsonObject.getString("carvingImg");
            String remark = jsonObject.getString("remark");

            ProcessBegin begin = new ProcessBegin();
            begin.setOrderName(orderName);
            begin.setConfirm(confirmInfo);
            begin.setLoanAmount(loanAmount);
            begin.setPayAmout(payAmount);
            begin.setDeliveryTime(deliveryTime);
            begin.setCustomerInfo(customerInfo);
            begin.setPages(pages);
            begin.setPagesState("0");
            begin.setBase(base);
            begin.setBaseState("0");
            begin.setPack(pack);
            begin.setPackState("0");
            begin.setThermoprint(thermoprint);
            begin.setThermoprintState("0");
            begin.setCarving(carving);
            begin.setCarvingState("0");
            begin.setPagesImg(pagesImg);
            begin.setBaseImg(baseImg);
            begin.setPackImg(packImg);
            begin.setThermoprintImg(thermoprintImg);
            begin.setCarvingImg(carvingImg);
            begin.setRemark(remark);
            begin.setSales(sales);
            begin.setState("0"); //0 未开始   1制作中   2 完成
            processBeginService.insertProcessBegin(begin);
        } catch (Exception e) {
            result = "2";
            e.printStackTrace();
        }
        return success(result);
    }
}
