package com.ruoyi.isky.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.HanyupinyinUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.isky.domain.WarehouseGoods;
import com.ruoyi.isky.domain.WarehouseLog;
import com.ruoyi.isky.mapper.WarehouseGoodsMapper;
import com.ruoyi.isky.mapper.WarehouseLogMapper;
import com.ruoyi.isky.service.IWarehouseGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 库存树 服务层实现
 *
 * @author sunlei
 * @date 2019-11-05
 */
@Service
public class WarehouseGoodsServiceImpl implements IWarehouseGoodsService {
    @Autowired
    private WarehouseGoodsMapper warehouseGoodsMapper;

    @Autowired
    private WarehouseLogMapper warehouseLogMapper;

    /**
     * 查询库存树信息
     *
     * @param id 库存树ID
     * @return 库存树信息
     */
    @Override
    public WarehouseGoods selectWarehouseGoodsById(Long id) {
        return warehouseGoodsMapper.selectWarehouseGoodsById(id);
    }

    /**
     * 查询库存树列表
     *
     * @param warehouseGoods 库存树信息
     * @return 库存树集合
     */
    @Override
    public List<WarehouseGoods> selectWarehouseGoodsList(WarehouseGoods warehouseGoods) {
        return warehouseGoodsMapper.selectWarehouseGoodsList(warehouseGoods);
    }


    /**
     * 查询部门管理树
     *
     * @param warehouseGoods 部门信息
     * @return 所有部门信息
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<Ztree> selectDeptTree(WarehouseGoods warehouseGoods) {
        List<WarehouseGoods> deptList = warehouseGoodsMapper.selectWarehouseGoodsList(warehouseGoods);
        List<Ztree> ztrees = initZtree(deptList);
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<WarehouseGoods> deptList) {
        return initZtree(deptList, null);
    }

    /**
     * 对象转部门树
     *
     * @param deptList     部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<WarehouseGoods> deptList, List<String> roleDeptList) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (WarehouseGoods goods : deptList) {
            if (UserConstants.DEPT_NORMAL.equals(goods.getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(goods.getId());
                ztree.setpId(goods.getParentId());
                ztree.setName(goods.getGoodsName());
                ztree.setTitle(goods.getGoodsName());
                if (isCheck) {
                    ztree.setChecked(roleDeptList.contains(goods.getId() + goods.getGoodsName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 新增库存树
     *
     * @param warehouseGoods 库存树信息
     * @return 结果
     */
    @Override
    public int insertWarehouseGoods(WarehouseGoods warehouseGoods) {
        WarehouseGoods info = warehouseGoodsMapper.selectWarehouseGoodsById(warehouseGoods.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus())) {
            throw new BusinessException("停用，不允许新增");
        }
        String namePy = HanyupinyinUtil.getPinyinString(warehouseGoods.getGoodsName());

        warehouseGoods.setAncestors(info.getAncestors() + "," + warehouseGoods.getParentId());
        warehouseGoods.setStatus("0");
        warehouseGoods.setCreateTime(new Date());
        warehouseGoods.setMobileHref(namePy);
        addQt(warehouseGoods);
        int result = warehouseGoodsMapper.insertWarehouseGoods(warehouseGoods);

        int a = warehouseGoods.getWarehouseA() == null ? 0 : warehouseGoods.getWarehouseA();
        int b = warehouseGoods.getWarehouseB() == null ? 0 : warehouseGoods.getWarehouseB();
        int c = warehouseGoods.getWarehouseC() == null ? 0 : warehouseGoods.getWarehouseC();
        int d = warehouseGoods.getWarehouseD() == null ? 0 : warehouseGoods.getWarehouseD();
        int e = warehouseGoods.getWarehouseE() == null ? 0 : warehouseGoods.getWarehouseE();
        WarehouseLog log = new WarehouseLog();
        log.setGoodId(warehouseGoods.getId());
        log.setWarehouseA(a);
        log.setWarehouseB(b);
        log.setWarehouseC(c);
        log.setWarehouseD(d);
        log.setWarehouseE(e);
        warehouseLogMapper.insertWarehouseLog(log);
        return result;
    }


    public void addQt(WarehouseGoods warehouseGoods) {
        String ancestors = warehouseGoods.getAncestors();
        int len = Convert.toStrArray(ancestors).length;
        if (len > 3) {
            int a = warehouseGoods.getWarehouseA() == null ? 0 : warehouseGoods.getWarehouseA();
            int b = warehouseGoods.getWarehouseB() == null ? 0 : warehouseGoods.getWarehouseB();
            int c = warehouseGoods.getWarehouseC() == null ? 0 : warehouseGoods.getWarehouseC();
            int d = warehouseGoods.getWarehouseD() == null ? 0 : warehouseGoods.getWarehouseD();
            int e = warehouseGoods.getWarehouseE() == null ? 0 : warehouseGoods.getWarehouseE();
            long all = a + b + c + d + e;
            warehouseGoods.setStockQuantity(all);
            //获取上级
            long parentid = warehouseGoods.getParentId();
            WarehouseGoods parentGoods = warehouseGoodsMapper.selectWarehouseGoodsById(parentid);
            parentGoods.setWarehouseA(a + parentGoods.getWarehouseA());
            parentGoods.setWarehouseB(b + parentGoods.getWarehouseB());
            parentGoods.setWarehouseC(c + parentGoods.getWarehouseC());
            parentGoods.setWarehouseD(d + parentGoods.getWarehouseD());
            parentGoods.setWarehouseE(e + parentGoods.getWarehouseE());
            parentGoods.setStockQuantity(all + parentGoods.getStockQuantity());
            warehouseGoodsMapper.updateWarehouseGoods(parentGoods);
            //获取上上级
            long pid = parentGoods.getParentId();
            WarehouseGoods pGoods = warehouseGoodsMapper.selectWarehouseGoodsById(pid);
            pGoods.setWarehouseA(a + pGoods.getWarehouseA());
            pGoods.setWarehouseB(b + pGoods.getWarehouseB());
            pGoods.setWarehouseC(c + pGoods.getWarehouseC());
            pGoods.setWarehouseD(d + pGoods.getWarehouseD());
            pGoods.setWarehouseE(e + pGoods.getWarehouseE());
            pGoods.setStockQuantity(all + pGoods.getStockQuantity());
            warehouseGoodsMapper.updateWarehouseGoods(pGoods);
        }
    }


    public void updateQt(WarehouseGoods warehouseGoods) {
        String ancestors = warehouseGoods.getAncestors();
        if (StringUtils.isEmpty(ancestors)) {
            return;
        }
        int len = Convert.toStrArray(ancestors).length;
        if (len > 3) {
            WarehouseGoods g = warehouseGoodsMapper.selectWarehouseGoodsById(warehouseGoods.getId());
            int ga = g.getWarehouseA() == null ? 0 : g.getWarehouseA();
            int gb = g.getWarehouseB() == null ? 0 : g.getWarehouseB();
            int gc = g.getWarehouseC() == null ? 0 : g.getWarehouseC();
            int gd = g.getWarehouseD() == null ? 0 : g.getWarehouseD();
            int ge = g.getWarehouseE() == null ? 0 : g.getWarehouseE();
            long gall = g.getStockQuantity();
            int a = warehouseGoods.getWarehouseA() == null ? 0 : warehouseGoods.getWarehouseA();
            int b = warehouseGoods.getWarehouseB() == null ? 0 : warehouseGoods.getWarehouseB();
            int c = warehouseGoods.getWarehouseC() == null ? 0 : warehouseGoods.getWarehouseC();
            int d = warehouseGoods.getWarehouseD() == null ? 0 : warehouseGoods.getWarehouseD();
            int e = warehouseGoods.getWarehouseE() == null ? 0 : warehouseGoods.getWarehouseE();
            long all = a + b + c + d + e;
            warehouseGoods.setStockQuantity(all);
            int ca = a - ga;
            int cb = b - gb;
            int cc = c - gc;
            int cd = d - gd;
            int ce = e - ge;
            long call = all - gall;


            //获取上级
            long parentid = warehouseGoods.getParentId();
            WarehouseGoods parentGoods = warehouseGoodsMapper.selectWarehouseGoodsById(parentid);
            parentGoods.setWarehouseA(ca + parentGoods.getWarehouseA());
            parentGoods.setWarehouseB(cb + parentGoods.getWarehouseB());
            parentGoods.setWarehouseC(cc + parentGoods.getWarehouseC());
            parentGoods.setWarehouseD(cd + parentGoods.getWarehouseD());
            parentGoods.setWarehouseE(ce + parentGoods.getWarehouseE());
            parentGoods.setStockQuantity(call + parentGoods.getStockQuantity());
            warehouseGoodsMapper.updateWarehouseGoods(parentGoods);

            //获取上上级
            long pid = parentGoods.getParentId();
            WarehouseGoods pGoods = warehouseGoodsMapper.selectWarehouseGoodsById(pid);
            pGoods.setWarehouseA(ca + pGoods.getWarehouseA());
            pGoods.setWarehouseB(cb + pGoods.getWarehouseB());
            pGoods.setWarehouseC(cc + pGoods.getWarehouseC());
            pGoods.setWarehouseD(cd + pGoods.getWarehouseD());
            pGoods.setStockQuantity(call + pGoods.getStockQuantity());
            warehouseGoodsMapper.updateWarehouseGoods(pGoods);
        }

        //日志


        int a = warehouseGoods.getWarehouseA() == null ? 0 : warehouseGoods.getWarehouseA();
        int b = warehouseGoods.getWarehouseB() == null ? 0 : warehouseGoods.getWarehouseB();
        int c = warehouseGoods.getWarehouseC() == null ? 0 : warehouseGoods.getWarehouseC();
        int d = warehouseGoods.getWarehouseD() == null ? 0 : warehouseGoods.getWarehouseD();
        int e = warehouseGoods.getWarehouseE() == null ? 0 : warehouseGoods.getWarehouseE();
        WarehouseLog log = new WarehouseLog();
        log.setGoodId(warehouseGoods.getId());
        log.setWarehouseA(a);
        log.setWarehouseB(b);
        log.setWarehouseC(c);
        log.setWarehouseD(d);
        log.setWarehouseE(e);
        warehouseLogMapper.insertWarehouseLog(log);
    }

    /**
     * 修改库存树
     *
     * @param warehouseGoods 库存树信息
     * @return 结果
     */
    @Override
    public int updateWarehouseGoods(WarehouseGoods warehouseGoods) {
        updateQt(warehouseGoods);
        return warehouseGoodsMapper.updateWarehouseGoods(warehouseGoods);
    }

    /**
     * 删除库存树对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWarehouseGoodsByIds(String ids) {
        return warehouseGoodsMapper.deleteWarehouseGoodsByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deleteWarehouseGoodsById(Long id) {
        WarehouseGoods warehouseGoods = warehouseGoodsMapper.selectWarehouseGoodsById(id);
        int a = warehouseGoods.getWarehouseA() == null ? 0 : warehouseGoods.getWarehouseA();
        int b = warehouseGoods.getWarehouseB() == null ? 0 : warehouseGoods.getWarehouseB();
        int c = warehouseGoods.getWarehouseC() == null ? 0 : warehouseGoods.getWarehouseC();
        int d = warehouseGoods.getWarehouseD() == null ? 0 : warehouseGoods.getWarehouseD();
        int e = warehouseGoods.getWarehouseE() == null ? 0 : warehouseGoods.getWarehouseE();
        long all = a + b + c + d + e;

        long parentId = warehouseGoods.getParentId();
        WarehouseGoods parentGood = warehouseGoodsMapper.selectWarehouseGoodsById(parentId);
        int aa = parentGood.getWarehouseA() == null ? 0 : parentGood.getWarehouseA();
        int bb = parentGood.getWarehouseB() == null ? 0 : parentGood.getWarehouseB();
        int cc = parentGood.getWarehouseC() == null ? 0 : parentGood.getWarehouseC();
        int dd = parentGood.getWarehouseD() == null ? 0 : parentGood.getWarehouseD();
        int de = parentGood.getWarehouseE() == null ? 0 : parentGood.getWarehouseE();
        long pall = parentGood.getStockQuantity() == null ? 0 : parentGood.getStockQuantity();
        parentGood.setWarehouseA(aa - a);
        parentGood.setWarehouseB(bb - b);
        parentGood.setWarehouseC(cc - c);
        parentGood.setWarehouseD(dd - d);
        parentGood.setWarehouseE(de - e);
        parentGood.setStockQuantity(pall - all);
        warehouseGoodsMapper.updateWarehouseGoods(parentGood);


        //获取上上级
        long pid = parentGood.getParentId();
        WarehouseGoods pGoods = warehouseGoodsMapper.selectWarehouseGoodsById(pid);
        pGoods.setWarehouseA(pGoods.getWarehouseA() - a);
        pGoods.setWarehouseB(pGoods.getWarehouseB() - b);
        pGoods.setWarehouseC(pGoods.getWarehouseC() - c);
        pGoods.setWarehouseD(pGoods.getWarehouseD() - d);
        pGoods.setWarehouseE(pGoods.getWarehouseE() - e);
        pGoods.setStockQuantity(pGoods.getStockQuantity() - all);
        warehouseGoodsMapper.updateWarehouseGoods(pGoods);

        return warehouseGoodsMapper.deleteWarehouseGoodsById(id);
    }

    @Override
    public int selectCount(Long parentId) {
        WarehouseGoods dept = new WarehouseGoods();
        dept.setParentId(parentId);
        return warehouseGoodsMapper.selectCount(dept);
    }

}
