package com.fubon.esb.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class SeleniumUtil {
	static Integer screeNumber;
	
	// SelectByXpath
	public static void selectValue(String valueToSelect, String xpathExpression) {
		WebElement select = DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(xpathExpression)));
		Select dropDown = new Select(select);
		dropDown.selectByVisibleText(valueToSelect);
	}

	// SelectBycssSelector
	public static void selectValueBycssSelector(String valueToSelect,
			String selectorExpression) {
		WebElement select = DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
						.cssSelector(selectorExpression)));
		Select dropDown = new Select(select);
		dropDown.selectByVisibleText(valueToSelect);
	}

	// SelectByIndexandXpath
	public static void selectIndex(int indexToSelect, String xpathExpression) {
		WebElement select = DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(xpathExpression)));
		Select dropDown = new Select(select);
		dropDown.selectByIndex(indexToSelect);
	}

}
