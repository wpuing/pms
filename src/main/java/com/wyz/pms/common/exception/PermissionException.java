package com.wyz.pms.common.exception;

/**
 * @Description: 权限异常类 
 * @author puing
 * @date 2020年11月28日
 */
public class PermissionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public PermissionException(String message) {
		super(message);
	}

}
