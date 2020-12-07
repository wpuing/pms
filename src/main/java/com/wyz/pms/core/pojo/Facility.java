package com.wyz.pms.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Facility implements Serializable {
    /**
     * 设施id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 设施名
     */
    @NotNull(message = "设施名称不能为空")
    private String name;

    /**
     * 数量
     */
    @NotNull(message = "设施数量不能为空")
    private Integer count;

    /**
     * 地点
     */
    private String site;

    /**
     * 使用日期
     */
    @TableField(strategy = FieldStrategy.IGNORED)//设置为IGNORE，不过滤null
    private LocalDateTime startTime;

    /**
     * 设施状态
     */
    private String status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", count=").append(count);
        sb.append(", site=").append(site);
        sb.append(", startTime=").append(startTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}