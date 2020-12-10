package com.wyz.pms.core.pojo.vo;

import java.io.Serializable;
import java.util.List;

public class FeeDetailVo implements Serializable {

    private String feeInfo;

    private List<FeeDetail> detailList;

    public FeeDetailVo() {
    }

    public FeeDetailVo(String feeInfo, List<FeeDetail> detailList) {
        this.feeInfo = feeInfo;
        this.detailList = detailList;
    }

    public String getFeeInfo() {
        return feeInfo;
    }

    public void setFeeInfo(String feeInfo) {
        this.feeInfo = feeInfo;
    }

    public List<FeeDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<FeeDetail> detailList) {
        this.detailList = detailList;
    }
}
