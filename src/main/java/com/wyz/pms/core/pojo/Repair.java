package com.wyz.pms.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Repair implements Serializable {
    /**
     * 维修id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 维修物品
     */
    @NotNull(message = "维修物品名不能为空")
    private String item;

    /**
     * 地点
     */
    @NotNull(message = "地点不能为空")
    private String site;

    /**
     * 原因
     */
    @NotNull(message = "维修原因不能为空")
    private String reason;

    /**
     * 报修时间
     */
    private LocalDateTime createTime;

    /**
     * 解决时间
     */
    private LocalDateTime resolveTime;

    /**
     * 状态（0未受理 1已受理(未解决) 2已解决）
     */
    @NotNull(message = "状态不能为空")
    private String status;

    /**
     * 报修人id
     */
    @TableField(strategy = FieldStrategy.IGNORED)//设置为IGNORE，不过滤null
    private Integer ownerId;

    /**
     * 处理人
     */
    @TableField(strategy = FieldStrategy.IGNORED)//设置为IGNORE，不过滤null
    private String employeeName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getResolveTime() {
        return resolveTime;
    }

    public void setResolveTime(LocalDateTime resolveTime) {
        this.resolveTime = resolveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", item=").append(item);
        sb.append(", site=").append(site);
        sb.append(", reason=").append(reason);
        sb.append(", createTime=").append(createTime);
        sb.append(", resolveTime=").append(resolveTime);
        sb.append(", status=").append(status);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", employeeName=").append(employeeName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}