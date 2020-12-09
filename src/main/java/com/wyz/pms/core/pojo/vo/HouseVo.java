package com.wyz.pms.core.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class HouseVo implements Serializable {

    /**
     * 房产id
     */
    private Integer id;

    /**
     * 编号
     */
    private String number;

    /**
     * 单元
     */
    private String unitSum;

    /**
     * 层数
     */
    private String layer;

    /**
     * 房产类型
     */
    private String houseType;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 状态（1待售，2已售）
     */
    private Integer status;

    /**
     * 业主id
     */
    private Integer ownerId;

    /**
     * 业主名称
     */
    private String ownerName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUnitSum() {
        return unitSum;
    }

    public void setUnitSum(String unitSum) {
        this.unitSum = unitSum;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "HouseVo{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", unitSum='" + unitSum + '\'' +
                ", layer='" + layer + '\'' +
                ", houseType='" + houseType + '\'' +
                ", area=" + area +
                ", status=" + status +
                ", ownerId=" + ownerId +
                ", ownerName=" + ownerName +
                '}';
    }
}
