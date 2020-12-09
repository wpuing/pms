package com.wyz.pms.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.pms.common.exception.ParameterException;
import com.wyz.pms.common.exception.PermissionException;
import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.core.mapper.ParkingMapper;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.pojo.Parking;
import com.wyz.pms.core.pojo.vo.ParkingVo;
import com.wyz.pms.core.service.OwnerService;
import com.wyz.pms.core.service.ParkingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingMapper parkingMapper;

    @Autowired
    private OwnerService ownerService;


    @Override
    public ParkingVo findById(Integer id) {
        PUINGUtil.notNullByZero(id, "员工编号不能为空或者小于等于0");
        Parking parking = parkingMapper.selectById(id);
        ParkingVo parkingVo = new ParkingVo();
        BeanUtils.copyProperties(parking,parkingVo);//拷贝
        Owner owner = clickOwner(parking);
        if(owner!=null){//存在用户
            parkingVo.setOwnerName(owner.getName());//添加用户名
        }
        return parkingVo;
    }

    @Override
    public List<ParkingVo> find(BigDecimal startPrice, BigDecimal endPrice, Parking parking, Integer sort) {
        List<ParkingVo> parkingVos= new ArrayList<>();
        LambdaQueryWrapper<Parking> wrapper = Wrappers.<Parking>lambdaQuery();
        if (parking != null) {
            if (PUINGUtil.isEmpty(parking.getNumber())) {//编号
                wrapper.like(Parking::getNumber, parking.getNumber());
            }
            if (parking.getOwnerId()!=null && parking.getOwnerId()>0) {//业主id
                wrapper.eq(Parking::getOwnerId, parking.getOwnerId());
            }
            if (parking.getStatus()!=null && parking.getStatus()>0) {//状态
                wrapper.eq(Parking::getStatus, parking.getStatus());
            }
        }
        if (startPrice != null && startPrice.compareTo(new BigDecimal("0.00")) > 0) {
            wrapper.ge(Parking::getPrice,startPrice);//大于等于
        }
        if (endPrice != null && endPrice.compareTo(new BigDecimal("0.00")) > 0) {
            wrapper.le(Parking::getPrice,endPrice);//小于等于
        }
        if(sort!=null && sort==1){//排序
            wrapper.orderByAsc(Parking::getId);
        }else {
            wrapper.orderByDesc(Parking::getId);
        }
        List<Parking> parkings = parkingMapper.selectList(wrapper);
        System.out.println(" parkings"+parkings);
        if(parkings!=null && parkings.size()>0){//存在车位
            for (Parking pk:parkings) {
                System.out.println(" pk"+pk);
                ParkingVo parkingVo = new ParkingVo();//创建组合对象
                BeanUtils.copyProperties(pk,parkingVo);//拷贝
                Owner owner = clickOwner(pk);//检查是否存在该用户
                if(owner!=null){//存在用户
                    parkingVo.setOwnerName(owner.getName());//添加用户名
                }
                parkingVos.add(parkingVo);//将组合对象放到集合中
            }
        }
        return parkingVos;
    }

    @Override
    public int insert(Parking parking) {
        clickOwner(parking);//存在用户则校验是否存在
        PMSUtil.clickStatusAnOwnerId(parking.getOwnerId(),parking.getStatus(),true);//校验参数
        PMSUtil.clickStatusAnOwnerId(parking.getStatus(),parking.getOwnerId(),false);
        return parkingMapper.insert(parking);
    }

    @Override
    public int update(Parking parking) {
        clickOwner(parking);//存在用户则校验是否存在
        PMSUtil.clickStatusAnOwnerId(parking.getOwnerId(),parking.getStatus(),true);
        PMSUtil.clickStatusAnOwnerId(parking.getStatus(),parking.getOwnerId(),false);
        return parkingMapper.updateById(parking);
    }

    @Override
    public int delete(Integer id) {
        PUINGUtil.notNullByZero(id, "车位编号不能为空或者小于等于0");
        return parkingMapper.deleteById(id);
    }

    /***
     * 校验用户是否存在
     * @param parking 车位信息
     */
    private Owner clickOwner(Parking parking){
        if(parking !=null && parking.getOwnerId() !=null && parking.getOwnerId()>0){
            Owner owner = ownerService.findById(parking.getOwnerId());
            if(owner==null){
                throw new PermissionException("添加车位失败，该用户不存在！id："+parking.getOwnerId());
            }
            return owner;
        }
        return null;
    }

}
