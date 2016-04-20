package com.xiaogua.better.basic;

import org.junit.Test;

public class TestBreakAllForLoop {

	@Test
	public void testBreakAllForLoop() {
		boolean isContinue = true;
		final int loopSize = 10;
		for (int i = 0; isContinue && i < loopSize; i++) {
			for (int j = 0; isContinue && j < loopSize; j++) {
				for (int k = 0; isContinue && k < loopSize; k++) {
					for (int l = 0; isContinue && l < loopSize; l++) {
						if (i + j + k + l == loopSize * 2) {
							isContinue = false;
							System.out.println("find value ,break all for loop");
						} else {
							System.out.println(String.format("finding,i=%s,j=%s,k=%s,l=%s", i, j, k, l));
						}
					}
				}
			}
		}
		System.out.println("end");
	}
}
