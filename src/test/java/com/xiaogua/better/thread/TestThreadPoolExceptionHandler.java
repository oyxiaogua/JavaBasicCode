package com.xiaogua.better.thread;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Test;

public class TestThreadPoolExceptionHandler {
	private Logger logger = Logger.getLogger(TestThreadPoolExceptionHandler.class);

	@Test
	public void testDefaultExecutorsHandlerException() throws Exception {
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new PrintSysDateThread(), 1, 1,
				TimeUnit.SECONDS);
		TimeUnit.SECONDS.sleep(30);
	}

	@Test
	public void testThreadHandlerException() throws Exception {
		final UncaughtExceptionHandler exceptionHandler = new UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				System.err.println("Uncaught exception in thread '" + t.getName() + "': " + e.getMessage());
			}
		};
		Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
		Thread thread = new Thread(new PrintSysDateThread());
		thread.start();
		TimeUnit.SECONDS.sleep(3);
	}

	@Test
	public void testAfterExecuteMethodHandlerException() throws Exception {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 1, TimeUnit.MINUTES,
				new ArrayBlockingQueue<Runnable>(10000)) {
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
				printException(r, t);
			}
		};
		for (int i = 0; i < 10; i++) {
			threadPoolExecutor.submit(new PrintSysDateThread());
		}
		TimeUnit.SECONDS.sleep(30);
	}

	private void printException(Runnable r, Throwable t) {
		if (t == null && r instanceof Future<?>) {
			try {
				Future<?> future = (Future<?>) r;
				if (future.isDone()) {
					future.get();
				}
			} catch (CancellationException ce) {
				t = ce;
			} catch (ExecutionException ee) {
				t = ee.getCause();
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt(); // ignore/reset
			}
		}
		if (t != null) {
			logger.error(t.getMessage(), t);
		}
	}

}
