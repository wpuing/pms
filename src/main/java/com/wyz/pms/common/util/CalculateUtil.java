package com.wyz.pms.common.util;

import com.wyz.pms.common.exception.ParameterException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 计算工具类
 * @author puing
 * @date 2020-12-07
 *
 */
public class CalculateUtil {
	
	/**
	 * 
	 * @Description:  钱数相加
	 * @param augend 被加数
	 * @param addend 加数
	 * @return
	 */
	public static BigDecimal moneyAdd(BigDecimal augend,BigDecimal addend) {
//		PUINGUtil.isEmpty(addend, " 加数不能为空或小于等于0  !");
//		if(augend==null) {
//			throw new ParameterException("被加数不能为空  !");
//		}
		return augend.add(addend).setScale(2, BigDecimal.ROUND_HALF_UP);
	}


	
}
