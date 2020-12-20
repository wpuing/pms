package com.wyz.pms.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Fee implements Serializable {
    /**
     * 收费id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 收费类型id
     */
    @NotNull(message = "收费类型不能为空")
    private Integer feeTypeId;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空")
    private BigDecimal money;

    /**
     * 缴费日期
     */
    @TableField(strategy = FieldStrategy.IGNORED)//设置为IGNORE，不过滤null
    private LocalDateTime payTime;

    /**
     * 缴费方式
     */
    @TableField(strategy = FieldStrategy.IGNORED)//设置为IGNORE，不过滤null
    private String method;

    /**
     * 状态（2已缴费，1未缴费）
     */
    @NotNull(message = "缴费状态不能为空")
    private Integer status;

    /**
     * 业主id
     */
    @NotNull(message = "业主编号不能为空")
    @Min(value = 1)//大于等于1
    private Integer ownerId;

    /**
     * 操作员id
     */
    @TableField(strategy = FieldStrategy.IGNORED)//设置为IGNORE，不过滤null
    private Integer operatorId;

    private static final long serialVersionUID = 1L;

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
        this.method = method == null ? null : method.trim();
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

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", feeTypeId=").append(feeTypeId);
        sb.append(", money=").append(money);
        sb.append(", payTime=").append(payTime);
        sb.append(", method=").append(method);
        sb.append(", status=").append(status);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}