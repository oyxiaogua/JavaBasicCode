package com.xiaogua.better.datetime;

import java.util.Calendar;

public enum Enum_Date_Dimension {
	DAY {
		String getFormatStr() {
			return "yyyyMMdd";
		}

		int getInterval() {
			return Calendar.DATE;
		}
	},
	MONTH {
		String getFormatStr() {
			return "yyyyMM";
		}

		int getInterval() {
			return Calendar.MONTH;
		}
	},
	YEAR {
		String getFormatStr() {
			return "yyyy";
		}

		int getInterval() {
			return Calendar.YEAR;
		}
	};
	abstract String getFormatStr();

	abstract int getInterval();
}
