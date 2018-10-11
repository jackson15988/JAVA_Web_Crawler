package com.fubon.esb.core;

import com.fubon.esb.util.DriverFactory;


//登入NSP的畫面
public  class QuitDriver  {
	
	
	
	public static void quitDriver(){
		System.out.println("呼叫到quitDriver()");
		
		//關掉driver
		DriverFactory.getDriver().quit();
		
		System.out.println("己關閉瀏覽器");
	}
	
	public static void closeDriver(){
		System.out.println("呼叫到closeDriver()");
		
		//關掉瀏覽器
		DriverFactory.getDriver().close();
		
		System.out.println("己關閉瀏覽器");
	}	
	
}
