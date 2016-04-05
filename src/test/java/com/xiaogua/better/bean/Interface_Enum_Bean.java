package com.xiaogua.better.bean;

import org.apache.commons.lang3.StringUtils;

public interface Interface_Enum_Bean {
	enum Coffee implements Interface_Enum_Bean {
		BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO;
		public String toString() {
			return "category coffer,name=" + name();
		}

		public static Coffee getEnum(String value) {
			if (StringUtils.isBlank(value)) {
				throw new IllegalArgumentException();
			}
			if (value.indexOf("name=") > 0) {
				String enumName = value.substring(value.lastIndexOf("name=") + "name=".length());
				return Coffee.valueOf(enumName);
			}
			throw new IllegalArgumentException();
		}

	}

	enum Dessert implements Interface_Enum_Bean {
		FRUIT, CAKE, GELATO;
		public String toString() {
			return "category dessert,name=" + name();
		}
	}
}
