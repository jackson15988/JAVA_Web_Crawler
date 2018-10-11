package com.fubon.esb.core;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.fubon.esb.util.DriverFactory;
import com.fubon.esb.util.SeleniumUtil;


//登入NSP的畫面
public class Login {

	
	public static void loginWEB() throws Exception {
		try {
			//登入
			DriverFactory.getDriver().get("https://signal.wetalktrade.com/login");
			
			Thread.sleep(1000);
			
			DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//*[@id='username']"))).sendKeys("estapg@mailnesia.com");
			//*[@id="username"]
			
			DriverFactory.getDriver().findElement(By
					.xpath("//*[@id='pass']")).sendKeys("9487941");
			//*[@id="pass"]
			
			DriverFactory.getDriver().findElement(By
					.xpath("//*[@id='LoginSubmit']")).click();
			//*[@id="LoginSubmit"]
		
			
			Thread.sleep(1000);
			
			//登入問題
			try {
				DriverFactory.getDriver().findElement(By
						.xpath("/html/body/div[6]/md-dialog/md-dialog-actions/button[2]")).click();
//				/html/body/div[6]/md-dialog/md-dialog-actions/button[2]
				
			} catch (Exception e) {

			}
			
			

			
			
			Thread.sleep(3000);
			
			

			SeleniumUtil.selectValue("100","//*[@id='DataTables_Table_0_length']/label/select");
			//*[@id="DataTables_Table_0_length"]/label/select
			

			Integer checknumber = 0;
			
			//取得筆數
			for(Integer i =1;i<101; i++){
				
				try {
					
				if(i==1){
					
					try {
						DriverFactory.getDriver().findElement(By
								.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]"));		

					} catch (Exception e) {
						DriverFactory.getDriver().findElement(By
								.xpath("//*[@id='DataTables_Table_0']/tbody/tr"));
						checknumber = i;
						break;
					}
	
				}else{
					DriverFactory.getDriver().findElement(By
							.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]"));
					
				}

				} catch (Exception e) {
					
					checknumber= i-1;
					break;
				}	
				
			};
			
			System.out.println("checknumber："+checknumber);
			
			String webtxt = DriverFactory.getDriver().findElement(By
					.xpath("//*[@id='board']")).getText();
//			//*[@id="board"]
			
			ArrayList<String> transactionlist = new ArrayList<String>();
			
			for(Integer i =1;i<checknumber; i++){
				try {	
					if(i==1){
						try {
							transactionlist.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]")).getText());
						} catch (Exception e) {
							transactionlist.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr")).getText());
							break;
						}
					}else{
						transactionlist.add(DriverFactory.getDriver().findElement(By
								.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]")).getText());
						
					}

				} catch (Exception e) {
					
					break;
				}	
				
			};
			
//			SIGNAL ID
//			COUNTER
//			ACTION
//			P/L PIPS
//			CURRENT PRICE
//			OPEN PRICE
//			TARGET PRICE
//			
			
			ArrayList<String> listSIGNAL_ID = new ArrayList<String>();
			ArrayList<String> listCOUNTER = new ArrayList<String>();
			ArrayList<String> listACTION = new ArrayList<String>();
			ArrayList<String> listPL_PIPS = new ArrayList<String>();
			ArrayList<String> listCURRENT_PRICE = new ArrayList<String>();
			ArrayList<String> listOPEN_PRICE = new ArrayList<String>();
			ArrayList<String> listTARGET_PRICE = new ArrayList<String>();
			
			
			
			for(Integer i =1;i<=checknumber; i++){
				try {	
					if(i==1){
						try {
							listSIGNAL_ID.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[1]")).getText());
							//*[@id="DataTables_Table_4"]/tbody/tr[1]/td[1]
							listCOUNTER.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[3]")).getText());
							//*[@id="DataTables_Table_4"]/tbody/tr[1]/td[3]
							listACTION.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[4]")).getText());
							//*[@id="DataTables_Table_4"]/tbody/tr[1]/td[4]
							listPL_PIPS.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[5]")).getText());
							//*[@id="DataTables_Table_4"]/tbody/tr[1]/td[5]
							listCURRENT_PRICE.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[6]")).getText());
							//*[@id="DataTables_Table_4"]/tbody/tr[1]/td[6]
							listOPEN_PRICE.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[7]")).getText());
							//*[@id="DataTables_Table_4"]/tbody/tr[1]/td[7]
							listTARGET_PRICE.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[8]")).getText());
							//*[@id="DataTables_Table_4"]/tbody/tr[1]/td[8]
							
							
						} catch (Exception e) {
							listSIGNAL_ID.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[1]")).getText());

							listCOUNTER.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[3]")).getText());

							listACTION.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[4]")).getText());

							listPL_PIPS.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[5]")).getText());

							listCURRENT_PRICE.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[6]")).getText());

							listOPEN_PRICE.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[7]")).getText());

							listTARGET_PRICE.add(DriverFactory.getDriver().findElement(By
									.xpath("//*[@id='DataTables_Table_0']/tbody/tr/td[8]")).getText());

							break;
						}
					}else{
						
						listSIGNAL_ID.add(DriverFactory.getDriver().findElement(By
								.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[1]")).getText());

						listCOUNTER.add(DriverFactory.getDriver().findElement(By
								.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[3]")).getText());

						listACTION.add(DriverFactory.getDriver().findElement(By
								.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[4]")).getText());

						listPL_PIPS.add(DriverFactory.getDriver().findElement(By
								.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[5]")).getText());

						listCURRENT_PRICE.add(DriverFactory.getDriver().findElement(By
								.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[6]")).getText());

						listOPEN_PRICE.add(DriverFactory.getDriver().findElement(By
								.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[7]")).getText());

						listTARGET_PRICE.add(DriverFactory.getDriver().findElement(By
								.xpath("//*[@id='DataTables_Table_0']/tbody/tr["+i+"]/td[8]")).getText());
						
					}

				} catch (Exception e) {
					
					break;
				}	
				
			};
			
			
			
			System.out.println("********************************");
			
			System.out.println(webtxt);
			
			System.out.println("********************************");
			

			System.out.println("listSIGNAL_ID："+listSIGNAL_ID);
			System.out.println("listCOUNTER："+listCOUNTER);
			System.out.println("listACTION："+listACTION);
			System.out.println("listPL_PIPS："+listPL_PIPS);
			System.out.println("listCURRENT_PRICE："+listCURRENT_PRICE);
			System.out.println("listOPEN_PRICE："+listOPEN_PRICE);
			System.out.println("listTARGET_PRICE："+listTARGET_PRICE);
			
			
			
			//參考
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
