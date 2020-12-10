package com.wyz.pms.core.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class FeeTypeMoneyVo implements Serializable {

    /**
     * 收费类型id
     */
    private Integer feeTypeId;

    /**
     * 类型名
     */
    private String feeTypeName;

    /**
     * 业主编号
     */
    private Integer ownerId;

    /**
     * 业主姓名
     */
    private String ownerName;

    /**
     * 总金额
     */
    private BigDecimal totalMoney;

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

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "FeeTypeMoney{" +
                "feeTypeId=" + feeTypeId +
                ", feeTypeName='" + feeTypeName + '\'' +
                ", ownerId=" + ownerId +
                ", ownerName=" + ownerName +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
