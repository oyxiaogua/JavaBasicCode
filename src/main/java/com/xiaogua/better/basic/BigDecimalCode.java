package com.xiaogua.better.basic;

import java.math.BigDecimal;

public class BigDecimalCode {
	public static BigDecimal getWithoutTrailingZeroes(final BigDecimal aValue) {
		if (aValue == null)
			return null;
		// stripTrailingZeros does not work for "0"!
		if (BigDecimal.ZERO.compareTo(aValue) == 0)
			return BigDecimal.ZERO;
		final BigDecimal ret = aValue.stripTrailingZeros();
		// Avoid stuff like "6E2"
		return ret.scale() >= 0 ? ret : ret.setScale(0);
	}

}
