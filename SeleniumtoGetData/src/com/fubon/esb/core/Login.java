package com.fubon.esb.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.fubon.esb.util.DriverFactory;

//登入NSP的畫面
public class Login {
	public static void main(String[] args) {
		try {
			loginWEB("D:/LuckeyAirship/20200225.txt", "彩票A計畫");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 上一期間開號碼
	private static ArrayList<String> lastDrawNumber = new ArrayList<>();
	// 預測號碼
	private static ArrayList<Integer> issueForecastNumber = new ArrayList<>();
	// 加碼次數
	private static Integer overweightCount = 0;

	private static int bitMoney[] = { 10, 20, 40, 80, 160, 320, 640, 1310 };

	// 是否開啟測試功能
	private static boolean isTest = true;

	public static void loginWEB(String readPath, String strategyName) throws Exception {
		try {

			DriverFactory.getDriver().get("https://gs5528.com/#/home?sub=0");

			if (isTest) {
				Thread.sleep(5000);

				// 測試按鈕登入
				DriverFactory.getDriver().findElement(By.xpath("//div[@class='demoBtn']")).click();
				Thread.sleep(1000);
				DriverFactory.getDriver().findElement(By.xpath("//div[@class='btn blueBtn']")).click();
				Thread.sleep(1000);
				DriverFactory.getDriver().findElement(By.xpath("//a[@class='yes']")).click();
				Thread.sleep(2000);

				DriverFactory.getDriver().findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				DriverFactory.getDriver().findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				DriverFactory.getDriver().findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				DriverFactory.getDriver().findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				DriverFactory.getDriver().findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				DriverFactory.getDriver().findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				DriverFactory.getDriver().findElement(By.xpath("//div[@class='closeIcon']")).click();

			} else {
				Thread.sleep(3000);

				// 登入帳號
				DriverFactory.getWait()
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")))
						.sendKeys("jackson15988");
				Thread.sleep(1000);
				// 登入密碼
				DriverFactory.getWait()
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")))
						.sendKeys("536225ab");
				Thread.sleep(1000);

				DriverFactory.getDriver().findElement(By.xpath("//div[@class='loginBtn']")).click();
				System.out.println("登入成功");
			}

			System.out.println("點選幸運飛艇");
			Thread.sleep(1000);
			// 進來之後要先點選幸運飛艇 //*[@id="app"]/div/nav/div[2]/div[2]/div[1]/div[3]/div
			DriverFactory.getDriver().findElement(By.xpath("//*[@id='app']/div/nav/div[2]/div[2]/div[1]/div[3]/div"))
					.click();
			System.out.println("點選數字盤");
			DriverFactory.getDriver().findElement(By.xpath("//*[@id='app']/div/menu/div[2]")).click();
			Thread.sleep(1000);

			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					String WhetherSeal = DriverFactory.getDriver()
							.findElement(By.xpath("//*[@id='betWrapper']/div[1]/div[3]/div[1]")).getText();

					boolean resultIsStartTime = TimeJudgment.timeJudgment();
					if (resultIsStartTime) {
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
						String time = sdf.format(new Date());
						String[] timelist = time.split(":");
						String realtime = timelist[1];

						if (Integer.valueOf(realtime) % 5 == 0) {
							try {
								System.out.println("先等待30秒!!!．．．．．");
								Thread.sleep(30 * 1000);

								if (!WhetherSeal.equals("已封盤")) {

									String openPrid = DriverFactory.getDriver()
											.findElement(By.xpath("//*[@id='betWrapper']/div[1]/div[3]")).getText();
									openPrid = openPrid.substring(0, openPrid.indexOf("期"));
									openPrid = openPrid.trim();
									openPrid = openPrid.substring(openPrid.length() - 3, openPrid.length());
									System.out.println("取得的網頁下單期數號碼" + openPrid);

									BufferedReader br;
									try {
										//
										ArrayList<String> thisPeriodNumber = new ArrayList<>();
										String str = null;
										InputStreamReader isr = new InputStreamReader(new FileInputStream(readPath),
												"UTF-8");
										br = new BufferedReader(isr);
										while ((str = br.readLine()) != null) {
											if (str.contains("期开奖号码")) {
												// System.out.println("最新开奖号码结果:" + str.toString());
												String openPid = str.substring(str.toString().indexOf("第") + 1,
														str.toString().indexOf("期开奖号码"));
												String newLottyNumber = str.substring(str.toString().indexOf("：") + 1,
														str.length());
												// 开奖结果
												String numbersp[] = newLottyNumber.split(" ");
												System.out.println(numbersp.toString());
												for (String number : numbersp) {
													number = number.replaceAll("[^(0-9)]", "");
													number = number.replace(" ", "");
													thisPeriodNumber.add(number);

												}
												break;
											}
										}
										Integer keyTrips = determineCar(thisPeriodNumber.get(0));
										System.out.println("取得上一次開獎結果: " + thisPeriodNumber);
										System.out.println("取得關鍵車次: 第幾車獲得第一名:" + keyTrips);
										System.out.print(
												"車次所在中獎關鍵號碼為:" + thisPeriodNumber.get(Integer.valueOf(keyTrips) - 1));
										String winningTripNumber = thisPeriodNumber.get(Integer.valueOf(keyTrips) - 1);
										boolean whetherWin = false;
										if (issueForecastNumber.size() != 0) {
											// issueForecastNumber 代表每次近來 也承接上一期塞入的結果
											for (Integer checkWinNo : issueForecastNumber) {
												// 處理有中獎區塊 則不進行加碼
												if (checkWinNo == keyTrips) {
													whetherWin = true;
													overweightCount = 0;
												}
											}
											if (!whetherWin) {
												// 沒有中獎的情況之下 必須要 +1 因為要進行加碼
												System.out.println("掛!!!!---- 進行一次加碼");
												overweightCount += 1;
											}
											// 處理下一期間預測結果
											issueForecastNumber.clear(); // 保守起見 先進行清空處理
											issueForecastNumber = KillNumber
													.getNextBetNumber(Integer.valueOf(winningTripNumber));
											System.out.println("取得預測號碼:" + issueForecastNumber);
											// 以下 else 等於 size等於 0剛進入時候
										} else {
											// 處理下一期間預測結果
											issueForecastNumber.clear(); // 保守起見 先進行清空處理
											overweightCount = 0;

											issueForecastNumber = KillNumber
													.getNextBetNumber(Integer.valueOf(winningTripNumber));
											System.out.println("取得預測號碼:" + issueForecastNumber);
										}

									} catch (IOException e) {
										System.out.println("系統發生錯誤" + e);
									}

//									if (result.get(2).toString().trim().equals("1")) {
//										System.out.print("第一名冠軍");
//									}

									Thread.sleep(3000);
									int bitOrderMoney = bitMoney[overweightCount];
									String bitOrderMoneyStr = String.valueOf(bitOrderMoney);
									for (Integer bitNo : issueForecastNumber) {
										switch (bitNo) {
										case 1:
											DriverFactory.getDriver().findElement(By.xpath(
													"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[1]/td[3]/input"))
													.sendKeys(bitOrderMoneyStr);
											break;
										case 2:
											DriverFactory.getDriver().findElement(By.xpath(
													"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[2]/td[3]/input"))
													.sendKeys(bitOrderMoneyStr);
											break;
										case 3:
											DriverFactory.getDriver().findElement(By.xpath(
													"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[3]/td[3]/input"))
													.sendKeys(bitOrderMoneyStr);
											break;
										case 4:
											DriverFactory.getDriver().findElement(By.xpath(
													"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[4]/td[3]/input"))
													.sendKeys(bitOrderMoneyStr);
											break;
										case 5:
											DriverFactory.getDriver().findElement(By.xpath(
													"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[5]/td[3]/input"))
													.sendKeys(bitOrderMoneyStr);
											break;
										case 6:
											DriverFactory.getDriver().findElement(By.xpath(
													"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[6]/td[3]/input"))
													.sendKeys(bitOrderMoneyStr);
											break;
										case 7:
											DriverFactory.getDriver().findElement(By.xpath(
													"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[7]/td[3]/input"))
													.sendKeys(bitOrderMoneyStr);
											break;
										case 8:
											DriverFactory.getDriver().findElement(By.xpath(
													"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[8]/td[3]/input"))
													.sendKeys(bitOrderMoneyStr);
											break;
										case 9:
											DriverFactory.getDriver().findElement(By.xpath(
													"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[9]/td[3]/input"))
													.sendKeys(bitOrderMoneyStr);
											break;
										case 10:
											DriverFactory.getDriver().findElement(By.xpath(
													"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[10]/td[3]/input"))
													.sendKeys(bitOrderMoneyStr);
											break;

										default:
											break;
										}

									}

									// 點即送出效果 先跳出懸浮視窗
									Thread.sleep(500);
									DriverFactory.getDriver()
											.findElement(
													By.xpath("//*[@id='betWrapper']/div[2]/div/div[1]/div[3]/div[2]"))
											.click();
									
									Thread.sleep(1500);
									DriverFactory.getDriver()
									.findElement(
											By.xpath("//*[@id='betWrapper']/div[2]/div/div[1]/div[4]/div/div[3]/div[1]"))
									.click();
									

								}

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
			};
			timer.schedule(task, 0, 1000); // 任务等待0秒后开始执行，之后每秒执行一次

			System.out.println("模擬成功");

			// *[@id="betWrapper"]/div[2]/div/div[1]/div[4]/div/div[3]/div[1]
			// *[@id="betWrapper"]/div[2]/div/div[1]/div[1]/div[1]
			// *[@id="betWrapper"]/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[1]/td[3]/input

//			Timer timer = new Timer();
//			MyTask taskFunction = new MyTask();

//				String outpath = path.replace('\\', '/');
//				taskFunction.setReadPath(outpath);
//				taskFunction.setWaitSec(sec);
//				taskFunction.setStrategyName(strategyName);
//				taskFunction.setErrorCount(errorCount);
//			timer.schedule(taskFunction, 0, 1000); // 任务等待0秒后开始执行，之后每秒执行一次

			// *[@id="username"]

//			DriverFactory.getDriver().findElement(By.xpath("//*[@id='pass']")).sendKeys("9487941");
//			// *[@id="pass"]
//
//			DriverFactory.getDriver().findElement(By.xpath("//*[@id='LoginSubmit']")).click();
			// *[@id="LoginSubmit"]

			// 登入問題

//				DriverFactory.getDriver()
//						.findElement(By.xpath("/html/body/div[6]/md-dialog/md-dialog-actions/button[2]")).click();

			;
			// 點選畫面跳出的東西
//			DriverFactory.getDriver()
//					.findElement(By.xpath("//div[@class='winMain']/ul/li[contains(@class='winBu')]/div/span/a"))
//					.click();

			Thread.sleep(1000);

			System.out.println("完成");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static Integer determineCar(String oneCar) {
		Integer determineTrips = -1;
		if (oneCar.equals("01")) {
			return determineTrips = 1;
		} else if (oneCar.equals("02")) {
			return determineTrips = 2;
		} else if (oneCar.equals("03")) {
			return determineTrips = 3;
		} else if (oneCar.equals("04")) {
			return determineTrips = 4;
		} else if (oneCar.equals("05")) {
			return determineTrips = 5;
		} else if (oneCar.equals("06")) {
			return determineTrips = 6;
		} else if (oneCar.equals("07")) {
			return determineTrips = 7;
		} else if (oneCar.equals("08")) {
			return determineTrips = 8;
		} else if (oneCar.equals("09")) {
			return determineTrips = 9;
		} else if (oneCar.equals("10")) {
			return determineTrips = 10;
		}
		return determineTrips;

	}

}
