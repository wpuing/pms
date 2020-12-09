package com.wyz.pms.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.pms.common.exception.PermissionException;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.core.mapper.FeeMapper;
import com.wyz.pms.core.pojo.Employee;
import com.wyz.pms.core.pojo.Fee;
import com.wyz.pms.core.pojo.FeeType;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.pojo.vo.FeeVo;
import com.wyz.pms.core.service.EmployeeService;
import com.wyz.pms.core.service.FeeService;
import com.wyz.pms.core.service.FeeTypeService;
import com.wyz.pms.core.service.OwnerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private FeeTypeService feeTypeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private FeeMapper feeMapper;

    @Override
    public FeeVo findById(Integer id) {
        PUINGUtil.notNullByZero(id, "收费编号不能为空或者小于等于0");
        Fee fee = feeMapper.selectById(id);
        FeeVo feeVo = new FeeVo();
        BeanUtils.copyProperties(fee,feeVo);//拷贝
        FeeVo clickFeeVo = clickFeeVo(fee);
        if(clickFeeVo!=null){
            feeVo.setOwnerName(clickFeeVo.getOwnerName());
            feeVo.setFeeTypeName(clickFeeVo.getFeeTypeName());
            feeVo.setOperatorName(clickFeeVo.getOperatorName());
        }
        return feeVo;
    }

    @Override
    public List<FeeVo> find(String startTime, String endTime, BigDecimal startPrice, BigDecimal endPrice, Fee fee, Integer sort) {
        LambdaQueryWrapper<Fee> wrapper = Wrappers.<Fee>lambdaQuery();
        List<FeeVo> feeVos = new ArrayList<>();
        if (fee != null) {
            if (fee.getId() != null && fee.getId() > 0) {//编号
                wrapper.like(Fee::getId, fee.getId());
            }
            if (fee.getFeeTypeId() != null && fee.getFeeTypeId() > 0) {//收费类型编号
                wrapper.eq(Fee::getFeeTypeId, fee.getFeeTypeId());
            }
            if (PUINGUtil.isEmpty(fee.getMethod())) {//缴费方式
                wrapper.like(Fee::getMethod, fee.getMethod());
            }
            if (fee.getStatus() != null && fee.getStatus() > 0) {//状态
                wrapper.eq(Fee::getStatus, fee.getStatus());
            }
            if (fee.getOwnerId() != null && fee.getOwnerId() > 0) {//业主id
                wrapper.eq(Fee::getOwnerId, fee.getOwnerId());
            }
            if (fee.getOperatorId() != null && fee.getOperatorId() > 0) {//操作员id
                wrapper.eq(Fee::getOperatorId, fee.getOperatorId());
            }
        }
        //缴费日期
        if (PUINGUtil.isEmpty(startTime)) {//开始
            wrapper.apply("date_format(pay_time,'%Y-%m-%d')>={0}", startTime);
        }
        if (PUINGUtil.isEmpty(endTime)) {//结束
            wrapper.apply("date_format(pay_time,'%Y-%m-%d')<={0}", endTime);
        }
        //金额
        if (startPrice != null && startPrice.compareTo(new BigDecimal("0.00")) > 0) {
            wrapper.ge(Fee::getMoney,startPrice);//大于等于
        }
        if (endPrice != null && endPrice.compareTo(new BigDecimal("0.00")) > 0) {
            wrapper.le(Fee::getMoney,endPrice);//小于等于
        }
        if (sort != null && sort == 1) {//排序
            wrapper.orderByAsc(Fee::getId);
        } else {
            wrapper.orderByDesc(Fee::getId);
        }
        List<Fee> fees = feeMapper.selectList(wrapper);
        if(fees!=null && fees.size()>0){//存在车位
            for (Fee f:fees) {
                FeeVo feeVo = new FeeVo();//创建组合对象
                BeanUtils.copyProperties(f,feeVo);//拷贝
                FeeVo clickFeeVo = clickFeeVo(f);
                if(clickFeeVo!=null){
                    feeVo.setOwnerName(clickFeeVo.getOwnerName());
                    feeVo.setFeeTypeName(clickFeeVo.getFeeTypeName());
                    feeVo.setOperatorName(clickFeeVo.getOperatorName());
                }
                feeVos.add(feeVo);//将组合对象放到集合中
            }
        }
        return feeVos;
    }

    @Override
    public int insert(Fee fee) {
        clickOwner(fee);//校验用户
        clickEmployee(fee);//校验员工
        clickPayTime(fee);
        return feeMapper.insert(fee);
    }

    @Override
    public int update(Fee fee) {
        clickOwner(fee);//校验用户
        clickEmployee(fee);//校验员工
        clickPayTime(fee);
        return feeMapper.updateById(fee);
    }

    @Override
    public int delete(Integer id) {
        PUINGUtil.notNullByZero(id, "收费编号不能为空或者小于等于0");
        return feeMapper.deleteById(id);
    }

    /***
     * 校验用户是否存在
     * @param fee 车位信息
     */
    private Owner clickOwner(Fee fee){
        if(fee !=null && fee.getOwnerId() !=null && fee.getOwnerId()>0){
            Owner owner = ownerService.findById(fee.getOwnerId());
            if(owner==null){
                throw new PermissionException("添加收费失败，该用户不存在！id："+fee.getOwnerId());
            }
            return owner;
        }
        return null;
    }

    /***
     * 校验操作员是否存在
     * @param fee 车位信息
     */
    private Employee clickEmployee(Fee fee){
        if(fee !=null && fee.getOperatorId() !=null && fee.getOperatorId()>0){
            Employee employee = employeeService.findById(fee.getOperatorId());
            if(employee==null){
                throw new PermissionException("添加收费失败，该员工不存在！id："+fee.getOperatorId());
            }
            return employee;
        }
        return null;
    }

    /***
     * 校验操作员是否存在
     * @param fee 车位信息
     */
    private FeeType clickFeeType(Fee fee){
        if(fee !=null && fee.getFeeTypeId() !=null && fee.getFeeTypeId()>0){
            FeeType feeType = feeTypeService.findById(fee.getFeeTypeId());
            if(feeType==null){
                throw new PermissionException("添加收费失败，该收费类型不存在！id："+fee.getOperatorId());
            }
            return feeType;
        }
        return null;
    }

    private FeeVo clickFeeVo(Fee fee){
        FeeVo feeVo = new FeeVo();
        Owner owner = clickOwner(fee);//检查是否存在该用户
        if(owner!=null){//存在用户
            feeVo.setOwnerName(owner.getName());//添加用户名
        }
        Employee employee = clickEmployee(fee);//检查是否存在该操作员
        if(employee!=null){//存在用户
            feeVo.setOperatorName(employee.getName());//添加操作员的姓名
        }
        FeeType feeType = clickFeeType(fee);
        if(feeType!=null){//存在类型
            feeVo.setFeeTypeName(feeType.getName());//添加操作员的姓名
        }
        return feeVo;
    }

    /***
     * 检验缴费状态
     * @param fee
     */
    private void clickPayTime(Fee fee){
        if (fee.getStatus()!= null && fee.getStatus()==2) {//状态
            fee.setPayTime(LocalDateTime.now());
        }else {
            fee.setPayTime(null);
        }
    }
}
