package com.fubon.esb.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fubon.esb.core.TimeJudgment;
import com.fubon.esb.dao.JdbcConnection;
import com.fubon.esb.util.DriverFactory;

public class getOfficialDraw {
	static WebDriver webObj = null;

	public static void main(String[] args) {
		getOfficialDrawRun();
	}

	public static void getOfficialDrawRun() {
		// 存取google Chrome 的 exe路徑
		webObj = DriverFactory.getDriver("C:/Users/IMI-JAVA-Ryan/Desktop/chromedriver.exe");
		webObj.get("http://www.luckyairship.com/");

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				boolean resultIsStartTime = TimeJudgment.timeJudgment();
				if (resultIsStartTime) {
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					String time = sdf.format(new Date());
					String[] timelist = time.split(":");
					String realtime = timelist[1];

					if (Integer.valueOf(realtime) % 5 == 0) {

						try {
							//
							System.out.println("正在接收彩哥哥資訊，先等待30秒!!!．．．．．");
							Thread.sleep(40 * 1000);
							String resultPeriod = webObj
									.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div/div[3]/font")).getText();
							String winningNumbers = webObj
									.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div/div[3]/span")).getText();
							resultPeriod = resultPeriod.trim();
							String issueStr = resultPeriod.substring(resultPeriod.length() - 3, resultPeriod.length());
							int issue = Integer.valueOf(issueStr);

							winningNumbers = winningNumbers.trim();
							winningNumbers = winningNumbers.replace(" ", ",");

							System.out
									.println("開獎期數 :" + resultPeriod + "  開獎號碼:" + winningNumbers + "  issue:" + issue);

							Thread.sleep(60 * 1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					System.out.println("系統還未達到開始開盤的時間-- 或已經結束時間");
				}

			}
		};
		timer.schedule(task, 0, 1000); // 任务等待0秒后开始执行，之后每秒执行一次

	}
}

// JdbcConnection.inserPeriodNumber(resultPeriod, winningNumbers,
// issue);
