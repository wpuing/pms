package com.wyz.pms.common.util;

import com.wyz.pms.common.exception.OperationException;
import com.wyz.pms.common.exception.ParameterException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author puing
 * @Description: 工具类
 * @date 2019年11月27日
 */
public class PUINGUtil {

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };

	/**
	 * @return 随机码
	 * @Title: generateShortUuid
	 * @Description: 生成8位UUID大写随机码
	 */
	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		// 得到随机值
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		// 转换大写
		uuid = shortBuffer.toString().toUpperCase();
		return uuid;
	}

	/**
	 * @return 16位随机码
	 * @Title: getRandomIdByUtil
	 * @Description: 生成16位UUID随机码
	 */
	public static String getRandomIdByUtil() {
		String randomId = UUID.randomUUID().toString().replace("-", "");
		return randomId;
	}

	/**
	 * @param number
	 * @param message
	 * @Description: 数字不为空且大于0
	 */
	public static void notNullByZero(Integer number, String message) {
		if (number == null || number <= 0) {
			throw new ParameterException(message);
		}
	}

	/**
	 * @param numbers 数值
	 * @param message 消息
	 * @Description: 数字不为空且大于0
	 */
	public static void notNullByInt(String message, Integer... numbers) {
		if (numbers != null) {
			for (Integer number : numbers) {
				if (number == null || number <= 0) {
					throw new ParameterException(message);
				}
			}
		} else {
			throw new ParameterException("校验数字参数为空！");
		}
	}

	/**
	 * @param message
	 * @param numbers
	 * @Description: 数字不为空且大于0
	 */
	public static void notNullByZeroLong(String message, Long... numbers) {
		if (numbers == null) {
			throw new ParameterException("校验数字参数为空！");
		}
		// 遍历
		for (Long number : numbers) {
			// 判断
			if (number == null || number <= 0) {
				throw new ParameterException(message);
			}
		}
	}

	/**
	 * @param numbers
	 * @Description: 数字不为空
	 */
	public static void notNullLong(Long... numbers) {
		if (numbers == null) {
			throw new ParameterException("校验数字参数为空！");
		}
		// 遍历
		for (Long number : numbers) {
			// 判断
			if (number == null) {
				throw new ParameterException("参数不能为空");
			}
		}
	}

	public static void isEmptys(String... strs) {
		if (strs == null) {
			throw new ParameterException("校验的字符参数为空！");
		}
		for (String str : strs) {
			// 判断
			isEmpty("参数不能为空", str);
		}
	}

	/**
	 * @param str 带数字的字符串
	 * @return 提取后的整数集合
	 * @Description: 截取字符串并提取数字
	 */
	public static List<Integer> getNumber(String str) {
		Assert.hasLength(str.trim(), "字符数据不能为空");
		List<Integer> list = new ArrayList<Integer>();
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		String[] strs = str.split(",");
		for (int i = 0, len = strs.length; i < len; i++) {
			Matcher m = p.matcher(strs[i].toString());
			list.add(Integer.parseInt(m.replaceAll("").trim()));
		}
		return list;
	}

	/**
	 * 提取字符串
	 * 
	 * @param str 字符串
	 * @return
	 */
	public static List<String> getStr(String str) {
		if (str == null || StringUtils.isEmpty(str.trim())) {
			throw new ParameterException("要提取字符串为空！");
		}
		List<String> list = new ArrayList<>();
		String[] strs = str.split(",");
		for (String string : strs) {
			list.add(string.trim());
		}
		return list;
	}

	/**
	 * 加密
	 * 
	 * @return 加密过后的密码
	 * @Title: getMD5Value
	 */
//	public static String getMD5Value(String password, String salt) {
//		Object hash = new SimpleHash("MD5", password.trim(), salt.trim(), 49);
//		return hash.toString();
//	}

	/**
	 * 判断list集合是否为空。为空抛出异常
	 * 
	 * @param T
	 * @param message
	 * @param <T>
	 */
	public static <T> void notNullByList(List<T> T, String message) {
		if (T.size() == 0 || T == null) {
			throw new OperationException(message);
		}
	}

	/**
	 * 判断是否为空
	 * 
	 * @param message 错误消息
	 * @param str     字符串
	 */
	public static void isEmpty(String message, String str) {

		if (str == null || StringUtils.isEmpty(str.trim())) {
			if (message == null || StringUtils.isEmpty(message.trim())) {
				throw new ParameterException("输入的字符为空");
			} else {
				throw new ParameterException(message);
			}
		}
	}

	/**
	 * 判断价格是否小于等于0
	 * 
	 * @Description:
	 * @param decimal 价格
	 */
	public static void isEmpty(BigDecimal decimal, String message) {
		if (decimal == null || decimal.compareTo(new BigDecimal("0.00")) <= 0) {
			if (PUINGUtil.isEmpty(message)) {
				throw new ParameterException("价格为空或者小于等于0！");
			} else {
				throw new ParameterException(message);
			}
		}

	}

	/**
	 * 判断是否为空
	 * 
	 * @param str 字符串
	 */
	public static boolean isEmpty(String str) {
		if (str == null || StringUtils.isEmpty(str.trim())) {
			return false;
		}
		return true;
	}

	/**
	 * 提取判断存在则追加
	 * 
	 * @param str1 原数据
	 * @param str2 要添加的数据
	 * @return 追加后的新集合
	 */
	public static List<String> checkAndAppend(String str1, String str2) {
		int flag = 0;
		List<String> str1_list = getStr(str1);// 原集合
		List<String> str2_list = getStr(str2);// 要比较的集合
		for (String str2_data : str2_list) {// 遍历要比较的集合
			for (String str1_data : str1_list) {// 遍历原集合
				if (str2_data.equals(str1_data)) {// 用要比较的字符串与原集合的元素比较，正确则为true
					flag = 1;
				}
			}
			if (flag == 0) {// 不同为0则往原集合追加
				str1_list.add(str2_data);
			}
			flag = 0;// 重置鉴别条件
		}

		return str1_list;// 返回

	}

	/***
	 * 根据字符串集合转化为字符串，以英文“ , ”分隔
	 * 
	 * @param list 要转化的集合
	 * @return
	 */
	public static String convertStrings(List<String> list) {
		StringBuffer sb = new StringBuffer();
		for (String id : list) {
			sb.append(id + ",");
		}
		return sb.toString();
	}

	/***
	 * 得到当前年月的字符串格式
	 * 
	 * @return
	 */
	public static String getYearMonth() {
		Calendar c1 = Calendar.getInstance();// 使用默认时区和区域设置获取日历。
		String year = String.valueOf(c1.get(Calendar.YEAR));// 得到年
		String month = null;
		int m = c1.get(Calendar.MONTH) + 1;// 得到月
		if (m == 10 || m == 11 || m == 12) {// 格式化
			month = m + "";
		} else {
			month = "0" + m;
		}
		return year + month;
	}

	/**
	 * @Description: 拼接模糊查询
	 * @param condition 条件
	 * @return 拼接过后的条件
	 */
	public static String like(String condition) {
		return "%" + condition + "%";
	}

	/**
	 * @Description: 正则校验手机号
	 * @param mobile 手机号
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 
	 * @Description: 获得某年某月第一天和最后一天
	 * @param year  年份
	 * @param month 月份
	 * @return 开始时间，终止时间
	 */
	public static String[] always(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 最大
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 存入数组
		int[] dateArr = { firstDay, maxDay };
		// 定义时间数组
		String[] dates = new String[dateArr.length];
		for (int i = 0; i < dateArr.length; i++) {
			// 设置日历中月份的最小天数
			cal.set(Calendar.DAY_OF_MONTH, dateArr[i]);
			// 格式化日期
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dates[i] = sdf.format(cal.getTime());
		}
		return dates;

	}

	/**
	 * @Description: 提取第一天和最后一天 字符串格式
	 * @param date 指定时间
	 * @return
	 */
	public static String[] getAlwaysFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String str = sdf.format(date);
		String[] days = str.split("-");
		int year = Integer.parseInt(days[0]);
		int month = Integer.parseInt(days[1]);
		return always(year, month);
	}

	/**
	 * @Description: 提取第一天和最后一天 date格式
	 * @param date
	 * @return
	 */
	public static Date[] getAlways(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateStrs = getAlwaysFormat(date);
		Date[] dates = new Date[2];
		Date newDate = null;
		for (int i = 0; i < dates.length; i++) {
			try {
				newDate = sdf.parse(dateStrs[i]);
			} catch (ParseException e) {
				throw new OperationException("日期时间转换有误，请检查日期时间格式是否正确 ，时间：" + date + " ,异常信息：" + e.getMessage());
			}
			dates[i] = newDate;
		}
		return dates;
	}



}
