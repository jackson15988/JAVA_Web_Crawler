package com.fubon.esb.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.fubon.esb.util.DriverFactory;
import com.fubon.esb.util.HandleNumber;
import com.fubon.esb.util.LineNotify;

//登入NSP的畫面
public class Login {

	static WebDriver webObj = null;

	private static ArrayList<String> specificSizeList01 = new ArrayList<>();
	private static ArrayList<String> specificSizeList02 = new ArrayList<>();
	private static ArrayList<String> specificSizeList03 = new ArrayList<>();
	private static ArrayList<String> specificSizeList04 = new ArrayList<>();
	private static ArrayList<String> specificSizeList05 = new ArrayList<>();
	private static ArrayList<String> specificSizeList06 = new ArrayList<>();
	private static ArrayList<String> specificSizeList07 = new ArrayList<>();
	private static ArrayList<String> specificSizeList08 = new ArrayList<>();
	private static ArrayList<String> specificSizeList09 = new ArrayList<>();
	private static ArrayList<String> specificSizeList10 = new ArrayList<>();
	private static HashMap<String, ArrayList<Object>> dbMap = new HashMap<>();
	private static String issuedPodStr = "";
	private static String publineToken = "";
	public static int stegosaurusOverweight = 0; // 劍龍加碼次數
	public static int stegosaurusDismissal = 0; // 劍龍加碼次數
	private static String WhetherSeal = "";
	public static boolean stegosaurusAutomaticRenewal = true;
	private static String lineGoldenKey;

	// 高關數暫存 如果是三先存在在這等待判斷是否加碼
	private static HashMap<String, Object> highPassCount = new HashMap<>();

	// 預測號碼
	private static ArrayList<Integer> issueForecastNumber = new ArrayList<>();
	// 加碼次數
	private static Integer overweightCount = 0;

	private static int luckAirShipbitMoney[] = null;
	private static int stegosaurusBitMoney[] = null; // 劍龍策略下注金額{0,10,20,30,40,}

	// 是否開啟測試功能
	private static boolean isTest;

	private static boolean isForceRun = false;

	public static boolean stegosaurusstrategyTimeout = true;

	public static void loginWEB(String readPath, String strategyName, HashMap<String, int[]> betList,
			HashMap<String, Boolean> strategySwitch, HashMap<String, String> accPas,
			HashMap<String, Object> otherParameters) throws Exception {
		try {

			webObj = DriverFactory.getDriver(otherParameters.get("googleDriverPathStr").toString());
			// 初始化地方
			luckAirShipbitMoney = betList.get("luckAirshpBitMoney");
			stegosaurusBitMoney = betList.get("stegosaurusBitMoney");
			lineGoldenKey = otherParameters.get("lineGoldenKey").toString();
			isTest = (boolean) otherParameters.get("isDemoRun");
			stegosaurusAutomaticRenewal = (boolean) otherParameters.get("StegosaurusAutomaticRenewalinit");
//			DriverFactory.getDriver().get("https://gs5528.com/#/home?sub=0");
			webObj.get("https://gs5528.com/#/home?sub=0");
			if (isTest) {
				boolean isInit = isJudgingElement(webObj, By.xpath("//div[@class='demoBtn']"));
				while (!isInit) {
					// 測試按鈕登入 如果還不存在 則持續搜尋
					isInit = isJudgingElement(webObj, By.xpath("//div[@class='demoBtn']"));
					Thread.sleep(1000);
					if (isInit) {
						break;
					}
				}
				webObj.findElement(By.xpath("//div[@class='demoBtn']")).click();
				Thread.sleep(1000);
				webObj.findElement(By.xpath("//div[@class='btn blueBtn']")).click();
				Thread.sleep(1000);
				webObj.findElement(By.xpath("//a[@class='yes']")).click();
				Thread.sleep(2000);

				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();

			} else {
				Thread.sleep(3000);

				// 登入帳號
				DriverFactory.getWait()
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")))
						.sendKeys("");
				Thread.sleep(1000);
				// 登入密碼
				DriverFactory.getWait()
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")))
						.sendKeys("");
				Thread.sleep(1000);

				webObj.findElement(By.xpath("//div[@class='loginBtn']")).click();
				System.out.println("登入成功");

				Thread.sleep(2000);
				webObj.findElement(By.xpath("//a[@class='yes']")).click();
				Thread.sleep(2000);

				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
				Thread.sleep(100);
				webObj.findElement(By.xpath("//div[@class='closeIcon']")).click();
			}

			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {

					boolean whBol = isJudgingElement(webObj, By.xpath("//*[@id='betWrapper']/div[1]/div[3]/div[1]"));
					while (!whBol) {
						try {
							Thread.sleep(1000);
							System.out.println("正在獲取開盤資訊>> ---   開盤  --- 已封盤 --- 未開狀態");
							whBol = isJudgingElement(webObj, By.xpath("//*[@id='betWrapper']/div[1]/div[3]/div[1]"));
							if (whBol) {
								WhetherSeal = webObj.findElement(By.xpath("//*[@id='betWrapper']/div[1]/div[3]/div[1]"))
										.getText();
								break;
							}
						} catch (InterruptedException e) {
							System.out.println("取得開盤狀態發生錯誤!!");
						}
					}

					boolean resultIsStartTime = TimeJudgment.timeJudgment();
					if (resultIsStartTime) {
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
						String time = sdf.format(new Date());
						String[] timelist = time.split(":");
						String realtime = timelist[1];

						if (Integer.valueOf(realtime) % 5 == 0 || isForceRun) {

							try {
//						
								System.out.println("正在接收彩哥哥資訊，先等待30秒!!!．．．．．");
								Thread.sleep(60 * 1000);

								if (!WhetherSeal.equals("已封盤") && strategySwitch.get("airshipCheckBoxValue") == true) {
									// 這裡直行友道的策略
									youdaoStrategy(readPath);
								}

								// 執行劍龍的策略
								if (!WhetherSeal.equals("已封盤") && strategySwitch.get("stegosaurusCheckBoxValue") == true
										&& stegosaurusstrategyTimeout == true) {
									stegosaurusStrategy(readPath, strategyName,
											otherParameters.get("rxecutiveRacing").toString());
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
			Thread.sleep(1000);

			System.out.println("完成");

		} catch (Exception e) {
			LineNotify.callEvent(lineGoldenKey, "系統發生錯誤" + e);
			System.out.println(e);
		}
	}

	public static void youdaoStrategy(String readPath) {

		try {

			System.out.println("youdaoStrategy點選幸運飛艇");
			Thread.sleep(500);
			webObj.findElement(By.xpath("//*[@id='app']/div/nav/div[2]/div[2]/div[1]/div[3]/div")).click();
			System.out.println("youdaoStrategy點選數字盤");
			webObj.findElement(By.xpath("//*[@id='app']/div/menu/div[2]")).click();
			Thread.sleep(500);

			// 取得網頁期數號碼
			String openPrid = webObj.findElement(By.xpath("//*[@id='betWrapper']/div[1]/div[3]")).getText();
			if (!openPrid.toString().contains("未开盘")) {
				openPrid = openPrid.substring(0, openPrid.indexOf("期"));
				openPrid = openPrid.trim();
				openPrid = openPrid.substring(openPrid.length() - 3, openPrid.length());
				System.out.println("youdaoStrategy取得的網頁即將下單期數號碼 : " + openPrid);
			}

			// 取得網頁左上角之號碼 (代表前一周期)
			String webViewBeforeBitNumber = isInnerPeriod();
			while (webViewBeforeBitNumber.isEmpty()) {
				// 如果是空的情況之下 持續進行搜尋.....
				System.out.println("還是搜尋到空....!! 持續進行搜尋");
				webViewBeforeBitNumber = isInnerPeriod();
				if (!webViewBeforeBitNumber.isEmpty()) {
					System.out.println("搜尋到開獎號碼了:!!!! 跳出迴圈");
					break;
				}
			}
			// 處理一下本期預測號碼
			ArrayList<String> thisPeriodNumber = new ArrayList<>();
			thisPeriodNumber = HandleNumber.numberConversionPage(webViewBeforeBitNumber);
			if (thisPeriodNumber.size() != 10) {
				System.out.println("錯誤:!! 搜尋出來長度不等於10個號碼 ");
				// 如果網頁上的號碼不等於9個改採用採哥哥搜尋
				thisPeriodNumber = (ArrayList<String>) HandleNumber.getCaipiaoOpenNumber(readPath);
			}
			Integer keyTrips = determineCar(thisPeriodNumber.get(0));
			System.out.println("取得上一次開獎結果: " + thisPeriodNumber);
			System.out.println("取得關鍵車次幾號車獲得第一名 : " + keyTrips);
			System.out.print("車次所在中獎關鍵號碼為:" + thisPeriodNumber.get(Integer.valueOf(keyTrips) - 1));
			// 中獎關鍵號碼
			String winningTripNumber = thisPeriodNumber.get(Integer.valueOf(keyTrips) - 1);
			boolean whetherWin = false;
			if (issueForecastNumber.size() != 0) {
				// issueForecastNumber 代表每次近來 也承接上一期塞入的結果
				for (Integer checkWinNo : issueForecastNumber) {
					System.out.print("上一期間號碼 " + checkWinNo + " ");
					// 處理有中獎區塊 則不進行加碼
					if (checkWinNo == keyTrips) {
						System.out.print("確定中獎---- 進行歸零動作");
						whetherWin = true;
						overweightCount = 0;
					}
				}
				if (!whetherWin) {
					// 沒有中獎的情況之下 必須要 +1 因為要進行加碼
					System.out.println("掛!!!!---- 進行一次加碼");
					overweightCount += 1;
				} else {
					System.out.println("中獎之後進行清空加碼狀態的動作．．");
					overweightCount = 0;
				}
				// 處理下一期間預測結果
				issueForecastNumber.clear(); // 保守起見 先進行清空處理
				issueForecastNumber = KillNumber.getNextBetNumber(webViewBeforeBitNumber, thisPeriodNumber,
						Integer.valueOf(winningTripNumber));
				System.out.println("取得預測號碼:" + issueForecastNumber);
				// 以下 else 等於 size等於 0剛進入時候
			} else {
				// 處理下一期間預測結果
				issueForecastNumber.clear(); // 保守起見 先進行清空處理
				overweightCount = 0;

				issueForecastNumber = KillNumber.getNextBetNumber(webViewBeforeBitNumber, thisPeriodNumber,
						Integer.valueOf(winningTripNumber));
				System.out.println("取得預測號碼:" + issueForecastNumber);
			}

			Thread.sleep(1000);
			int bitOrderMoney = luckAirShipbitMoney[overweightCount];
			String bitOrderMoneyStr = String.valueOf(bitOrderMoney);
			System.out.println("此次為第幾關:" + overweightCount + "此次下注金額為:" + bitOrderMoneyStr);

			if (bitOrderMoney != 0 && !bitOrderMoneyStr.equals("0")) {
				for (Integer bitNo : issueForecastNumber) {
					switch (bitNo) {
					case 1:
						webObj.findElement(By.xpath(
								"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[1]/td[3]/input"))
								.sendKeys(bitOrderMoneyStr);
						break;
					case 2:
						webObj.findElement(By.xpath(
								"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[2]/td[3]/input"))
								.sendKeys(bitOrderMoneyStr);
						break;
					case 3:
						webObj.findElement(By.xpath(
								"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[3]/td[3]/input"))
								.sendKeys(bitOrderMoneyStr);
						break;
					case 4:
						webObj.findElement(By.xpath(
								"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[4]/td[3]/input"))
								.sendKeys(bitOrderMoneyStr);
						break;
					case 5:
						webObj.findElement(By.xpath(
								"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[5]/td[3]/input"))
								.sendKeys(bitOrderMoneyStr);
						break;
					case 6:
						webObj.findElement(By.xpath(
								"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[6]/td[3]/input"))
								.sendKeys(bitOrderMoneyStr);
						break;
					case 7:
						webObj.findElement(By.xpath(
								"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[7]/td[3]/input"))
								.sendKeys(bitOrderMoneyStr);
						break;
					case 8:
						webObj.findElement(By.xpath(
								"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[8]/td[3]/input"))
								.sendKeys(bitOrderMoneyStr);
						break;
					case 9:
						webObj.findElement(By.xpath(
								"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[9]/td[3]/input"))
								.sendKeys(bitOrderMoneyStr);
						break;
					case 10:
						webObj.findElement(By.xpath(
								"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[10]/td[3]/input"))
								.sendKeys(bitOrderMoneyStr);
						break;

					default:
						break;
					}

				}
			}
			// 點即送出效果 先跳出懸浮視窗
			Thread.sleep(500);
			webObj.findElement(By.xpath("//*[@id='betWrapper']/div[2]/div/div[1]/div[3]/div[2]")).click();

			Thread.sleep(1500);
			webObj.findElement(By.xpath("//*[@id='betWrapper']/div[2]/div/div[1]/div[4]/div/div[3]/div[1]")).click();
		} catch (Exception e) {
			LineNotify.callEvent(lineGoldenKey, "系統發生錯誤" + e);
			System.out.println("系統發生錯誤" + e);
		}

	}

	public static void stegosaurusStrategy(String readPath, String strategyName, String executiveRanking) {

		System.out.println("進入執行");
		BufferedReader br;
		int i = 0;
		try {
			System.out.println("點選幸運飛艇");
			Thread.sleep(500);
			boolean clickLuckPag = isJudgingElement(webObj,
					By.xpath("//*[@id='app']/div/nav/div[2]/div[2]/div[1]/div[3]/div"));
			while (!clickLuckPag) {
				clickLuckPag = isJudgingElement(webObj,
						By.xpath("//*[@id='app']/div/nav/div[2]/div[2]/div[1]/div[3]/div"));
				System.out.println("如操作者切勿自行切換自動化網頁的頁面，目前找不到幸運飛艇的頁面，搜尋三次失敗則會自行關閉程式");
				Thread.sleep(500);
				if (clickLuckPag) {
					break;
				}
			}
			// 進來之後要先點選幸運飛艇 //*[@id="app"]/div/nav/div[2]/div[2]/div[1]/div[3]/div
			webObj.findElement(By.xpath("//*[@id='app']/div/nav/div[2]/div[2]/div[1]/div[3]/div")).click();
			System.out.println("點選數字盤");
			webObj.findElement(By.xpath("//*[@id='app']/div/menu/div[2]")).click();
			Thread.sleep(1000);

			if (webObj.findElement(By.xpath("//*[@id='defaultTop']")).isSelected()) {
				webObj.findElement(By.xpath("//*[@id='defaultTop']")).click();
				// 清空元件
				webObj.findElement(By.xpath("//*[@id='betWrapper']/div[2]/div/div[1]/div[1]/div[2]")).click();

			}

			String str = null;
			InputStreamReader isr = new InputStreamReader(new FileInputStream(readPath), "UTF-8");
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {

				if (str.contains("期开奖号码")) {
					// System.out.println("最新开奖号码结果:" + str.toString());
					String openPid = str.substring(str.toString().indexOf("第") + 1, str.toString().indexOf("期开奖号码"));
					String newLottyNumber = str.substring(str.toString().indexOf("：") + 1, str.length());
					// 开奖结果
					String numbersp[] = newLottyNumber.split(" ");

					int executiveRankingint = Integer.valueOf(executiveRanking);
					String catchNumber = numbersp[executiveRankingint];

				}

				// System.out.println("显示数据 :" + str.toString());
				if (str.contains("期")) {
					if (str.contains(strategyName + "冠军")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList01.add(str.toString());
						}
					} else if (str.contains(strategyName + "亚军")) {

						if (str != null && !str.isEmpty()) {
							specificSizeList02.add(str.toString());
						}
					} else if (str.contains(strategyName + "季军")) {

						if (str != null && !str.isEmpty()) {
							specificSizeList03.add(str.toString());
						}
					} else if (str.contains(strategyName + "第4名")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList04.add(str.toString());
						}
					} else if (str.contains(strategyName + "第5名")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList05.add(str.toString());
						}
					} else if (str.contains(strategyName + "第6名")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList06.add(str.toString());
						}
					} else if (str.contains(strategyName + "第7名")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList07.add(str.toString());
						}
					} else if (str.contains(strategyName + "第8名")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList08.add(str.toString());
						}
					} else if (str.contains(strategyName + "第9名")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList09.add(str.toString());
						}
					} else if (str.contains(strategyName + "第10名")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList10.add(str.toString());
						}

					}
				}
			}
			// 以上回滾資料結束

			Integer code = 0;
			if (specificSizeList01.size() != 0 && executiveRanking.equals("1")) {
				Integer codeResult = specificSize(specificSizeList01, 1, "3");
				code = code + codeResult;
				// 判断转换一 是否成功
			}

			if (specificSizeList02.size() != 0 && executiveRanking.equals("2")) {
				Integer codeResult = specificSize(specificSizeList02, 2, "3");
				code = code + codeResult;
			}

			if (specificSizeList03.size() != 0 && executiveRanking.equals("3")) {
				Integer codeResult = specificSize(specificSizeList03, 3, "3");
				code = code + codeResult;
			}

			if (specificSizeList04.size() != 0 && executiveRanking.equals("4")) {
				Integer codeResult = specificSize(specificSizeList04, 4, "3");
				code = code + codeResult;
			}

			if (specificSizeList05.size() != 0 && executiveRanking.equals("5")) {
				Integer codeResult = specificSize(specificSizeList05, 5, "3");
				code = code + codeResult;
			}

			if (specificSizeList06.size() != 0 && executiveRanking.equals("6")) {
				Integer codeResult = specificSize(specificSizeList06, 6, "3");
				code = code + codeResult;
			}

			if (specificSizeList07.size() != 0 && executiveRanking.equals("7")) {
				Integer codeResult = specificSize(specificSizeList07, 7, "3");
				code = code + codeResult;
			}

			if (specificSizeList08.size() != 0 && executiveRanking.equals("8")) {
				Integer codeResult = specificSize(specificSizeList08, 8, "3");
				code = code + codeResult;
			}

			if (specificSizeList09.size() != 0 && executiveRanking.equals("9")) {
				Integer codeResult = specificSize(specificSizeList09, 9, "3");
				code = code + codeResult;
			}

			if (specificSizeList10.size() != 0 && executiveRanking.equals("10")) {
				Integer codeResult = specificSize(specificSizeList10, 10, "3");
				code = code + codeResult;
			}

			if (code > 0) {
				// 如果有資料 則進行獲取的動作
				ArrayList<Object> betList = dbMap.get("specific0" + executiveRanking);
				stegosaurusStrategyBet(betList);
			}
			// 新增所有的資料之後把全域變數清空
			dbMap.clear();
			issuedPodStr = "";
			// DBService.saveLuckeyAirShipData(luckShipdbList);
			//
		} catch (Exception e) {
			System.out.println("發生錯誤:" + e);
			LineNotify.callEvent(lineGoldenKey, "系統發生錯誤" + e);
		}

		// 整行讀取

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

	public static String isInnerPeriod() {

		String webViewBeforeBitNumber = "";
		// 取得頁面上的號碼

		String beforePeriod = webObj.findElement(By.xpath("//*[@id='app']/div/nav/div[1]/div[1]/div/div/div[2]"))
				.getText();
		// 轉換頁面上的期號
		beforePeriod = beforePeriod.replace("期开奖", "");
		beforePeriod = beforePeriod.substring(beforePeriod.length() - 3, beforePeriod.length());
		Integer beforePeriodInt = Integer.valueOf(beforePeriod);
		String theDrawPeriod = webObj.findElement(By.xpath("//*[@id='betWrapper']/div[1]/div[3]")).getText();
		theDrawPeriod = theDrawPeriod.substring(0, theDrawPeriod.indexOf("期"));
		theDrawPeriod = theDrawPeriod.substring(theDrawPeriod.length() - 3, theDrawPeriod.length());
		Integer thisPeriodint = Integer.valueOf(theDrawPeriod);

		if (thisPeriodint - beforePeriodInt == 1) {
			webViewBeforeBitNumber = webObj
					.findElement(By.xpath("//*[@id='app']/div/nav/div[1]/div[1]/div/a/div/div[1]")).getText();
		}
		return webViewBeforeBitNumber;
	}

	public static int getGspedNowPeriod() {

		// 取得頁面上的號碼
		String beforePeriod = webObj.findElement(By.xpath("//*[@id='app']/div/nav/div[1]/div[1]/div/div/div[2]"))
				.getText();
		// 轉換頁面上的期號
		beforePeriod = beforePeriod.replace("期开奖", "");
		beforePeriod = beforePeriod.substring(beforePeriod.length() - 3, beforePeriod.length());
		Integer beforePeriodInt = Integer.valueOf(beforePeriod);
		String theDrawPeriod = webObj.findElement(By.xpath("//*[@id='betWrapper']/div[1]/div[3]")).getText();
		theDrawPeriod = theDrawPeriod.substring(0, theDrawPeriod.indexOf("期"));
		theDrawPeriod = theDrawPeriod.substring(theDrawPeriod.length() - 3, theDrawPeriod.length());
		Integer thisPeriodint = Integer.valueOf(theDrawPeriod);

		return thisPeriodint;
	}

	public static int specificSize(ArrayList<String> numberOneList, int ranking, String errorCount) {
		Integer code = 0;
		try {

			// 取得最新的一列
			String firstTwoTimesPeriodStr = numberOneList.get(numberOneList.size() - 2);

			// 取得最新的一列
			String predictionPeriodStr = numberOneList.get(numberOneList.size() - 1);

			if (firstTwoTimesPeriodStr.contains("挂")) {
				System.out.println("偵測到【掛】名次完整資料為:" + firstTwoTimesPeriodStr);
				stegosaurusOverweight += 1;
				// 這代表要啟動加碼機制
				if (!stegosaurusAutomaticRenewal) {
					LineNotify.callEvent(lineGoldenKey, "名次" + ranking + "發生(掛)情形，目前已經暫停下單，如需再次啟動起按啟動下單按鍵");
					stegosaurusstrategyTimeout = false;
				}
			}

			// 則代表在加碼的情況之下要開始解除他
			if (stegosaurusOverweight > 0 && firstTwoTimesPeriodStr.contains("中")) {
				stegosaurusDismissal += 1;
			}
			// 則是代表中三次
			if (stegosaurusDismissal >= 3) {
				LineNotify.callEvent(lineGoldenKey,
						"名次" + ranking + "解除總資金加碼第 " + stegosaurusOverweight + " 層，請判斷是否更換策略!");
				stegosaurusOverweight = 0;
				stegosaurusDismissal = 0;
			}

			// 如果上一期間是掛掉的狀態，則才會取得最新一期間的預測號碼
			String periodStr = numberOneList.get(numberOneList.size() - 1);
			System.out.println("取得最新一期名次【" + ranking + "】的資料:" + periodStr);

			String opex = periodStr.substring(0, periodStr.indexOf("期 "));
			String strategy = periodStr.substring(periodStr.indexOf("期 ") + 1, periodStr.indexOf("【"));
			strategy = strategy.replace(" ", "");
			String bend_key = opex + "-" + ranking;
			bend_key = bend_key.replace(" ", "");

			String[] journal = opex.split("-");
			int startPeriod = Integer.valueOf(journal[0]);
			int endPeriod = Integer.valueOf(journal[1]);

			System.out.println("策略名稱= " + strategy + "取得策略唯一KEY = " + bend_key + "預測開始期數: " + startPeriod + "結束時期數 : "
					+ endPeriod);
			// 獲取網頁上的期數
			String openPrid = webObj.findElement(By.xpath("//*[@id='betWrapper']/div[1]/div[3]")).getText();
			openPrid = openPrid.substring(0, openPrid.indexOf("期"));
			openPrid = openPrid.trim();
			openPrid = openPrid.substring(openPrid.length() - 3, openPrid.length());

			issuedPodStr = openPrid;

			int issuedNumber = Integer.valueOf(issuedPodStr);

			// 必須小於等於 彩哥哥系統的預測計畫結束 Period 並且 大於等於 彩哥哥計畫周期的Period
			// 以下取得加碼周期 例如三次 四次 五次 (等待開始的這個周期)

			Integer codingPeriodStart = periodStr.indexOf("(") == -1 ? periodStr.indexOf("（") + 1
					: periodStr.indexOf("(") + 1;

			Integer codingPeriodend = periodStr.indexOf(")") == -1 ? periodStr.indexOf("）") : periodStr.indexOf(")");

			String codingPeriodCount = periodStr.substring(codingPeriodStart, codingPeriodend);

			System.out.println("取得網頁上下單周期期數:" + issuedPodStr + "取得即將下單的周期:" + codingPeriodCount);
			Integer codingper = Integer.valueOf(codingPeriodCount);
			System.out.println("下單BetMoney的 幾期  codingper:" + codingper);

			// 錯誤周期 大於多少 // 保留使用
			Integer errorCounts = Integer.valueOf(errorCount);

			// 判斷爬蟲回來 在這區間之內
			if (issuedNumber <= endPeriod && issuedNumber >= startPeriod) {
				// 意思是等待開獎這個周期 必須小於等於3 我們才會在新增一筆到乾淨的下單table當中
				if (codingper <= errorCounts) {

					// 九碼的策略 這裡是獲得前一次的開獎計劃資訊
					Integer eductIndexfirst = predictionPeriodStr.indexOf("【") + 1;
					Integer eductIndexlest = predictionPeriodStr.indexOf("】");
					String getpredictionNo = predictionPeriodStr.substring(eductIndexfirst, eductIndexlest);
					getpredictionNo = getpredictionNo.replace(" ", ",");
					ArrayList<Object> dBlist = new ArrayList<>();

					dBlist.add(issuedNumber); // 期數
					dBlist.add(ranking); // 名次
					dBlist.add(getpredictionNo); // 預測號碼結果
					dBlist.add(codingPeriodCount);
					dBlist.add(strategy); // 策略中文
					dBlist.add(bend_key);// 進入內部的key
					System.out.println("---------------------------");
					System.out.println("下單期數為:" + issuedNumber);
					System.out.println("此次下單猜測第幾名:" + ranking + "預測下單號碼為:" + getpredictionNo);
					System.out.println("目前是第幾關 : " + codingPeriodCount);
					System.out.println("---------------------------");
					if (ranking == 10) {
						dbMap.put("specific10", dBlist);
					} else {
						dbMap.put("specific0" + ranking, dBlist);
					}
					code = 1;
				} else {
					if (!publineToken.isEmpty())
						LineNotify.callEvent(publineToken,
								"策略 : " + publineToken + " 目前高於:" + errorCount + "次錯誤，" + "請立即轉換，等倍加碼的動作");
				}

			} else {
				System.out.println("錯誤: 爬蟲回來的周期與檔案內部周期不相符合");
			}

		} catch (Exception e) {
			System.out.println("發生錯誤:" + e);
		}
		return code;
	}

	public static boolean stegosaurusStrategyBet(ArrayList<Object> betList) throws InterruptedException {

		int ranking = (int) betList.get(1);
		Object StrgosaurusbetNo = betList.get(2);
		int[] betNumber = StringArrToIntArr(StrgosaurusbetNo.toString().split(","));
		int codingPeriodCount = Integer.valueOf((String) betList.get(3));
		int bitOrderMoneyint = stegosaurusBitMoney[codingPeriodCount - 1];

		// 如果是到3週期時候 我們紀錄進去一筆
		if (codingPeriodCount >= 3) {
			// 存入 關卡其 // 預測號碼
			highPassCount.put(betList.get(0).toString(), betNumber);
		}
		// http去撈取資料開講資料了
		if (highPassCount.size() != 0) {

			// HTTP出去 回來開獎號碼

			// 回滾 KEY 比對 中獎

//			中獎  //if() {
//				highPassCount.remove
//			}else{
			/// 真正的掛了
			// }

		}

		// 金額
		// 代表總本金有進行加碼動作
		if (stegosaurusOverweight > 0) {
			System.out.println("劍龍策略進行總資金double動作!!");
			System.out.println("目前總資金加碼狀態為:" + stegosaurusOverweight);
			System.out.println("我們即將進行本金加成的動作...");
			switch (stegosaurusOverweight) {
			case 1:
				System.out.println("加碼初始總本金第一階層 2 倍");
				bitOrderMoneyint = bitOrderMoneyint * 2;
				break;
			case 2:
				System.out.println("加碼初始總本金第二階層 4 倍");
				bitOrderMoneyint = bitOrderMoneyint * 4;
				break;
			case 3:
				System.out.println("加碼初始總本金第三階層 8 倍");
				bitOrderMoneyint = bitOrderMoneyint * 8;
				break;
			default:
				break;
			}
		}
		String bitOrderMoneyStr = String.valueOf(bitOrderMoneyint);

		if (ranking == 1 && !bitOrderMoneyStr.equals("0")) {
			for (Integer bitNo : betNumber) {
				switch (bitNo) {
				case 1:
					webObj.findElement(By.xpath(
							"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[1]/td[3]/input"))
							.sendKeys(bitOrderMoneyStr);
					break;
				case 2:
					webObj.findElement(By.xpath(
							"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[2]/td[3]/input"))
							.sendKeys(bitOrderMoneyStr);
					break;
				case 3:
					webObj.findElement(By.xpath(
							"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[3]/td[3]/input"))
							.sendKeys(bitOrderMoneyStr);
					break;
				case 4:
					webObj.findElement(By.xpath(
							"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[4]/td[3]/input"))
							.sendKeys(bitOrderMoneyStr);
					break;
				case 5:
					webObj.findElement(By.xpath(
							"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[5]/td[3]/input"))
							.sendKeys(bitOrderMoneyStr);
					break;
				case 6:
					webObj.findElement(By.xpath(
							"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[6]/td[3]/input"))
							.sendKeys(bitOrderMoneyStr);
					break;
				case 7:
					webObj.findElement(By.xpath(
							"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[7]/td[3]/input"))
							.sendKeys(bitOrderMoneyStr);
					break;
				case 8:
					webObj.findElement(By.xpath(
							"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[8]/td[3]/input"))
							.sendKeys(bitOrderMoneyStr);
					break;
				case 9:
					webObj.findElement(By.xpath(
							"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[9]/td[3]/input"))
							.sendKeys(bitOrderMoneyStr);
					break;
				case 10:
					webObj.findElement(By.xpath(
							"//*[@id='betWrapper']/div[2]/div/div[1]/div[2]/div/div[1]/table[1]/tbody/tr[10]/td[3]/input"))
							.sendKeys(bitOrderMoneyStr);
					break;

				default:
					break;
				}
			}
		}

		// 點即送出效果 先跳出懸浮視窗
		Thread.sleep(500);
		webObj.findElement(By.xpath("//*[@id='betWrapper']/div[2]/div/div[1]/div[3]/div[2]")).click();

		Thread.sleep(1500);
		webObj.findElement(By.xpath("//*[@id='betWrapper']/div[2]/div/div[1]/div[4]/div/div[3]/div[1]")).click();

		return false;

	}

	public static boolean isJudgingElement(WebDriver webDriver, By by) {
		try {
			webDriver.findElement(by);
			return true;
		} catch (Exception e) {
			System.out.println("不存在此元素");
			return false;
		}
	}

	public static int[] StringArrToIntArr(String[] s) {
		int[] result = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			result[i] = Integer.parseInt(s[i]);
		}
		return result;
	}
}
