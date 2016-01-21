package com.xiaogua.better.basic;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class TestQueue {
	@Test
	public void testQueueWrongUsage() {
		Queue<String> queue = new LinkedList<String>();
		for (int i = 0; i < 20; i++) {
			queue.add("test_" + i);
		}
		for (int j = 0; j < queue.size(); j++) {
			String str = (String) queue.poll();// 取出后大小改变 j++ size--
			System.out.println(String.format("j=%s,queue size=%s,current value=%s", j, queue.size(), str));
		}
	}

	@Test
	public void testQueueRightUsage() {
		Queue<String> queue = new LinkedList<String>();
		for (int i = 0; i < 20; i++) {
			queue.add("test_" + i);
		}
		for (int j = 0, len = queue.size(); j < len; j++) {
			String str = (String) queue.poll();// 取出后大小改变 j++ size--
			System.out.println(String.format("j=%s,queue size=%s,current value=%s", j, queue.size(), str));
		}
	}
}
