package com.fubon.esb.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeJudgment {

	public static boolean timeJudgment() {
		boolean isCheckOk = true;

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.HOUR_OF_DAY, 12); // 控制時
		startCalendar.set(Calendar.MINUTE, 59); // 控制分
		startCalendar.set(Calendar.SECOND, 0); // 控制秒
		long starttime = startCalendar.getTimeInMillis(); // 得出執行任務的時間,此處為今天的13：30：00

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.add(Calendar.DATE, 1);// 把日期往後增加一天.整數往後推,負數往前移動
		endCalendar.set(Calendar.HOUR_OF_DAY, 4); // 控制時
		endCalendar.set(Calendar.MINUTE, 03); // 控制分
		endCalendar.set(Calendar.SECOND, 0); // 控制秒
		long endtime = endCalendar.getTimeInMillis(); // 得出執行任務的時間,此處為今天的3：00：00

		Calendar now = Calendar.getInstance();
		long nowTime = now.getTimeInMillis();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
		String defaultEndDate = sdf.format(endtime);
		String defaultNowDate = sdf.format(nowTime);
		String defaultStartDate = sdf.format(starttime);

		if (starttime <= nowTime && nowTime <= endtime) {
			isCheckOk = true;
		}else {
			System.out.println("取得現在系統毫秒數 : " + defaultNowDate + " 取得開始系統毫秒數 : " + defaultStartDate + " 取得結束系統毫秒數 :"
					+ defaultEndDate);

		}
		return isCheckOk;
	}

}
