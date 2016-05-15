package com.xiaogua.better.basic;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class TestBigDecimalCode {
	@Test
	public void testGetWithoutTrailingZeroes() {
		// 清除结尾多余的0
		BigDecimal decimal = new BigDecimal("1.233300e3");
		System.out.println(decimal.toString());
		BigDecimal rtnDecimal = BigDecimalCode.getWithoutTrailingZeroes(decimal);
		Assert.assertEquals("1233.3", rtnDecimal.toString());

		decimal = new BigDecimal("6e2");
		rtnDecimal = BigDecimalCode.getWithoutTrailingZeroes(decimal);
		Assert.assertEquals("600", rtnDecimal.toString());

		String str = "3.1190102E9";
		BigDecimal b = new BigDecimal(str);
		System.out.println(b.toPlainString());

		double doubleValue=3.1190102E9;
		DecimalFormat df = new DecimalFormat("0");
		System.out.println(df.format(doubleValue));
	}

	@Test
	public void testDecimalFormatCustomerStyle() {
		BigDecimal decimal = new BigDecimal("1234567890987654321.01234567");
		DecimalFormat formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.ENGLISH);
		DecimalFormatSymbols custom = new DecimalFormatSymbols();
		custom.setDecimalSeparator('#');// 小数点
		custom.setGroupingSeparator('$');// 整数分隔符
		formatter.setGroupingSize(5);
		formatter.setDecimalFormatSymbols(custom);
		formatter.setMinimumIntegerDigits(1);
		formatter.setMaximumIntegerDigits(10);
		formatter.setMinimumFractionDigits(3);
		formatter.setMaximumFractionDigits(5);
		Assert.assertEquals("09876$54321#01235", formatter.format(decimal));
	}

}
