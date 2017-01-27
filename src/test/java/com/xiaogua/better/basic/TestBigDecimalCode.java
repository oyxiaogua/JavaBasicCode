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

		double doubleValue = 3.1190102E9;
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

	@Test
	public void testBigDecimalCompare() {
		BigDecimal a = new BigDecimal("1.23");
		BigDecimal b = new BigDecimal("1.230");
		boolean equalRtn = a.equals(b);
		Assert.assertFalse(equalRtn);
		int compareRtn = a.compareTo(b);
		Assert.assertEquals(0, compareRtn);
	}

	@Test
	public void testBigDecimalAdd() {
		BigDecimal a = new BigDecimal("1.23");
		BigDecimal b = new BigDecimal("1.24");
		a.add(b);
		Assert.assertEquals(1.23, a.doubleValue(), 0.0);
	}

	@Test
	public void testBigDecimalDivide() {
		BigDecimal a = new BigDecimal("100");
		BigDecimal b = new BigDecimal("12");
		BigDecimal c = a.divide(b,2, BigDecimal.ROUND_HALF_DOWN);
		System.out.println(c);
	}
	
	@Test(expected=ArithmeticException.class)
	public void testBigDecimalDivideUndefined() {
		BigDecimal a = BigDecimal.ZERO;
		BigDecimal b = BigDecimal.ZERO;
		BigDecimal c=a.divide(b);
		System.out.println(c);
	}

	@Test(expected=ArithmeticException.class)
	public void testBigDecimalDivideNonTerminatingDecimal() {
		BigDecimal a = new BigDecimal("100");
		BigDecimal b = new BigDecimal("12");
		BigDecimal c=a.divide(b);
		System.out.println(c);
	}

}
