package com.fubon.esb.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.fubon.esb.util.DriverFactory;
import com.fubon.esb.util.SeleniumTabsUtil;

//進入NSP客資查詢的畫面
public class ActivatePostPay {

	public void callActivatePostPay() {
		System.out.println("呼叫到NSP_Activation");
	}

	public void moveToSubscriberButton() {
		// 點選門號開通
		WebElement subscriberOpenEnterButton = DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//*[@id='verticalmenu2']/li[5]/a")));
		DriverFactory.getActions().moveToElement(subscriberOpenEnterButton, 0, 0).perform();
		System.out.println("移動到SubscriberButton");
		//*[@id="verticalmenu2"]/li[5]/a
	}

	public void clickSubscriberButton() {
		// 月租啟用
		DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//*[@id='m2Link_199476']"))).click();
		System.out.println("按下SubscriberButton");
		// Xpath：//*[@id="m2Link_199476"]
	}

	public void finishActPostPay() {
		// 己點選月租啟用
		System.out.println("己點選月租啟用" + DriverFactory.getDriver().getTitle());

	}

	public static void ActPostPay() throws Exception {
		try {

			
			DriverFactory.getDriver().get("http://172.17.240.25/fubon-esb-web-3th_sit/viewLogin");
			DriverFactory.getDriver().findElement(By.name("password")).click();
			DriverFactory.getDriver().findElement(By.name("password")).click();
		    // ERROR: Caught exception [ERROR: Unsupported command [doubleClick | name=password | ]]
			DriverFactory.getDriver().findElement(By.xpath("//input[@value='登入系統']")).click();
			DriverFactory.getDriver().findElement(By.xpath("//ul[@id='nav']/li/a/span")).click();
			DriverFactory.getDriver().findElement(By.linkText("電文對應設定")).click();
			DriverFactory.getDriver().findElement(By.xpath("//th")).click();
			DriverFactory.getDriver().findElement(By.id("in_txn_code")).click();
			DriverFactory.getDriver().findElement(By.id("in_txn_code")).click();
			DriverFactory.getDriver().findElement(By.id("in_txn_code")).clear();
			DriverFactory.getDriver().findElement(By.id("in_txn_code")).sendKeys("085105");
			DriverFactory.getDriver().findElement(By.id("search_button")).click();
			
			
			
			
		} catch (Exception e) {
			String Err = "NSPACT_ERR001";
			e.printStackTrace();
			throw new Exception(Err);
		}
	}

}
