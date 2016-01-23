package com.xiaogua.better.str;

import java.security.MessageDigest;
import java.util.Map;

public class StringCommonUtils extends org.apache.commons.lang3.StringUtils {
	/**
	 * 删除首尾空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static String trimWhitespace(String str) {
		if (str == null) {
			return null;
		}
		str = str.trim();
		StringBuilder sb = new StringBuilder(str);
		while ((sb.length() > 0) && (Character.isWhitespace(str.charAt(0)))) {
			sb.deleteCharAt(0);
		}
		while ((sb.length() > 0) && (Character.isWhitespace(sb.charAt(sb.length() - 1)))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 判断字符串是否为空或""或者null字符串(未考虑undefined字符串)
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNull(String str) {
		if (isBlank(str) || str.trim().equals("null")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除左边空格
	 */
	public static String trimLeft(String str) {
		if (!(hasLength(str))) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while ((sb.length() > 0) && (Character.isWhitespace(sb.charAt(0)))) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * 删除右边空格
	 */
	public static String trimRight(String str) {
		if (!(hasLength(str))) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while ((sb.length() > 0) && (Character.isWhitespace(sb.charAt(sb.length() - 1)))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public static boolean hasLength(String str) {
		return ((str != null) && (str.length() > 0));
	}

	/**
	 * 替换str所有变量,如#{key_1}
	 * 
	 * @param str
	 * @param beforSplit:变量前缀,如#{
	 * @param afterSplit:变量后缀,如}
	 * @param map
	 * @return
	 */
	public static String replaceStrAllParam(final String str, String beforSplit, String afterSplit,
			Map<String, String> map) {
		if (map == null || map.size() == 0) {
			return str;
		}
		if (isBlank(str)) {
			return str;
		}
		if (beforSplit == null) {
			beforSplit = "${";
		}
		if (afterSplit == null) {
			afterSplit = "}";
		}
		StringBuilder sb = new StringBuilder((int) (str.length() * 1.5));
		int cursor = 0;
		for (int start, end; (start = str.indexOf(beforSplit, cursor)) != -1
				&& (end = str.indexOf(afterSplit, start)) != -1;) {
			sb.append(str.substring(cursor, start));
			String key = str.substring(start + beforSplit.length(), end);
			if (map.get(trim(key)) != null) {
				sb.append(map.get(trim(key)));
			} else {
				sb.append(beforSplit).append(key).append(afterSplit);
			}
			cursor = end + afterSplit.length();
		}
		sb.append(str.substring(cursor, str.length()));
		return sb.toString();
	}

	/**
	 * 替换字符串,不区分大小写
	 */
	public static String replaceIgnoreCase(String line, String oldString, String newString) {
		if ((!(hasLength(line))) || (!(hasLength(oldString))) || (newString == null)) {
			return line;
		}
		StringBuilder sb = new StringBuilder();
		String lineLowerStr = line.toLowerCase();
		String oldLowerStr = oldString.toLowerCase();
		int pos = 0;
		int index = lineLowerStr.indexOf(oldLowerStr);
		int patLen = oldLowerStr.length();
		while (index >= 0) {
			sb.append(line.substring(pos, index));
			sb.append(newString);
			pos = index + patLen;
			index = lineLowerStr.indexOf(oldLowerStr, pos);
		}
		sb.append(line.substring(pos));
		return sb.toString();
	}

	/**
	 * 获取字符串str在String中出现的次数
	 */
	public static int countSubStr(String string, String str) {
		if (!hasLength(string) || !hasLength(str)) {
			return 0;
		}
		int count = 0;
		int index = 0;
		while ((index = string.indexOf(str, index)) != -1) {
			count++;
			index += str.length();
		}
		return count;
	}

	/**
	 * 替换首次出现字符串
	 */
	public static String replaceFirst(String s, String sub, String with) {
		return replaceFirstIgnoreCase(s, sub, with, false);
	}

	/**
	 * 替换首次出现字符串,不区分大小写
	 */
	public static String replaceFirstIgnoreCase(String s, String sub, String with) {
		return replaceFirstIgnoreCase(s, sub, with, true);
	}

	private static String replaceFirstIgnoreCase(String s, String sub, String with, boolean ignoreCase) {
		if ((!(hasLength(s))) || (!(hasLength(sub))) || (with == null)) {
			return s;
		}
		int index = -1;
		if (ignoreCase) {
			String lowerS = s.toLowerCase();
			String lowerSub = sub.toLowerCase();
			index = lowerS.indexOf(lowerSub);
		} else {
			index = s.indexOf(sub);
		}
		if (index < 0) {
			return s;
		}
		return s.substring(0, index) + with + s.substring(index + sub.length());
	}

	/**
	 * 替换最后一次出现字符串
	 */
	public static String replaceLast(String s, String sub, String with) {
		return replaceLastIgnoreCase(s, sub, with, false);
	}

	/**
	 * 替换最后一次出现字符串,不区分大小写
	 */
	public static String replaceLastIgnoreCase(String s, String sub, String with) {
		return replaceLastIgnoreCase(s, sub, with, true);
	}

	private static String replaceLastIgnoreCase(String s, String sub, String with, boolean ignoreCase) {
		if ((!(hasLength(s))) || (!(hasLength(sub))) || (with == null)) {
			return s;
		}
		int index = -1;
		if (ignoreCase) {
			String lowerS = s.toLowerCase();
			String lowerSub = sub.toLowerCase();
			index = lowerS.lastIndexOf(lowerSub);
		} else {
			index = s.lastIndexOf(sub);
		}
		if (index < 0) {
			return s;
		}
		return s.substring(0, index) + with + s.substring(index + sub.length());
	}

	/**
	 * 求字符串md5值
	 */
	public static String md5(String str) throws Exception {
		if (str == null) {
			return null;
		}
		StringBuffer hexStrSb = new StringBuffer();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(str.getBytes());
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexStrSb.append("0" + Integer.toHexString((0xFF & hash[i])));
			} else {
				hexStrSb.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return hexStrSb.toString();
	}

	/**
	 * 是否包含,不区分大小写
	 * 
	 * @param strArr
	 * @param checkStr
	 * @return
	 */
	public static boolean containsIgnoreCase(String[] strArr, String checkStr) {
		if (checkStr != null && (strArr != null && strArr.length > 0)) {
			checkStr = checkStr.toLowerCase();
			for (String str : strArr) {
				if (str != null && str.toLowerCase().contains(checkStr)) {
					return true;
				}
			}
		}
		if (checkStr == null) {
			if (strArr == null || strArr.length == 0) {
				return true;
			}
			for (String str : strArr) {
				if (str == null) {
					return true;
				}
			}
		}
		return false;
	}
}
