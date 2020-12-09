package com.wyz.pms.core.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 *  @author: PUING
 *  @Date: 2020/12/8 12:29
 *  @Description: 车位视图类
 */
public class ParkingVo implements Serializable {

    /**
     * 车位id
     */
    private Integer id;

    /**
     * 编号
     */
    private String number;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 状态（1已售，0未售）
     */
    private Integer status;

    /**
     * 业主id
     */
    private Integer ownerId;

    /**
     * 业主姓名
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        return "ParkingVo{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
