package com.xiaogua.better.bean;

public class Normal_Out_Class {
	private String name = "test_out";

	public String getOutValue() {
		return name;
	}

	public String printName() {
		return "[" + name + "]";
	}

	public String getPrivateInnerPubMethod() {
		Inner_Private_Class innerPrivateClas = new Inner_Private_Class();
		return innerPrivateClas.getPublicMethodValue();
	}

	public String getPrivateInnerPriMethod() {
		Inner_Private_Class innerPrivateClas = new Inner_Private_Class();
		return innerPrivateClas.getPrivateMethodValue();
	}

	public class Inner_Class {
		private String name = "test_inner";

		public String getOutValue() {
			return Normal_Out_Class.this.name;
		}

		public String getInnerValue() {
			return this.name;
		}

		public String printName() {
			return Normal_Out_Class.this.printName() + ",[" + name + "]";
		}
		
		@SuppressWarnings("unused")
		private String getPrivateMthValue() {
			return "test_normal_private_rtn";
		}
	}

	private class Inner_Private_Class {
		public String getPublicMethodValue() {
			return "inner_public_value";
		}

		public String getPrivateMethodValue() {
			return "inner_private_value";
		}
		
		@SuppressWarnings("unused")
		private String getPrivateMthValue() {
			return "test_private_inner_rtn";
		}
	}

}