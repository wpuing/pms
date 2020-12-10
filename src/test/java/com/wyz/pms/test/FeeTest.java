package com.wyz.pms.test;

import com.wyz.pms.core.pojo.vo.FeeDetail;
import com.wyz.pms.core.pojo.vo.FeeTypeMoneyVo;
import com.wyz.pms.core.service.FeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FeeTest {

    @Autowired
    private FeeService feeService;


    @Test
    public void selectFeeTypeMoney() {
        String startTime="2020-12-04";
        String endTime="2020-12-08";
        Integer feeTypeId=3;
        Integer ownerId=4;
        List<FeeTypeMoneyVo> list = feeService.find(startTime, endTime, feeTypeId, ownerId);
        list.forEach(System.out::println);
    }

    @Test
    public void selectFeeDetailByOwner() {
        Integer feeTypeId=3;
        Integer ownerId=4;
        Integer status = 1;
        List<FeeDetail> list = feeService.find(feeTypeId, ownerId,status);
        list.forEach(System.out::println);
    }

}
