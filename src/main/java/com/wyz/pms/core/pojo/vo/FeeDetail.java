package com.wyz.pms.core.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FeeDetail implements Serializable {
    /**
     * 收费id
     */
    private Integer feeId;

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
     * 业主姓名
     */
    private String ownerName;

    /**
     * 操作员姓名
     */
    private String operatorName;

    public Integer getFeeId() {
        return feeId;
    }

    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        return "FeeDetail{" +
                "feeId=" + feeId +
                ", feeTypeName='" + feeTypeName + '\'' +
                ", money=" + money +
                ", payTime=" + payTime +
                ", method='" + method + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", operatorName='" + operatorName + '\'' +
                '}';
    }
}
