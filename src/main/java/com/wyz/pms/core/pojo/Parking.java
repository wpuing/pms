package com.wyz.pms.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class Parking implements Serializable {
    /**
     * 车位id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    @NotNull(message = "车位编号不能为空")
    private String number;

    /**
     * 价格
     */
    @NotNull(message = "车位价格不能为空")
    private BigDecimal price;

    /**
     * 状态（2已售，1未售）
     */
    @NotNull(message = "车位状态不能为空")
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}