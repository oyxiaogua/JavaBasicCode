package com.xiaogua.better.file;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class GetProperties {
	private static final Logger log = Logger.getLogger(GetProperties.class);

	/**
	 * 无缓存加载配置文件
	 */
	public static Properties getPropertiesNoCache(final URL url) {
		InputStream in = null;
		try {
			// We must ensure that useCaches is set to false, as the
			// default behaviour of java is to cache file handles, and
			// this "locks" files, preventing hot-redeploy on windows.
			URLConnection connection = url.openConnection();
			connection.setUseCaches(false);
			in = connection.getInputStream();
			if (in != null) {
				Properties props = new Properties();
				props.load(in);
				return props;
			}
		} catch (Exception e) {
			log.error("getProperties error", e);
		} finally {
			IOUtils.closeQuietly(in);
			in = null;
		}
		return null;
	}

	/**
	 * 获取系统属性
	 */
	public static String getSystemProperty(final String key, final String def) throws SecurityException {
		return (String) AccessController.doPrivileged(new PrivilegedAction<Object>() {
			public Object run() {
				return System.getProperty(key, def);
			}
		});
	}

}
