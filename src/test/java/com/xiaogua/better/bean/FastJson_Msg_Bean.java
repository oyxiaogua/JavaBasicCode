package com.xiaogua.better.bean;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

public class FastJson_Msg_Bean {
	public FastJson_Msg_Bean() {
		super();
	}

	public FastJson_Msg_Bean(int id, Enum_StatusCodeEnum statusCode) {
		super();
		this.id = id;
		this.statusCode = statusCode;
	}

	public String toString() {
		return " [id=" + id + ", statusCode=" + statusCode + "]";
	}

	private int id;
	@JSONField(name = "status")
	private Enum_StatusCodeEnum statusCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return statusCode.getCode();
	}

	public void setStatus(int code) {
		this.statusCode = Enum_StatusCodeEnum.getEnum(code);
	}

	@JSONField(serialize = false)
	public Enum_StatusCodeEnum getStatusCode() {
		//设置属性不解析到json字符串中
		return statusCode;
	}

	@JSONField(deserialize = false)
	public void setStatusCode(Enum_StatusCodeEnum statusCode) {
		this.statusCode = statusCode;
	}

	public static enum Enum_StatusCodeEnum {
		OK(200, "正常"), BAD_REQUEST(400, "服务器出错");
		private static final Map<Integer, Enum_StatusCodeEnum> CODE_MAP = new HashMap<Integer, Enum_StatusCodeEnum>();

		static {
			for (Enum_StatusCodeEnum typeEnum : Enum_StatusCodeEnum.values()) {
				CODE_MAP.put(typeEnum.getCode(), typeEnum);
			}
		}

		Enum_StatusCodeEnum(int code, String meaning) {
			this.code = code;
			this.meaning = meaning;
		}

		public int getCode() {
			return code;
		}

		public String getMeaning() {
			return meaning;
		}

		public static Enum_StatusCodeEnum getEnum(Integer code) {
			return CODE_MAP.get(code);
		}

		private final int code;
		private final String meaning;
	}
}