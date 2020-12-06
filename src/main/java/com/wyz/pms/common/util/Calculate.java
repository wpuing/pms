package com.wyz.pms.common.util;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 *  @author: PUING
 *  @Date: 2020/11/29 22:29
 *  @Description: 计算器类
 */
public class Calculate implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO  
	 */ 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 单价
	 */
	private BigDecimal unitPrice;
	
	/**
	 * 数量
	 */
	private Integer count;
	
	/**
	 * 折扣
	 */
	private Double discount;

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public Calculate setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
		return this;
	}

	public Integer getCount() {
		return count;
	}

	public Calculate setCount(Integer count) {
		this.count = count;
		return this;
	}


	public Double getDiscount() {
		return discount;
	}

	public Calculate setDiscount(Double discount) {
		this.discount = discount;
		return this;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Calculate [unitPrice=" + unitPrice + ", count=" + count + ", discount=" + discount + "]";
	}

	
	
}
