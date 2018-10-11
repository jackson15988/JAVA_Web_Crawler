package com.fubon.esb.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;



public class SeleniumTabsUtil {

	
	public static void Tab0(){
		DriverFactory.getDriver().switchTo().window(DriverFactory.getTabs().get(0));
		System.out.println("getTabs().get(0)");
	}
	public static void Tab1(){
		DriverFactory.getDriver().switchTo().window(DriverFactory.getTabs().get(1));
		System.out.println("getTabs().get(1)");
	}
	public static void KeyDown(String xpath){
		WebElement keyDown =DriverFactory.getDriver().findElement(By.xpath(xpath));
		keyDown.sendKeys(Keys.DOWN);

	}
	

	
}
