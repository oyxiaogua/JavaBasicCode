package com.xiaogua.better.algorithm;

import org.apache.commons.lang3.tuple.Pair;

import com.xiaogua.better.basic.NumberCode;

public class NumbericalAlgorithmCode {
	/**
	 * x乘以y
	 * 限制：y >= 0
	 * 原理:         | (x*(y>>1))<<1      y偶数
	 *      x * y = | x + (x*(y>>1))<<1  y奇数
	 *              | 0                  y == 0
	 */
	public static int doMuliply(int x, int y) {
		if(y<0){
			throw new IllegalArgumentException("y>=0");
		}
		if (y == 0){
			return 0;
		}
		int z = doMuliply(x, y >> 1);
		return NumberCode.isEven(y) ? z << 1 : x + (z << 1);
	}
	
	/**
	 * x除以y
	 * 限制：x > 0 ，y > 0
	 *
	 *              | ArithmeticException                     y == 0
	 * 原理                         | (0, 0)                                  x == 0
	 *      x / y = | (q'<<1, r'<<1) 其中(q',r')=(x>>1)/y      x偶数
	 *              | (q'<<1, r'<<1 + 1) 其中(q',r')=(x>>1)/y  x奇数
	 * @return : 商，余
	 */
	public static Pair<Integer, Integer> doDivide(int x, int y) {
		if (x < 0) {
			throw new IllegalArgumentException("x>=0");
		}
		if (y < 0) {
			throw new IllegalArgumentException("y>0");
		}
		if (y == 0) {
			throw new ArithmeticException();
		}
		if (x == 0) {
			return Pair.of(0, 0);
		}
		Pair<Integer, Integer> half = doDivide(x >> 1, y);
		int left = half.getLeft() << 1;
		int right = half.getRight() << 1;
		if (NumberCode.isOdd(x))
			right += 1;
		return (right < y) ? Pair.of(left, right) : Pair.of(left + 1, right - y);
	}
	
	/**
	 * pow(x, y) % N
	 * 限制：x > 0, y > 0, N > 1
	 *
	 * 原理:                  | (pow(x, y>>1) << 1) % N       y是偶数
	 *        pow(x, y) % N =|
	 *                       | x * (pow(x, y>>1) << 1) % N   y是奇数
	 */
	public static int powerMod(int x, int y, int N) {
		if (x < 0) {
			throw new IllegalArgumentException("x>0");
		}
		if (y < 0) {
			throw new IllegalArgumentException("y>0");
		}
		if (N <= 1) {
			throw new IllegalArgumentException("N>1");
		}
		if (y == 0) {
			return 1;
		}
		int half = powerMod(x, y >> 1, N);
		return NumberCode.isEven(y) ? (half * half) % N : (x * half * half) % N;
	}
	
	
}
