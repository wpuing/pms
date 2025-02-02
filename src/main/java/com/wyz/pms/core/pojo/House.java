package com.wyz.pms.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class House implements Serializable {
    /**
     * 房产id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    @NotNull(message = "房产编号不能为空")
    private String number;

    /**
     * 单元
     */
    @NotNull(message = "单元不能为空")
    private String unitSum;

    /**
     * 层数
     */
    @NotNull(message = "楼层数不能为空")
    private String layer;

    /**
     * 房产类型
     */
    @NotNull(message = "房产类型不能为空")
    private String houseType;

    /**
     * 面积
     */
    @NotNull(message = "房产面积不能为空")
    private BigDecimal area;

    /**
     * 状态（1待售，2已售）
     */
    @NotNull(message = "房产状态不能为空")
    private Integer status;

    /**
     * 业主id
     */
    @TableField(strategy = FieldStrategy.IGNORED)//设置为IGNORE，不过滤null
    private Integer ownerId;

    private static final long serialVersionUID = 1L;

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
        this.number = number == null ? null : number.trim();
    }

    public String getUnitSum() {
        return unitSum;
    }

    public void setUnitSum(String unitSum) {
        this.unitSum = unitSum == null ? null : unitSum.trim();
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer == null ? null : layer.trim();
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", unitSum=").append(unitSum);
        sb.append(", layer=").append(layer);
        sb.append(", houseType=").append(houseType);
        sb.append(", area=").append(area);
        sb.append(", status=").append(status);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}