package com.fubon.esb.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandleNumber {

	public static void main(String[] args) {
		numberConversionPage("04091007030802060501");
		try {
			List<String> ewx= getCaipiaoOpenNumber("D:/LuckeyAirship/20200225.txt");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public static ArrayList<String> numberConversionPage(String webNumber) {

		ArrayList<String> beforeBitNo = new ArrayList<>();
		String waitWebNumber = webNumber;

		while (!waitWebNumber.isEmpty()) {
			String catchNo = waitWebNumber.substring(0, 2);
			beforeBitNo.add(catchNo);
			waitWebNumber = waitWebNumber.replace(catchNo, "");
		}
		System.out.println("開始切割字串，取得上一周期所獲的網頁上的開獎號碼:" + beforeBitNo);
		return beforeBitNo;

	}

	/**
	 * @author admin 取得採哥哥的開獎號碼
	 * @param readPath
	 * @return
	 * @throws IOException
	 */
	public static List<String> getCaipiaoOpenNumber(String readPath) throws IOException {
		String str = null;
		InputStreamReader isr = new InputStreamReader(new FileInputStream(readPath), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
//		br = new BufferedReader(new FileReader(readPath));
		List<String> caipiaoOpenNumber = new ArrayList<>();
		System.out.println("進行讀取彩哥哥上一周期開獎號碼....!");
		while ((str = br.readLine()) != null) {
			if (str.contains("期开奖号码")) {
				System.out.println("彩哥哥顯示最新开奖号码结果:" + str.toString());
				String openPid = str.substring(str.toString().indexOf("第") + 1, str.toString().indexOf("期开奖号码"));
				String newLottyNumber = str.substring(str.toString().indexOf("：") + 1, str.length());
				// 开奖结果
				String numbersp[] = newLottyNumber.split(" ");
				System.out.println(numbersp.toString());
				caipiaoOpenNumber = new ArrayList<String>(Arrays.asList(numbersp)); // n

			}
		}
		return caipiaoOpenNumber;
	}
}
