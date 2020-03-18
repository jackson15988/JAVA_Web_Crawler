package com.fubon.esb.bo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
		String resultPeriod = webObj.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div/div[3]/font")).getText();
		String winningNumbers = webObj.findElement(By.xpath("/html/body/div[2]/div[3]/div[2]/div/div[3]/span"))
				.getText();
		resultPeriod = resultPeriod.trim();
		String issueStr = resultPeriod.substring(resultPeriod.length() - 3, resultPeriod.length());
		int issue = Integer.valueOf(issueStr);
		System.out.println("開獎期數 :" + resultPeriod + "  開獎號碼:" + winningNumbers + "issue:" + issue);

	}

}
