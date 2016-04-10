package com.xiaogua.better.algorithm;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class Test_Find_MajorityElement {

	@Test
	public void testFindMajorityElement() {
		int[] intArr = new int[] { 1, 3, 2, 3, 3 };
		int rtnNum = Find_MajorityElementCode.findMajorityElement(intArr);
		Assert.assertEquals(3, rtnNum);
	}

	@Test
	public void testFindMajorityElementGtOneThird() {
		int[] intArr = new int[] { 1, 3, 4, 2, 3, 4, 2, 1, 1, 3, 1 };
		List<Integer> rtnList = Find_MajorityElementCode.findMajorityElementGtOneThird(intArr);
		System.out.println(rtnList);
		
		intArr = new int[] { -1,-1,-1,-1,-1,-1 };
		rtnList = Find_MajorityElementCode.findMajorityElementGtOneThird(intArr);
		System.out.println(rtnList);
		
		intArr = new int[] { 0,0,0,0,0,0,0 };
		rtnList = Find_MajorityElementCode.findMajorityElementGtOneThird(intArr);
		System.out.println(rtnList);
	}
}
