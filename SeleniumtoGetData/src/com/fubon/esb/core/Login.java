package com.fubon.esb.core;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.fubon.esb.main.replacefunction;
import com.fubon.esb.util.DriverFactory;
import com.fubon.esb.util.SeleniumUtil;

//登入NSP的畫面
public class Login {

	public static void loginWEB() throws Exception {
		try {
			// 登入
			DriverFactory.getDriver().get("https://signal.wetalktrade.com/login");

			Thread.sleep(1000);

			DriverFactory.getWait()
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']")))
					.sendKeys("estapg@mailnesia.com");
			// *[@id="username"]

			DriverFactory.getDriver().findElement(By.xpath("//*[@id='pass']")).sendKeys("9487941");
			// *[@id="pass"]

			DriverFactory.getDriver().findElement(By.xpath("//*[@id='LoginSubmit']")).click();
			// *[@id="LoginSubmit"]

			Thread.sleep(1000);

			// 登入問題
			try {
				DriverFactory.getDriver()
						.findElement(By.xpath("/html/body/div[6]/md-dialog/md-dialog-actions/button[2]")).click();
//				/html/body/div[6]/md-dialog/md-dialog-actions/button[2]

			} catch (Exception e) {

			}

			Thread.sleep(3000);

			SeleniumUtil.selectValue("100", "//*[@id='DataTables_Table_0_length']/label/select");
			// *[@id="DataTables_Table_0_length"]/label/select
			replacefunction sc = new replacefunction();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Boolean result = false;
			int count = 0;
			while (!result) {
				try {

					sc.replace();

					Thread.sleep(60 * 1000); // 设置暂停的时间 5 秒
					count++;
					System.out.println(sdf.format(new Date()) + "--循环执行第" + count + "次");
					if (count == 3) {
						result = true;
						break;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 參考
//			WebElement IndexButton = DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
//					.xpath("//*[@id='nav']/li[1]/a")));
//			
//			
//			DriverFactory.getDriver().findElement(By
//					.xpath("//*[@id='nav']/li[1]/a"));

			Thread.sleep(1000);

			System.out.println("完成");

		} catch (Exception e) {
			String Err = "ESB0001";
			e.printStackTrace();
			throw new Exception(Err);
		}
	}

}
