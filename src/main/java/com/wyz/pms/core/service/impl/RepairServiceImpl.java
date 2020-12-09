package com.wyz.pms.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.pms.common.exception.PermissionException;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.core.mapper.RepairMapper;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.pojo.Repair;
import com.wyz.pms.core.pojo.vo.RepairVo;
import com.wyz.pms.core.service.OwnerService;
import com.wyz.pms.core.service.RepairService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairMapper repairMapper;

    @Autowired
    private OwnerService ownerService;

    @Override
    public RepairVo findById(Integer id) {
        PUINGUtil.notNullByZero(id, "维修编号不能为空或者小于等于0");
        Repair repair = repairMapper.selectById(id);
        RepairVo repairVo = new RepairVo();
        BeanUtils.copyProperties(repair,repairVo);//拷贝
        Owner owner = clickOwner(repair);
        if(owner!=null){//存在用户
            repairVo.setOwnerName(owner.getName());//添加用户名
        }
        return repairVo;
    }

    @Override
    public List<RepairVo> find(String createStartTime, String createEndTime, String resolveStartTime, String resolveEndTime, Repair repair, Integer sort) {
        List<RepairVo> repairVos= new ArrayList<>();
        LambdaQueryWrapper<Repair> wrapper = Wrappers.<Repair>lambdaQuery();
        if (repair != null) {
            if (repair.getId() != null && repair.getId() > 0) {//编号
                wrapper.eq(Repair::getId, repair.getId());
            }
            if (PUINGUtil.isEmpty(repair.getItem())) {//物品名
                wrapper.like(Repair::getItem, repair.getItem());
            }
            if (PUINGUtil.isEmpty(repair.getSite())) {//地点
                wrapper.like(Repair::getSite, repair.getSite());
            }
            if (PUINGUtil.isEmpty(repair.getReason())) {//原因
                wrapper.like(Repair::getReason, repair.getReason());
            }
            if (repair.getStatus() != null && repair.getStatus() > 0) {//状态
                wrapper.eq(Repair::getStatus, repair.getStatus());
            }
            if (repair.getOwnerId() != null && repair.getOwnerId() > 0) {//用户编号
                wrapper.eq(Repair::getOwnerId, repair.getOwnerId());
            }
            if (PUINGUtil.isEmpty(repair.getEmployeeName())) {//处理人
                wrapper.like(Repair::getEmployeeName, repair.getEmployeeName());
            }
        }
        //报修时间
        if (PUINGUtil.isEmpty(createStartTime)) {//开始
            wrapper.apply("date_format(create_time,'%Y-%m-%d')>={0}", createStartTime);
        }
        if (PUINGUtil.isEmpty(createEndTime)) {//结束
            wrapper.apply("date_format(create_time,'%Y-%m-%d')<={0}", createEndTime);
        }
        //解决时间
        if (PUINGUtil.isEmpty(resolveStartTime)) {//开始
            wrapper.apply("date_format(resolve_time,'%Y-%m-%d')>={0}", resolveStartTime);
        }
        if (PUINGUtil.isEmpty(resolveEndTime)) {//结束
            wrapper.apply("date_format(resolve_time,'%Y-%m-%d')<={0}", resolveEndTime);
        }
        if (sort != null && sort == 1) {//排序
            wrapper.orderByAsc(Repair::getId);
        } else {
            wrapper.orderByDesc(Repair::getId);
        }
        List<Repair> repairs = repairMapper.selectList(wrapper);
        if(repairs!=null && repairs.size()>0){//存在车位
            for (Repair r:repairs) {
                RepairVo repairVo = new RepairVo();//创建组合对象
                BeanUtils.copyProperties(r,repairVo);//拷贝
                Owner owner = clickOwner(r);//检查是否存在该用户
                if(owner!=null){//存在用户
                    repairVo.setOwnerName(owner.getName());//添加用户名
                }
                repairVos.add(repairVo);//将组合对象放到集合中
            }
        }
        return repairVos;
    }

    @Override
    public int insert(Repair repair) {
        clickOwner(repair);//存在用户id则去校验
        if(repair.getCreateTime()==null){
            repair.setCreateTime(LocalDateTime.now());
        }
        resolveTime(repair);
        return repairMapper.insert(repair);
    }

    @Override
    public int update(Repair repair) {
        clickOwner(repair);//存在用户id则去校验
        resolveTime(repair);
        return repairMapper.updateById(repair);
    }

    @Override
    public int delete(Integer id) {
        PUINGUtil.notNullByZero(id, "维修编号不能为空或者小于等于0");
        return repairMapper.deleteById(id);
    }

    /***
     * 校验用户是否存在
     * @param repair 维修信息
     */
    private Owner clickOwner(Repair repair){
        if(repair !=null && repair.getOwnerId() !=null && repair.getOwnerId()>0){
            Owner owner = ownerService.findById(repair.getOwnerId());
            if(owner==null){
                throw new PermissionException("添加维修失败，该用户不存在！id："+repair.getOwnerId());
            }
            return owner;
        }
        return null;
    }

    /***
     * 当已解决的时候
     * @param repair
     */
    private void resolveTime(Repair repair){
        if(repair.getStatus()==3){//设置为已解决的时候修改解决时间
            repair.setResolveTime(LocalDateTime.now());
        }else {
            repair.setResolveTime(null);
        }
    }
}
