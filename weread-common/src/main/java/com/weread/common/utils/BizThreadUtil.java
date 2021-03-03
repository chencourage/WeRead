package com.weread.common.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 业务通用线程池，固定线程数20，最大等待任务数300，超过300抛出RejectedExecutionException异常
 * @author lisheng
 *
 */
public class BizThreadUtil {
	
	private static volatile BizThreadUtil threadUtil;
	private volatile ExecutorService pool;
	private static final int THREAD_NUM = 20; //线程数
	private static final int MAX_WAIT_NUM = 300; //最大等待执行数

	private BizThreadUtil() {
	};

	public static BizThreadUtil getThreadUtil() {
		if (null == threadUtil) {
			synchronized (BizThreadUtil.class) {
				if (null == threadUtil)
					threadUtil = new BizThreadUtil();
			}
		}
		return threadUtil;
	}

	/**
	 * 
	 * @Description: 提交任务到线程池
	 * @param thread
	 * @return void
	 * @throws
	 */
	public void execute(Runnable runnable) {
		if (pool == null) {
			synchronized (BizThreadUtil.class) {
				if (null == pool) {
					BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(MAX_WAIT_NUM);
					RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
					ThreadFactory threadFactory = new ThreadFactory() {
						@Override
						public Thread newThread(Runnable r) {
							Thread thread = new Thread(r);
							thread.setDaemon(true);
							return thread;
						}
					};
					pool = new ThreadPoolExecutor(THREAD_NUM, THREAD_NUM, 0L, TimeUnit.MILLISECONDS, queue, threadFactory, handler);
				}
			}
		}
		pool.execute(runnable);
	}
}
