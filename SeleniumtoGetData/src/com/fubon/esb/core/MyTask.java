package com.fubon.esb.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;


public class MyTask extends TimerTask {

	private static String waitSec;
	private static String readPath;
	private static String strategyName;
	private static String errorCount;
	private static String lineToken;

	public String getWaitSec() {
		return waitSec;
	}

	public void setWaitSec(String waitSec) {
		this.waitSec = waitSec;
	}

	public String getReadPath() {
		return readPath;
	}

	public void setReadPath(String readPath) {
		this.readPath = readPath;
	}

	public static String getStrategyName() {
		return strategyName;
	}

	public static void setStrategyName(String strategyName) {
		MyTask.strategyName = strategyName;
	}

	public static String getErrorCount() {
		return errorCount;
	}

	public static void setErrorCount(String errorCount) {
		MyTask.errorCount = errorCount;
	}

	public static String getLineToken() {
		return lineToken;
	}

	public static void setLineToken(String lineToken) {
		MyTask.lineToken = lineToken;
	}

	@Override
	public void run() {
//		Date day = new Date();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(df.format(day) + "\n");

		boolean resultIsStartTime = TimeJudgment.timeJudgment();
		if (resultIsStartTime) {

			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(new Date());
			String[] timelist = time.split(":");
			String realtime = timelist[1];
			System.out.println("目前分鐘數:" + realtime + "等待時間為:" + waitSec + "讀取路徑:" + readPath + "策略名稱 :" + strategyName);

			if (Integer.valueOf(realtime) % 5 == 0) {
				try {
					System.out.println("先等待40秒");
					Thread.sleep(Integer.valueOf(waitSec) * 1000);
					
					
					
					
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("未達進入判斷分鐘數");
			}
		} else {
			System.out.println("系統還未達到開始開盤的時間-- 或已經結束時間");
		}

	}

}
