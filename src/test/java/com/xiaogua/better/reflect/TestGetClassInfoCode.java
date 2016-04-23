package com.xiaogua.better.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.xiaogua.better.bean.InterfaceGetName;
import com.xiaogua.better.bean.Normal_Methd_Inner_Class;
import com.xiaogua.better.bean.Normal_Out_Class;
import com.xiaogua.better.bean.Normal_Out_Static_Inner_Class;

public class TestGetClassInfoCode {

	@Test
	public void testGetInnerClass() {
		System.out.println(Arrays.toString(GetClassInfoCode.getInnerClass(Normal_Out_Class.class)));
		System.out.println(Arrays.toString(GetClassInfoCode.getInnerClass(Normal_Out_Static_Inner_Class.class)));
	}

	@Test
	public void testGetClassName() {
		String classNameStr = GetClassInfoCode.getClassName(Normal_Out_Class.class);
		Assert.assertEquals("Normal_Out_Class", classNameStr);
		classNameStr = GetClassInfoCode.getClassName(Normal_Out_Class.Inner_Class.class);
		Assert.assertEquals("Inner_Class", classNameStr);

		classNameStr = GetClassInfoCode.getClassName(Normal_Out_Static_Inner_Class.class);
		Assert.assertEquals("Normal_Out_Static_Inner_Class", classNameStr);
		classNameStr = GetClassInfoCode.getClassName(Normal_Out_Static_Inner_Class.Static_Public_Inner_Class.class);
		Assert.assertEquals("Static_Public_Inner_Class", classNameStr);
	}

	@Test
	public void testInvokeInnerClassMethod() {
		Normal_Out_Class normalOutClass = new Normal_Out_Class();
		String valurStr = normalOutClass.getOutValue();
		Assert.assertEquals("test_out", valurStr);
		valurStr = normalOutClass.printName();
		Assert.assertEquals("[test_out]", valurStr);

		valurStr = normalOutClass.getPrivateInnerPubMethod();
		Assert.assertEquals("inner_public_value", valurStr);

		valurStr = normalOutClass.getPrivateInnerPriMethod();
		Assert.assertEquals("inner_private_value", valurStr);

		Normal_Out_Class.Inner_Class innerClass = new Normal_Out_Class().new Inner_Class();
		valurStr = innerClass.getOutValue();
		Assert.assertEquals("test_out", valurStr);
		valurStr = innerClass.getInnerValue();
		Assert.assertEquals("test_inner", valurStr);
		valurStr = innerClass.printName();
		Assert.assertEquals("[test_out],[test_inner]", valurStr);

		// or
		Normal_Out_Class.Inner_Class innerClass2 = normalOutClass.getInnerClass();
		valurStr = innerClass2.printName();
		Assert.assertEquals("[test_out],[test_inner]", valurStr);
	}

	@Test
	public void testReflectNormalInnerClass() throws Exception {
		String outClassPathStr = "com.xiaogua.better.bean.Normal_Out_Class";
		String innerClassPathStr = outClassPathStr + "$Inner_Class";
		Class<?> outCls = Class.forName(outClassPathStr);
		Normal_Out_Class outClsInstance = (Normal_Out_Class) outCls.newInstance();

		Class<?> innerCls = Class.forName(innerClassPathStr);
		Constructor<?> constructor = innerCls.getDeclaredConstructor(Normal_Out_Class.class);
		constructor.setAccessible(true);
		Object child = constructor.newInstance(outClsInstance);

		Method method = innerCls.getDeclaredMethod("getPrivateMthValue", new Class<?>[] {});
		method.setAccessible(true);
		String rtnStr = (String) method.invoke(child, new Object[] {});
		Assert.assertEquals("test_normal_private_rtn", rtnStr);
	}

	@Test
	public void testReflectPublicStaticInnerClass() throws Exception {
		String outClassPathStr = "com.xiaogua.better.bean.Normal_Out_Static_Inner_Class";
		String innerClassPathStr = outClassPathStr + "$Static_Public_Inner_Class";

		Class<?> innerCls = Class.forName(innerClassPathStr);
		Normal_Out_Static_Inner_Class.Static_Public_Inner_Class innerStaticCls = (Normal_Out_Static_Inner_Class.Static_Public_Inner_Class) innerCls
				.newInstance();

		Method method = innerCls.getDeclaredMethod("getPrivateMthValue", new Class<?>[] {});
		method.setAccessible(true);
		String rtnStr = (String) method.invoke(innerStaticCls, new Object[] {});
		Assert.assertEquals("test_public_static_inner_rtn", rtnStr);
	}

	@Test
	public void testReflectPrivateStaticInnerClass() throws Exception {
		String outClassPathStr = "com.xiaogua.better.bean.Normal_Out_Static_Inner_Class";
		String innerClassPathStr = outClassPathStr + "$Static_Private_Inner_Class";

		Class<?> innerCls = Class.forName(innerClassPathStr);
		Constructor<?> constructor = innerCls.getDeclaredConstructor();
		constructor.setAccessible(true);
		Object o = constructor.newInstance(new Object[] {});

		Method method = innerCls.getDeclaredMethod("getPrivateMthValue", new Class<?>[] {});
		method.setAccessible(true);
		String rtnStr = (String) method.invoke(o, new Object[] {});
		Assert.assertEquals("test_private_static_inner_rtn", rtnStr);
	}

	@Test
	public void testNormalMethdInnerClass() {
		Normal_Methd_Inner_Class clas = new Normal_Methd_Inner_Class();
		InterfaceGetName mth = clas.getMthInnerClass();
		String str = mth.getName();
		Assert.assertEquals("method_inner_class", str);
	}

	@Test
	public void testReflectStaticInnerClass() throws Exception {
		String outClassPathStr = "com.xiaogua.better.bean.Static_Inner_Class";
		String innerClassPathStr = outClassPathStr + "$Inner_Static_Class";
		Class<?> innerCls = Class.forName(innerClassPathStr);
		Constructor<?> constructor = innerCls.getDeclaredConstructor(String.class);
		constructor.setAccessible(true);
		Object o = constructor.newInstance(new Object[] { "test" });
		Method method = innerCls.getDeclaredMethod("getNameInfo", new Class<?>[] {});
		method.setAccessible(true);
		String rtnStr = (String) method.invoke(o, new Object[] {});
		System.out.println(rtnStr);

		method = innerCls.getDeclaredMethod("setName", new Class<?>[] { String.class });
		method.setAccessible(true);
		method.invoke(o, new Object[] { "test_2" });

		method = innerCls.getDeclaredMethod("getNameInfo", new Class<?>[] {});
		method.setAccessible(true);
		rtnStr = (String) method.invoke(o, new Object[] {});
		System.out.println(rtnStr);
	}
}
