package com.wyz.pms.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.pms.common.exception.ParameterException;
import com.wyz.pms.common.exception.PermissionException;
import com.wyz.pms.common.util.PMSUtil;
import com.wyz.pms.common.util.PUINGUtil;
import com.wyz.pms.core.mapper.HouseMapper;
import com.wyz.pms.core.pojo.House;
import com.wyz.pms.core.pojo.Owner;
import com.wyz.pms.core.pojo.vo.HouseVo;
import com.wyz.pms.core.service.HouseService;
import com.wyz.pms.core.service.OwnerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private OwnerService ownerService;

    @Override
    public HouseVo findById(Integer id) {
        PUINGUtil.notNullByZero(id, "房产编号不能为空或者小于等于0");
        House house = houseMapper.selectById(id);
        HouseVo houseVo = new HouseVo();
        BeanUtils.copyProperties(house,houseVo);//拷贝
        Owner owner = clickOwner(house);
        if(owner!=null){//存在用户
            houseVo.setOwnerName(owner.getName());//添加用户名
        }
        return houseVo;
    }

    @Override
    public List<HouseVo> find(BigDecimal startArea, BigDecimal endArea,House house, Integer sort) {
        List<HouseVo> houseVos= new ArrayList<>();
        LambdaQueryWrapper<House> wrapper = Wrappers.<House>lambdaQuery();
        if (house != null) {
            if (PUINGUtil.isEmpty(house.getNumber())) {//编号
                wrapper.like(House::getNumber, house.getNumber());
            }
            if (PUINGUtil.isEmpty(house.getUnitSum())) {//单元
                wrapper.like(House::getUnitSum, house.getUnitSum());
            }
            if (PUINGUtil.isEmpty(house.getLayer())) {//层数
                wrapper.like(House::getLayer, house.getLayer());
            }
            if (PUINGUtil.isEmpty(house.getHouseType())) {//房产类型
                wrapper.like(House::getHouseType, house.getHouseType());
            }
            if (house.getOwnerId()!=null && house.getOwnerId()>0) {//业主id
                wrapper.eq(House::getOwnerId, house.getOwnerId());
            }
            if (house.getStatus()!=null && house.getStatus()>0) {//状态
                wrapper.eq(House::getStatus, house.getStatus());
            }
        }
        if (startArea != null && startArea.compareTo(new BigDecimal("0.00")) > 0) {
            wrapper.ge(House::getArea,startArea);//大于等于
        }
        if (endArea != null && endArea.compareTo(new BigDecimal("0.00")) > 0) {
            wrapper.le(House::getArea,endArea);//小于等于
        }
        if(sort!=null && sort==1){//排序
            wrapper.orderByAsc(House::getId);
        }else {
            wrapper.orderByDesc(House::getId);
        }
        List<House> houses = houseMapper.selectList(wrapper);
        if(houses!=null && houses.size()>0){//存在车位
            for (House h:houses) {
                HouseVo houseVo = new HouseVo();//创建组合对象
                BeanUtils.copyProperties(h,houseVo);//拷贝
                Owner owner = clickOwner(h);//检查是否存在该用户
                if(owner!=null){//存在用户
                    houseVo.setOwnerName(owner.getName());//添加用户名
                }
                houseVos.add(houseVo);//将组合对象放到集合中
            }
        }
        return houseVos;
    }

    @Override
    public int insert(House house) {
        clickOwner(house);//校验用户id
        PMSUtil.clickStatusAnOwnerId(house.getOwnerId(),house.getStatus(),true);//校验参数
        PMSUtil.clickStatusAnOwnerId(house.getStatus(),house.getOwnerId(),false);
        House house1 = findByNumber(house.getNumber());
        if(house1!=null){
            throw new ParameterException("该编号所属房产已存在！！！ id："+house1.getNumber());
        }
        return houseMapper.insert(house);
    }

    @Override
    public int update(House house) {
        clickOwner(house);//校验用户id
        PMSUtil.clickStatusAnOwnerId(house.getOwnerId(),house.getStatus(),true);//校验参数
        PMSUtil.clickStatusAnOwnerId(house.getStatus(),house.getOwnerId(),false);
        House house1 = findByNumber(house.getNumber());
        if(house1!=null && house1.getId()!=house.getId()){
            throw new ParameterException("该编号所属房产已存在！！！修改失败 id："+house1.getNumber());
        }
        return houseMapper.updateById(house);
    }

    @Override
    public int delete(Integer id) {
        PUINGUtil.notNullByZero(id, "房产编号不能为空或者小于等于0");
        return houseMapper.deleteById(id);
    }

    /***
     * 校验用户是否存在
     * @param house 车位信息
     */
    private Owner clickOwner(House house){
        if(house !=null && house.getOwnerId() !=null && house.getOwnerId()>0){
            Owner owner = ownerService.findById(house.getOwnerId());
            if(owner==null){
                throw new PermissionException("添加房产失败，该用户不存在！id："+house.getOwnerId());
            }
            return owner;
        }
        return null;
    }

    private House findByNumber(String number){
        LambdaQueryWrapper<House> wrapper = Wrappers.<House>lambdaQuery();
        wrapper.eq(House::getNumber,number);
        List<House> list = houseMapper.selectList(wrapper);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
