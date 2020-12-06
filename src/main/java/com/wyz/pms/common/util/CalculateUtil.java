package com.wyz.pms.common.util;

import com.wyz.pms.common.exception.ParameterException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 计算工具类
 * @author puing
 * @date 2020-9-27
 *
 */
public class CalculateUtil {
	
	
	/**
	 * 
	 * @Description:  相乘
	 * @param calculate
	 * @return
	 */
	public static BigDecimal multiply(Calculate calculate) {
		if(calculate==null) {
			throw new ParameterException("计算信息为空  !");
		}
		if(calculate.getDiscount()!=null &&calculate.getDiscount()>0.00) {
			//有折扣
			return multiply(calculate.getCount(), calculate.getUnitPrice(), calculate.getDiscount());
		}else {
			return multiply(calculate.getCount(),calculate.getUnitPrice());
			
		}
	
	}

	/**
	 * @Description:  计算价格
	 * @param count 数量
	 * @param unitPrice 单价
 	 * @return
	 */
	public static BigDecimal multiply(Integer count, BigDecimal unitPrice) {
		PUINGUtil.notNullByZero(count, " 数量不能为空或小于等于0 !");
		PUINGUtil.isEmpty(unitPrice, "单价不能为空或小于等于0  !");
		return unitPrice.multiply(new BigDecimal(count)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 
	 * @Description:  钱数相加
	 * @param augend 被加数
	 * @param addend 加数
	 * @return
	 */
	public static BigDecimal moneyAdd(BigDecimal augend,BigDecimal addend) {
		PUINGUtil.isEmpty(addend, " 加数不能为空或小于等于0  !");
		if(augend==null) {
			throw new ParameterException("被加数不能为空  !");
		}
		return augend.add(addend).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 计算价格
	 * @param count 折扣
	 * @param unitPrice 单价
	 * @param discount 数量
	 * @return
	 */
	public static BigDecimal multiply(Integer count, BigDecimal unitPrice,double discount) {
		BigDecimal discountPrice = unitPrice.multiply(new BigDecimal(discount)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return multiply(count, discountPrice);
	}
	
	/**
	 * @Description:  计算总价格
	 * @param list
	 * @return
	 */
	public static BigDecimal totalPrices(List<Calculate> list) {
		if(list==null || list.size()<=0) {
			throw new ParameterException("  计算信息为空！");
		}
		Integer commodityCount = 0;// 商品数量
		BigDecimal unitPrice = null;// 单价
		BigDecimal money = new BigDecimal(0.00); // 总价
		for (Calculate calculate : list) {
			commodityCount = calculate.getCount();
			unitPrice = calculate.getUnitPrice();
			Double discount = calculate.getDiscount();
			//存在折扣
			if(discount!=null &&discount>0.00) {
				// 将单价和折扣和数量相乘然后+=
				money = moneyAdd(money, multiply(commodityCount, unitPrice,discount));
			}else {
				// 将单价和数量相乘然后+=
				money = moneyAdd(money, multiply(commodityCount, unitPrice));
			}
		}
		return  money;
	}

	
}
