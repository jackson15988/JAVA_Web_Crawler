package com.fubon.esb.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReaderOrder {

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

	static int doubleCount = 0;

	public static void main(String[] args) {
		readOrder("D:/LuckeyAirship/20200225.txt", "彩票大小A計畫", "1");
	}

	public static ArrayList<Object> readOrder(String readPath, String strategyName, String orderNumber) {
		ArrayList<Object> order1list = new ArrayList<>();
		BufferedReader br;
		try {
			String str = null;
			InputStreamReader isr = new InputStreamReader(new FileInputStream(readPath), "UTF-8");
			br = new BufferedReader(isr);

			while ((str = br.readLine()) != null) {
				System.out.println("讀取到不為空");
				System.out.println(str.toString());

				if (str.contains("期")) {
					System.out.println("期--彩票有資料");
					if (str.contains(strategyName + "冠军")) {
						System.out.println("進入冠軍");
						if (str != null && !str.isEmpty()) {
							specificSizeList01.add(str.toString());
						}
					} else if (str.contains(strategyName + "亚军大小")) {
						System.out.println("進入亞軍大小");
						if (str != null && !str.isEmpty()) {
							specificSizeList02.add(str.toString());
						}
					} else if (str.contains(strategyName + "季军大小")) {
						System.out.println("進入季軍大小");
						if (str != null && !str.isEmpty()) {
							specificSizeList03.add(str.toString());
						}
					} else if (str.contains(strategyName + "第4名大小")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList04.add(str.toString());
						}
					} else if (str.contains(strategyName + "第5名大小")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList05.add(str.toString());
						}
					} else if (str.contains(strategyName + "第6名大小")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList06.add(str.toString());
						}
					} else if (str.contains(strategyName + "第7名大小")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList07.add(str.toString());
						}
					} else if (str.contains(strategyName + "第8名大小")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList08.add(str.toString());
						}
					} else if (str.contains(strategyName + "第9名大小")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList09.add(str.toString());
						}
					} else if (str.contains(strategyName + "第10名大小")) {
						if (str != null && !str.isEmpty()) {
							specificSizeList10.add(str.toString());
						}
					}
				}
			}

			if (specificSizeList01.size() != 0) {
				String periodStr = specificSizeList01.get(specificSizeList01.size() - 2);
				System.out.println("取得倒數第二筆" + periodStr);
				if (periodStr.contains("挂")) {
					doubleCount += 1;
				} else {
					String befor2 = specificSizeList01.get(specificSizeList01.size() - 2);
					String befor3 = specificSizeList01.get(specificSizeList01.size() - 3);
					String befor4 = specificSizeList01.get(specificSizeList01.size() - 4);
					int cleanDoubleStatus = 0;
					if (befor2.contains("中")) {
						cleanDoubleStatus += 1;
					} else if (befor3.contains("中")) {
						cleanDoubleStatus += 1;
					} else if (befor4.contains("中")) {
						cleanDoubleStatus += 1;
					}
					if (doubleCount != 0 && cleanDoubleStatus >= 3) {
						System.out.println("解除加碼狀態-1" + doubleCount);
						doubleCount = doubleCount - 1;
					}

					// 意味著目前不需要加碼 //取得最後一筆
					System.out.println("目前不需要加碼取得最後一筆");
					periodStr = specificSizeList01.get(specificSizeList01.size() - 1);
					System.out.println(periodStr);

					String opex = periodStr.substring(0, periodStr.indexOf("期 "));
					String strategy = periodStr.substring(periodStr.indexOf("期 ") + 1, periodStr.indexOf("【"));
					strategy = strategy.replace(" ", "");
					String bend_key = opex + "-" + "1";
					bend_key = bend_key.replace(" ", "");
					System.out.println("策略名稱= " + strategy);
					System.out.println("取得策略唯一KEY = " + bend_key);
					String[] journal = opex.split("-");
					int startPeriod = Integer.valueOf(journal[0]);
					int endPeriod = Integer.valueOf(journal[1]);
					System.out.println("預測開始期數" + startPeriod + "結束時期數" + endPeriod);

					System.out.println("開始進行()大小結果拆分");

					Integer codingPeriodStart = periodStr.indexOf("(") == -1 ? periodStr.indexOf("（") + 1
							: periodStr.indexOf("(") + 1;
					System.out.println("查詢到左邊括號位置結果：" + codingPeriodStart);

					Integer codingPeriodend = periodStr.indexOf(")") == -1 ? periodStr.indexOf("）")
							: periodStr.indexOf(")");
					System.out.println("查詢到右邊括號位置：" + codingPeriodend);
					String codingPeriodCount = periodStr.substring(codingPeriodStart, codingPeriodend);

					System.out.println("取得即將下單的周期:" + codingPeriodCount);
					Integer codingper = Integer.valueOf(codingPeriodCount);
					System.out.println("codingper:" + codingper);

					Integer eductIndexfirst = periodStr.indexOf("【") + 1;
					Integer eductIndexlest = periodStr.indexOf("】");
					String getpredictionNo = periodStr.substring(eductIndexfirst, eductIndexlest);
					String[] order = getpredictionNo.split(" ");
					String exOrder = "";
					for (String s : order) {
						s = s.trim();
						exOrder = exOrder + s + ",";
					}

					System.out.println("獲得即將下單的結果" + exOrder);

					order1list.add(startPeriod); // 開始期數
					order1list.add(endPeriod); // 結束期數
					order1list.add(1); // 名次
					order1list.add(exOrder); // 預測號碼結果
					order1list.add(codingPeriodCount); // 即將下單周期 總共三關
					order1list.add(doubleCount); // 總加碼到第幾次了

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return order1list;
	}

}
