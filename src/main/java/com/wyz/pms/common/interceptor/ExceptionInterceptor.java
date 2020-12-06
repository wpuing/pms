package com.wyz.pms.common.interceptor;

import com.wyz.pms.common.exception.OperationException;
import com.wyz.pms.common.exception.ParameterException;
import com.wyz.pms.common.exception.PermissionException;
import com.wyz.pms.common.util.Result;
import com.wyz.pms.common.util.ResultCode;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description: 自定义异常拦截器
 * @author puing
 * @date 2020年8月2日
 *
 */
@RestControllerAdvice
public class ExceptionInterceptor {

	/**
	 * @Title: operationException
	 * @Description: 操作异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(OperationException.class)
	public Result operationException(OperationException e) {
		return new Result()
				.setCode(ResultCode.INTERNAL_SERVER_ERROR)
				.setMessage(e.getMessage());
	}

	/**
	 * 
	 * @Title: parameterException
	 * @Description: 参数异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ParameterException.class)
	public Result parameterException(ParameterException e) {
		return new Result()
				.setCode(ResultCode.FAIL)
				.setMessage(e.getMessage());
	}

	/**
	 * 
	 * @Title: permissionException
	 * @Description: 权限异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(PermissionException.class)
	public Result permissionException(PermissionException e) {
		return new Result()
				.setCode(ResultCode.ACCESS_PERMISSION_ERROR)
				.setMessage(e.getMessage());
	}


	/**
	 * 
	 * @Description:  空指针
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	public Result nullPointerException(PermissionException e) {
		return new Result()
				.setCode(ResultCode.INTERNAL_SERVER_ERROR)
				.setMessage("空指针异常, "+e.getMessage());
	}

	/**
	 * 拦截所有运行时的全局异常   
	 */
	@ExceptionHandler(RuntimeException.class)
	public Result runtimeException(RuntimeException e) {
		return new Result()
				.setCode(ResultCode.INTERNAL_SERVER_ERROR)
				.setMessage(e.getMessage());
	}

	/**
	 * 系统异常捕获处理
	 */
	@ExceptionHandler(Exception.class)
	public Result exception(Exception e) {
		return new Result()
				.setCode(ResultCode.INTERNAL_SERVER_ERROR)
				.setMessage(e.getMessage());
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}
		});
		binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}
		});
		binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm:ss")));
			}
		});
	}
}
