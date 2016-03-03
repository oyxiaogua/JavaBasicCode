package com.xiaogua.better.bean;

public enum Public_Enum_Gender {
	MALE(0, "man") {
		public String getGenderInfo() {
			return "the gender is man";
		}
	},
	FEMALE(1, "female") {
		public String getGenderInfo() {
			return "the gender is female";
		}
	},
	UNKNOW(2, "unknown") {
		public String getGenderInfo() {
			return "the gender is unknown";
		}
	};
	private int code;
	private String desc;

	public abstract String getGenderInfo();

	private Public_Enum_Gender(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}