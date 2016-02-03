package com.xiaogua.better.basic;

public class ByteCode {
	public static int indexOf(byte[] srcArr, byte[] subArray) {
		if (srcArr.length == 0 || subArray.length == 0) {
			return -1;
		}

		if (subArray.length > srcArr.length) {
			return -1;
		}

		for (int i = 0, len = srcArr.length; i < len; i++) {
			if (srcArr[i] == subArray[0]) {
				boolean subArrayFound = true;
				for (int j = 0; j < subArray.length; j++) {
					if (len <= i + j || subArray[j] != srcArr[i + j]) {
						subArrayFound = false;
						break;
					}
				}
				if (subArrayFound) {
					return i;
				}
			}
		}
		return -1;
	}
	
}
