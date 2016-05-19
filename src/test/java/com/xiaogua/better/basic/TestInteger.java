package com.xiaogua.better.basic;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestInteger {
	@Test
	public void testIntIncrement() {
		int n = 1;
		for (int i = 0; i < 100; i++) {
			n = n++;
			// 第一： n_copy =n；
			// 第二n++
			// 第三n= n_copy
		}
		Assert.assertEquals(1, n);
	}

	@Test
	public void testIntegerOverFlow() {
		// 溢出
		long x = Integer.MAX_VALUE + 1;
		Assert.assertTrue(x < 0);
		long y = Integer.MAX_VALUE + (long) 1;
		Assert.assertTrue(y > 0);
	}

	@Test
	public void testIntegerOverFlow2() {
		int minValue = Integer.MIN_VALUE;
		int maxValue = Integer.MAX_VALUE;
		Assert.assertTrue(maxValue > maxValue + 1);
		Assert.assertEquals(-1, maxValue - minValue);
		Assert.assertEquals(1, minValue - maxValue);
		Assert.assertEquals(maxValue + minValue, maxValue - minValue);
	}

	@Test
	public void testMathAbs() {
		// 溢出
		int intAbs = Math.abs(Integer.MIN_VALUE);
		Assert.assertTrue(intAbs < 0);
	}

	@Test(expected = NumberFormatException.class)
	public void testIntegerValueOfNumberFormatException() {
		Assert.assertEquals(-2147483648, Integer.MIN_VALUE);
		String str = "-2147483649";
		long longRtn = Long.valueOf(str);
		Assert.assertEquals(-2147483649L, longRtn);
		int intRtn = Integer.valueOf(str);
		System.out.println(intRtn);
	}

	@Test
	public void testIntegerCache() {
		Integer a = Integer.valueOf(-127);
		Integer b = Integer.valueOf(-127);
		Integer c = new Integer(-127);
		Assert.assertTrue(a == b);
		Assert.assertFalse(a == c);
	}

	@Test
	public void testChangeIntegerCache() throws Exception {
		Class<?> cache = Integer.class.getDeclaredClasses()[0];
		Field myCache = cache.getDeclaredField("cache");
		myCache.setAccessible(true);
		// 获取Integer中的小整数缓存
		Integer[] newCache = (Integer[]) myCache.get(cache);
		Assert.assertEquals(0, newCache[128].intValue());
		// 修改Integer中的小整数缓存，把4修改为5，使2+2=5
		newCache[132] = newCache[133]; // 5
		int a = 2;
		int b = a + a;
		System.out.printf("%d + %d = %d", a, a, b);
	}

	@Test
	public void testChangeIntegerCache2() throws Exception {
		Class<?> clazz = Class.forName("java.lang.Integer$IntegerCache");
		Field field = clazz.getDeclaredField("cache");
		field.setAccessible(true);
		Integer[] cache = (Integer[]) field.get(clazz);
		// 改变Integer的缓存
		for (int i = 0; i < cache.length; i++) {
			cache[i] = new Integer(new Random().nextInt(cache.length));
		}
		for (int i = 0; i < 10; i++) {
			System.out.println((Integer) i); // 这个时候1不是1 ，2也不是2
		}
	}

	@Test
	public void testConvertObjectToPrimitive() {
		List<Integer> integerList = new ArrayList<Integer>();
		integerList.add(1);
		// 不能有null对象
		// integerList.add(null);
		Integer[] integers = integerList.toArray(new Integer[integerList.size()]);
		int[] ints = ArrayUtils.toPrimitive(integers);
		System.out.println(Arrays.toString(ints));
	}

	@Test
	public void testConvertPrimitiveToObject() {
		int[] ints = { 2 };
		Integer[] integersArr = ArrayUtils.toObject(ints);
		List<Integer> integerList = new ArrayList<Integer>();
		Collections.addAll(integerList, integersArr);
		System.out.println(integerList);
	}

	@Test
	public void testGeIntLength() {
		Assert.assertEquals(3, geIntLength(999));
	}

	@Test
	public void testAddNum() {
		Assert.assertEquals(27, addNum(13, 14));
	}

	@Test
	public void testGetAvg() {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		double avg = arr[0];
		for (int i = 1, len = arr.length; i < len; i++) {
			avg = (avg * i + arr[i]) / (i + 1);
			System.out.println((i + 1) + "," + avg);
		}
	}

	@Test
	public void testSwapInt() {
		swapInt(Integer.MAX_VALUE, Integer.MIN_VALUE);
	}

	public int countNumOf1(int x) {
		int num = 0;
		while (x != 0) {
			x &= (x - 1);
			num++;
		}
		return num;
	}

	public int addNum(int m, int n) {
		int sum = 0;
		int carry = 0;
		do {
			sum = m ^ n;
			carry = (m & n) << 1;
			m = sum;
			n = carry;
		} while (n != 0);
		return m;
	}

	public int geIntLength(int x) {
		final int[] sizeTable = { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE };
		for (int i = 0;; i++) {
			if (x <= sizeTable[i])
				return i + 1;
		}
	}

	/*
	 * 交换2个数
	 */
	public void swapInt(int i, int j) {
		if (i == j)
			return;
		i ^= j;
		j ^= i;
		i ^= j;
		System.out.println(i + "," + j);
	}

	@Test
	public void testGetCapacity() {
		int num = new Random().nextInt(Integer.MAX_VALUE);
		System.out.println(num + "," + getCapacity(num, 1));
		System.out.println(num + "," + closestPowerOf2Number(num));
	}

	@Test
	public void testIntegerNumberOfZeros() {
		int num = 12345678;
		String binStr = Integer.toBinaryString(num);
		System.out.println(binStr);
		// 从最左边算起连续的0的总数量(填充0)
		int result = Integer.numberOfLeadingZeros(num);
		System.out.println(result);
		// 从最右边算起连续的0的总数量
		result = Integer.numberOfTrailingZeros(num);
		System.out.println(result);
	}

	@Test
	public void testIntegerToBinaryString() {
		int num = 1234567;
		// 返回二进制字符串
		String str = Integer.toBinaryString(num);
		System.out.println(str);
		// 二进制串中1的总数量
		int numOf1 = Integer.bitCount(num);
		System.out.println(numOf1 + "," + countNumOf1(num));

		// 二进制转10进制
		int rtnNum = Integer.valueOf(str, 2);
		Assert.assertEquals(num, rtnNum);

		// 负数二进制转为10进制
		num = -1 * num;
		str = Integer.toBinaryString(num);
		long longRtn = Long.parseLong(str, 2);
		rtnNum = (int) longRtn;
		System.out.println(str + "," + rtnNum);
	}

	@Test
	public void testIntegerHex() {
		int intNum = 12345678;
		// 十进制转16进制
		String hexStr = Integer.toHexString(intNum);
		System.out.println(hexStr);

		// 十进制转成8进制
		String octStr = Integer.toOctalString(intNum);
		System.out.println(octStr);

		// 16进制转为10进制
		int rtnNum = Integer.valueOf(hexStr, 16);
		System.out.println(rtnNum);
		// 8进制转为10进制
		rtnNum = Integer.valueOf(octStr, 8);
		System.out.println(rtnNum);

		hexStr = Integer.toHexString(intNum);
		System.out.println(hexStr);
		int rtnInt = Integer.valueOf(hexStr, 16);
		System.out.println(rtnInt);
	}

	@Test
	public void testGetPowerOf2() {
		System.out.println(getPower2(123456));
	}

	@SuppressWarnings("unused")
	@Test
	public void testIntCharValueConvert() {
		char x = 'X';
		int i = 0;
		System.out.println(true ? x : i);// 88
		System.out.println(true ? x : 0);// X
		System.out.println(true ? i : x);// 0
		System.out.println(false ? i : x);// 88
		Assert.assertEquals('\0', (false ? x : 0));
		Assert.assertEquals(0, (true ? i : x));

	}

	@Test
	public void testIntegerSignum() {
		Assert.assertEquals(0, Integer.signum(0));
		Assert.assertEquals(-1, Integer.signum(Integer.MIN_VALUE));
		Assert.assertEquals(1, Integer.signum(Integer.MAX_VALUE));
	}

	@Test
	public void testIntegerParseInt() {
		final String hindiNumber = "१२३४५६७८९०";// 印地语
		int resultNum = Integer.parseInt(hindiNumber);
		Assert.assertEquals(1234567890, resultNum);
	}

	@Test
	public void testPatternMatchInteger() {
		final Pattern digit = Pattern.compile("[0-9]*");
		final Pattern unicode_digit = Pattern.compile("\\p{Nd}*");
		final String hindiNumber = "१२३४५६७८९०";
		System.out.println(String.format("digit match = %s", digit.matcher(hindiNumber).matches()));
		System.out.println(String.format("Unicode digit match = %s", unicode_digit.matcher(hindiNumber).matches()));
	}

	@Test
	public void testConvertedToInt() {
		final short sh1 = -20;
		final int int1 = (int) sh1;

		final char ch1 = (char) sh1;
		final int int2 = (int) ch1;

		System.out.println(String.format("short: %s converted to int: %d", String.valueOf(sh1), int1));
		System.out.println(String.format("char: %s converted to int: %d", String.valueOf(ch1), int2));
		Assert.assertEquals(-20, int1);
		Assert.assertNotEquals(-20, int2);
	}

	@Test
	public void testIntegerComplement() {
		int a = 2;
		int b = ~a;
		// 2补码:0000 0010
		// 2取反后补码 1111 1101
		// 取反后反码 1111 1100
		// 原码 1000 0011
		Assert.assertEquals(-3, b);
	}

	@Test
	public void testIntegerShiftOperation() {
		// 无符号向右移动7位
		System.out.println(0xff >>> 7);// 1
		// 转换为int>>>7
		System.out.println((((byte) 0xff) >>> 7));
		// 低位截取
		System.out.println((byte) (((byte) 0xff) >>> 7));
	}

	@Test
	public void testStrToInteger() {
		String numberStr = "1234";
		int intValue = org.apache.commons.lang3.math.NumberUtils.toInt(numberStr, -1);
		Assert.assertEquals(1234, intValue);

		intValue = org.apache.commons.lang3.math.NumberUtils.toInt(null, -1);
		Assert.assertEquals(-1, intValue);

		// 会删除空白字符,不能为null
		intValue = org.springframework.util.NumberUtils.parseNumber(numberStr, Integer.class);
		Assert.assertEquals(1234, intValue);

		numberStr = " 12 34 ";
		intValue = org.springframework.util.NumberUtils.parseNumber(numberStr, Integer.class);
		Assert.assertEquals(1234, intValue);
	}

	@Test
	public void testAddIntegerWithChar() {
		System.out.println((int) '1');// 49
		System.out.println(1 + '1' + '1' + 1);// 100
		System.out.println('1' + 1 + '1' + 1);// 100
		System.out.println(1 + 1 + "1" + 1);// 211
		System.out.println("1" + 1 + '1' + 1);// 1111
	}

	@Test
	public void testIntegerDivision() {
		int rtn = (-11 % -7);
		System.out.println(rtn);
	}

	@Test
	public void testIntegerOperationPriority() {
		int a = 1;
		int b = 2;
		// ---->
		int rtnInt = (a = 3) + (b = 4) + a * b;
		Assert.assertEquals(19, rtnInt);
		Assert.assertEquals(3, a);
		Assert.assertEquals(4, b);

		a = 2;
		b = 1;
		int c = a > b ? (a = 4) : (b = 3);
		Assert.assertEquals(4, a);
		Assert.assertEquals(1, b);
		Assert.assertEquals(4, c);// 短路
	}

	@Test
	public void testIntegerIncrementAndSum() {
		int a = 1;
		a += a += a++;
		System.out.println(a);
		// 1.a=a+(a+=a++)
		// 2.a=1+(a=a+(a++))
		// 3.a=1+(a=1+(a++))
		// 4.a=1+(a=1+1)
		// 5.a=1+2

		a = 1;
		a += a++;
		System.out.println(a);// 2-->a=1+(a++)

		a = 1;
		a += ++a;
		System.out.println(a);// 3-->a=1+(++a)
	}

	@Test
	public void testIntegerTypeConvert() {
		int a = 10;
		double d = 9.5;
		System.out.println(a > d ? a : d);// 10.0
		System.out.println(3 > 2 ? 'a' : true);// a (Object)

		char charA = 'a';
		int intC = 0;
		System.out.println(true ? charA : 0);// a (0常量)
		System.out.println(false ? intC : charA);// 97
		System.out.println(true ? charA : intC);// 97
	}

	@Test
	public void testIntegerEquals() {
		int a = 10;
		int b = 10;
		double c = 10.0;
		Assert.assertTrue(a == b);// 缓存
		Assert.assertTrue(a == c);// int-->double

		a = 127;
		b = 127;
		Assert.assertTrue(a == b);// 缓存
	}

	public int getUnsignedByte(short data) {
		// 将data字节型数据转换为0~65535
		return data & 0x0FFFF;
	}

	// 得到2的n次方
	public int getPower2(int num) {
		return (int) (Math.log10(num) / Math.log10(2));
	}

	/**
	 * 根据需要存储的元素个数确定HashMap等Map接口实现类的初始容量(使用默认的负载因子：0.75)
	 * 
	 * @param capacity
	 *            需要存储的元素个数
	 * @return
	 */
	public static final int getCapacity(int capacity) {
		return getCapacity(capacity, 0.75f);
	}

	/**
	 * 根据需要存储的元素个数确定HashMap等Map接口实现类的初始容量
	 * 
	 * @param capacity
	 *            需要存储的元素个数
	 * @param loadFactor
	 *            负载因子，必须介于0-1之间，如果不在此范围，内部也不检测，后果自负
	 * @return
	 */
	public static final int getCapacity(int capacity, float loadFactor) {
		int initCapacity = 16;
		while (capacity > initCapacity * loadFactor) {
			initCapacity <<= 1;// 如果默认容量小于指定的期望，则扩大一倍
		}
		return initCapacity;
	}

	public static int closestPowerOf2Number(final int number) {
		return number == 0 ? 0 : 1 << (32 - Integer.numberOfLeadingZeros(number - 1));
	}
}
