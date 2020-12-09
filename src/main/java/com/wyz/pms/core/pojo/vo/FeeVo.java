package com.wyz.pms.core.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FeeVo implements Serializable {

    /**
     * 收费id
     */
    private Integer id;

    /**
     * 收费类型id
     */
    private Integer feeTypeId;

    /**
     * 收费类型名
     */
    private String feeTypeName;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 缴费日期
     */
    private LocalDateTime payTime;

    /**
     * 缴费方式
     */
    private String method;

    /**
     * 状态（2已缴费，1未缴费）
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

    /**
     * 操作员id
     */
    private Integer operatorId;

    /**
     * 操作员姓名
     */
    private String operatorName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(Integer feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public String getFeeTypeName() {
        return feeTypeName;
    }

    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        return "FeeVo{" +
                "id=" + id +
                ", feeTypeId=" + feeTypeId +
                ", feeTypeName=" + feeTypeName +
                ", money=" + money +
                ", payTime=" + payTime +
                ", method='" + method + '\'' +
                ", status=" + status +
                ", ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                '}';
    }
}
