package com.fubon.esb.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fubon.esb.core.TimeJudgment;
import com.fubon.esb.dao.JdbcConnection;
import com.fubon.esb.util.DriverFactory;

public class getOfficialDraw {
	static WebDriver webObj = null;
	private static String period = "";
	static String chromePath = "";

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("請輸入Google驅動：");
		chromePath = scanner.nextLine();
		System.out.println("您輸入Google驅動為：" + chromePath);
		getOfficialDrawRun();
	}

	public static void getOfficialDrawRun() {
		// 存取google Chrome 的 exe路徑
		webObj = DriverFactory.getDriver(chromePath);
		webObj.get("http://www.luckyairship.com/");

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				String time = sdf.format(new Date());
				String[] timelist = time.split(":");
				String realtime = timelist[1];

//					if (true) {
				if (Integer.valueOf(realtime) % 5 == 0) {
					try {
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

						if (period.isEmpty() || !period.equals(resultPeriod)) {
							JdbcConnection.inserPeriodNumber(resultPeriod, winningNumbers, issue);
							period = resultPeriod;
						} else {
							while (period.equals(resultPeriod)) {

								resultPeriod = webObj
										.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div/div[3]/font"))
										.getText();
								winningNumbers = webObj
										.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div/div[3]/span"))
										.getText();
								resultPeriod = resultPeriod.trim();
								issueStr = resultPeriod.substring(resultPeriod.length() - 3, resultPeriod.length());
								issue = Integer.valueOf(issueStr);

								winningNumbers = winningNumbers.trim();
								winningNumbers = winningNumbers.replace(" ", ",");
								if (!period.equals(resultPeriod)) {
									break;
								}
								JdbcConnection.inserPeriodNumber(resultPeriod, winningNumbers, issue);
							}

						}
						System.out.println("開獎期數 :" + resultPeriod + "  開獎號碼:" + winningNumbers + "  issue:" + issue);

						Thread.sleep(60 * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		};
		timer.schedule(task, 0, 1000); // 任务等待0秒后开始执行，之后每秒执行一次

	}
}

// JdbcConnection.inserPeriodNumber(resultPeriod, winningNumbers,
// issue);
