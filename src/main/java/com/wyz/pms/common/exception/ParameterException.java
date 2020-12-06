package com.wyz.pms.common.exception;

/**
 * @Description: 参数异常类
 * @author puing
 * @date 2020年11月28日
 */
public class ParameterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ParameterException(String mesaage) {
		super(mesaage);
	}

}
