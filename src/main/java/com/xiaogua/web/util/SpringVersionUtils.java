package com.xiaogua.web.util;

public final class SpringVersionUtils {

	public static boolean isSpring30AtLeast() {
		final ClassLoader classLoader = getClassLoader(SpringVersionUtils.class);
		try {
			Class.forName("org.springframework.web.bind.annotation.RequestBody", false, classLoader);
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	public static boolean isSpring31AtLeast() {
		final ClassLoader classLoader = getClassLoader(SpringVersionUtils.class);
		try {
			Class.forName("org.springframework.web.method.HandlerMethod", false, classLoader);
			return true;
		} catch (final Exception e) {
			return false;
		}

	}

	public static boolean isSpring32AtLeast() {
		final ClassLoader classLoader = getClassLoader(SpringVersionUtils.class);
		try {
			Class.forName("org.springframework.web.context.request.async.DeferredResult", false, classLoader);
			return true;
		} catch (final Exception e) {
			return false;
		}

	}

	public static boolean isSpring40AtLeast() {

		final ClassLoader classLoader = getClassLoader(SpringVersionUtils.class);
		try {
			Class.forName("org.springframework.core.io.PathResource", false, classLoader);
			return true;
		} catch (final Exception e) {
			return false;
		}

	}

	public static ClassLoader getClassLoader(final Class<?> clazz) {
		final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		if (contextClassLoader != null) {
			return contextClassLoader;
		}
		if (clazz != null) {
			final ClassLoader clazzClassLoader = clazz.getClassLoader();
			if (clazzClassLoader != null) {
				return clazzClassLoader;
			}
		}
		return ClassLoader.getSystemClassLoader();
	}

	private SpringVersionUtils() {
		super();
	}
}