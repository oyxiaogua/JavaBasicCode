package com.xiaogua.better.bean;

import java.util.ArrayList;
import java.util.List;

public class Normal_Out_Static_Inner_Class {
	private static List<Integer> stus = new ArrayList<Integer>();

	public int test() {
		return new Static_Public_Inner_Class().test();
	}

	public static class Static_Public_Inner_Class {
		public int test() {
			return stus.size();
		}

		@SuppressWarnings("unused")
		private String getPrivateMthValue() {
			return "test_public_static_inner_rtn";
		}
	}

	@SuppressWarnings("unused")
	private static class Static_Private_Inner_Class {

		private String getPrivateMthValue() {
			return "test_private_static_inner_rtn";
		}
	}
}