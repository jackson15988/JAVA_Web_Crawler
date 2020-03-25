package com.fubon.esb.core;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BigSmall {


	public static void RunBigSmall(WebDriver webObj) {

		HashMap<String, String> oddnumber = new HashMap<>(); // 單
		HashMap<String, String> evennumber = new HashMap<>(); // 雙
		HashMap<String, String> bigNumber = new HashMap<>(); // 大
		HashMap<String, String> smallNumber = new HashMap<>(); // 小
		try {
			System.out.println("點選幸運飛艇");

			boolean clickLuckPag = isJudgingElement(webObj,
					By.xpath("//*[@id='app']/div/nav/div[2]/div[2]/div[1]/div[3]/div"));
			Thread.sleep(1000);
			while (!clickLuckPag) {
				clickLuckPag = isJudgingElement(webObj,
						By.xpath("//*[@id='app']/div/nav/div[2]/div[2]/div[1]/div[3]/div"));
				System.out.println("如操作者切勿自行切換自動化網頁的頁面，目前找不到幸運飛艇的頁面，搜尋三次失敗則會自行關閉程式");
				Thread.sleep(1000);
				if (clickLuckPag) {
					break;
				}
			}
			Thread.sleep(1000);
			// 進來之後要先點選幸運飛艇
			// //*[@id="app"]/div/nav/div[2]/div[2]/div[1]/div[3]/div
			webObj.findElement(By.xpath("//*[@id='app']/div/nav/div[2]/div[2]/div[1]/div[3]/div")).click();
			System.out.println("點選數字盤");
			webObj.findElement(By.xpath("//*[@id='app']/div/menu/div[2]")).click();
			Thread.sleep(1000);
			if (webObj.findElement(By.xpath("//*[@id='defaultTop']")).isSelected()) {
				webObj.findElement(By.xpath("//*[@id='defaultTop']")).click();
				// 清空元件
				webObj.findElement(By.xpath("//*[@id='betWrapper']/div[2]/div/div[1]/div[1]/div[2]")).click();
				Thread.sleep(1000);
			}
			Thread.sleep(1000);
			String html = webObj.findElement(By.xpath("//*[@id='betWrapper']/div[2]/div/div[2]/div[2]/table/tbody"))
					.getAttribute("innerHTML");

			int ret = 0;
			WebElement streak = webObj
					.findElement(By.xpath("//*[@id='betWrapper']/div[2]/div/div[2]/div[2]/table/tbody"));
			List<WebElement> fleetDetails = streak.findElements(By.tagName("tr"));

			for (WebElement detail : fleetDetails) {
				List<WebElement> tabel = detail.findElements(By.tagName("td"));

				if (tabel.size() != 2)
					continue;

				String ranking = tabel.get(0).getText();
				ranking = coverNumber(ranking);

				String periodCount = tabel.get(1).getText();
				periodCount = periodCount.replace("期", "");
				int periodCountInt = Integer.valueOf(periodCount);

				String betResult = tabel.get(0).getText();
				betResult = conversionBetResult(betResult);

				if (betResult.equals("单")) {
					oddnumber.put(ranking, periodCount);
				} else if (betResult.equals("双")) {
					evennumber.put(ranking, periodCount);
				} else if (betResult.equals("大")) {
					bigNumber.put(ranking, periodCount);
				} else if (betResult.equals("小")) {
					smallNumber.put(ranking, periodCount);
				}

				System.out.println("名次: " + ranking + " 期數:" + periodCount + "下單結果:" + betResult);

			}
			// 以上結束迴圈 並且把資料統整好了

			System.out.println(html);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isJudgingElement(WebDriver webDriver, By by) {
		try {
			webDriver.findElement(by);
			return true;
		} catch (Exception e) {
			System.out.println("不存在此元素 : " + by);
			return false;
		}
	}

	public static String coverNumber(String str) {
		String np = "";
		if (str.contains("亚军")) {
			np = "2";
		} else if (str.contains("冠军")) {
			np = "1";
		} else if (str.contains("第三名")) {
			np = "3";
		} else if (str.contains("第四名 ")) {
			np = "4";
		} else if (str.contains("第五名 ")) {
			np = "5";
		} else if (str.contains("第六名 ")) {
			np = "6";
		} else if (str.contains("第七名 ")) {
			np = "7";
		} else if (str.contains("第八名 ")) {
			np = "8";
		} else if (str.contains("第九名 ")) {
			np = "9";
		} else if (str.contains("第十名 ")) {
			np = "10";
		}
		return np;
	}

	public static String conversionBetResult(String strResult) {
		String strResu = "";
		if (strResult.contains("小")) {
			strResu = "小";
		} else if (strResult.contains("大")) {
			strResu = "大";
		} else if (strResult.contains("单")) {
			strResu = "单";
		} else if (strResult.contains("双")) {
			strResu = "双";
		}

		return strResu;

	}
}
