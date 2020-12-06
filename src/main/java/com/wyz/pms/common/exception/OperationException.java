package com.wyz.pms.common.exception;

/**
 * @Description: 操作异常类
 * @author puing
 * @date 2020年11月28日
 */
public class OperationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public OperationException(String message) {
		
		super(message);
	}

	
}
