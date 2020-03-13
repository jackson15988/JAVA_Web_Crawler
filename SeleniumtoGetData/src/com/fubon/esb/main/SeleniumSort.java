package com.fubon.esb.main;

import java.util.ArrayList;

import com.fubon.esb.core.ActivatePostPay;
import com.fubon.esb.core.Login;
import com.fubon.esb.core.Login2;
import com.fubon.esb.core.QuitDriver;
import com.fubon.esb.util.DateUtil;
import com.fubon.esb.util.DriverFactory;

public class SeleniumSort {	
	//紀錄現在是哪一個SerialNo
	public static Integer nowSerialNo ;
	public static String  theStatus ;
	public static String  theMessage ;
	
	public static void selenSort() throws InterruptedException {
		
		
		// 根據有幾筆的資料的Status=N，取SerialNo來跑for
		
			try {	
				
//			
//				Login.loginWEB();
				 
//				QuitDriver.quitDriver();
//				 
//				System.out.println(DateUtil.SetTime());
				 
			} catch (Exception e) {				
				e.printStackTrace();

				System.out.println("***********************************************************");				
//				Thread.sleep(2000);
//				//關閉網頁
//				QuitDriver.quitDriver();
//				//全部設定重製
//				DriverFactory.setAllNull();	
			}
//				System.out.println("程式結束");				
				System.out.println("===========================================================");

		
	}

}
