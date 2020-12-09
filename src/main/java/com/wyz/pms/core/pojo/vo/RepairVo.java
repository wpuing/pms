package com.wyz.pms.core.pojo.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RepairVo implements Serializable {

    /**
     * 维修id
     */
    private Integer id;

    /**
     * 维修物品
     */
    private String item;

    /**
     * 地点
     */
    private String site;

    /**
     * 原因
     */
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
    private Integer status;

    /**
     * 报修人id
     */
    private Integer ownerId;

    /**
     * 报修人姓名
     */
    private String ownerName;

    /**
     * 处理人
     */
    private String employeeName;

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
        this.item = item;
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
        this.reason = reason;
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

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "RepairVo{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", site='" + site + '\'' +
                ", reason='" + reason + '\'' +
                ", createTime=" + createTime +
                ", resolveTime=" + resolveTime +
                ", status='" + status + '\'' +
                ", ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", employeeName='" + employeeName + '\'' +
                '}';
    }
}
