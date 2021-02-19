package com.xsl.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p> 内容描述 : 小数计算器</p> 
 * <p> 修改日期： 2016年8月1日 下午9:06:40 </p>
 * @author yuewangh
 * @version V1.0
 */
public class BigDecimalArith {
	static DecimalFormat df = new DecimalFormat("0.00");

	// 增加输入3位小数的格式，konglc，2016-10-16
	static DecimalFormat df3 = new DecimalFormat("0.###");

	/**
	 * 提供精确加法计算的compare方法
	 * @param value1=v1 = 0
	 * @param value1 <v2 = -1
	 * @return v1>v2 = 1
	 */
	public static int compare(String value1, String value2) {
		value1 = nullto0(value1);
		value2 = nullto0(value2);
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return b1.compareTo(b2);
	}

	/**
	 * 提供精确加法计算的add方法
	 * @param value1 被加数
	 * @param value2 加数
	 * @return 两个参数的和
	 */
	public static String add(String value1, String value2) {
		value1 = nullto0(value1);
		value2 = nullto0(value2);
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return df.format(b1.add(b2));
	}

	/**
	 * 提供精确减法运算的sub方法
	 * @param value1 被减数
	 * @param value2 减数
	 * @return 两个参数的差
	 */
	public static String sub(String value1, String value2) {
		value1 = nullto0(value1);
		value2 = nullto0(value2);
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return df.format(b1.subtract(b2));
	}

	/**
	 * 提供精确减法运算的sub方法，保留3位小数
	 * @param value1 被减数
	 * @param value2 减数
	 * @return 两个参数的差
	 */
	public static String subAndReturn3Digit(String value1, String value2) {
		value1 = nullto0(value1);
		value2 = nullto0(value2);
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return df3.format(b1.subtract(b2));
	}

	/**
	 * 提供精确乘法运算的mul方法
	 * @param value1  被乘数
	 * @param value2 乘数
	 * @return 两个参数的积
	 */
	public static String mul(String value1, String value2) {
		value1 = nullto0(value1);
		value2 = nullto0(value2);
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return df.format(b1.multiply(b2));
	}

	/**
	 * 提供精确的除法运算方法div
	 * @param value1  被除数
	 * @param value2 除数
	 * @return 两个参数的商
	 * @throws IllegalAccessException
	 */
	public static String div(String value1, String value2) {
		value1 = nullto0(value1);
		value2 = nullto0(value2);
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return df.format(b1.divide(b2, 2, BigDecimal.ROUND_HALF_EVEN));
	}

	/**
	 * 提供精确的除法运算方法div取整取整取整
	 * @param value1 被除数
	 * @param value2 除数
	 * @return 取整
	 * @throws IllegalAccessException
	 */
	public static String divide(String value1, String value2) {
		value1 = nullto0(value1);
		value2 = nullto0(value2);
		BigDecimal b1 = new BigDecimal(value1);
		BigDecimal b2 = new BigDecimal(value2);
		return String.valueOf(b1.divide(b2, 0, BigDecimal.ROUND_FLOOR));
	}

	public static void main(String[] args) throws Exception {
//		System.out.println(compare("2", "0"));
//		System.out.println(compare("-2", "0"));
//		System.out.println(compare("0", "0"));
		System.out.println(add("12", "-1"));
	}
	public static String nullto0(String value){
		if(value == null || value.equals("")){
			return "0";
		} else{
			return value;
		}
	}
}
