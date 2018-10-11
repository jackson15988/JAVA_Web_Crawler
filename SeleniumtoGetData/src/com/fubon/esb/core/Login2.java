package com.fubon.esb.core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.fubon.esb.util.DriverFactory;


//登入NSP的畫面
public class Login2 {

	
	public static void loginESB2() throws Exception {
		try {
			//登入
			DriverFactory.getDriver().get("http://172.17.240.25/fubon-esb-web");
			
			Thread.sleep(2000);
			
			DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//*[@id='userId']"))).sendKeys("brian.pai");
			
			DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//*[@id='saveList2']/div/div[2]/div[2]/p[2]/input"))).sendKeys("9ol.0p;/");
			
			
			DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//*[@id='saveList2']/div/div[2]/div[2]/input"))).click();
							//*[@id="saveList2"]/div/div[2]/div[2]/input
			Thread.sleep(1000);
			
			
			//選單
			WebElement IndexButton = DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//*[@id='nav']/li[1]/a/span")));
			//*[@id="nav"]/li[1]/a/span
			
			
			DriverFactory.getActions().moveToElement(IndexButton).build().perform();
			
			Thread.sleep(2000);
			
			DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath("//*[@id='nav']/li[1]/ul/li[1]/a"))).click();
			//*[@id="nav"]/li[6]/ul/li[1]/a
			
			ArrayList<String> esblist = new ArrayList<String>();
			
//			esblist.add("AFEE062");
			esblist.add("AFEE081");
			esblist.add("AFEE082");
			esblist.add("AFEE083");
			esblist.add("AJWEE010");
			esblist.add("AJWEE060");
			esblist.add("AJWEE061");
			esblist.add("CE6128R");
			esblist.add("CEW013R");
			esblist.add("CEW1410R");
			esblist.add("CEW200R");
			esblist.add("CEW201R");
			esblist.add("CEW205R");
			esblist.add("CEW208R");
			esblist.add("CEW209R");
			esblist.add("CEW212R");
			esblist.add("CEW213R");
			esblist.add("CEW215R");
			esblist.add("CEW217R");
			esblist.add("CEW218R");
			esblist.add("CEW220R");
			esblist.add("CEW221R");
			esblist.add("CEW222R");
			esblist.add("CEW223R");
			esblist.add("CEW227R");
			esblist.add("CEW230R");
			esblist.add("CEW301R");
			esblist.add("CEW302R");
			esblist.add("CEW303R");
			esblist.add("CEW305R");
			esblist.add("CEW306R");
			esblist.add("CEW307R");
			esblist.add("CEW308R");
			esblist.add("CEW309R");
			esblist.add("CEW311R");
			esblist.add("CEW313R");
			esblist.add("CEW314R");
			esblist.add("CEW315R");
			esblist.add("CEW316R");
			esblist.add("CEW317R");
			esblist.add("CEW318R");
			esblist.add("CEW319R");
			esblist.add("CEW320R");
			esblist.add("CEW321R");
			esblist.add("CEW322R");
			esblist.add("CEW323R");
			esblist.add("CEW326R");
			esblist.add("CEW327R");
			esblist.add("CEW328R");
			esblist.add("CEW329R");
			esblist.add("CEW423R");
			esblist.add("CEW431R");
			esblist.add("EB012666");
			esblist.add("EB032191");
			esblist.add("EB12020019");
			esblist.add("EB142651");
			esblist.add("EB151153");
			esblist.add("EB256610");
			esblist.add("EB382201");
			esblist.add("EB602654A");
			esblist.add("EB602655A");
			esblist.add("EB602658A");
			esblist.add("EB602659A");
			esblist.add("EB602660");
			esblist.add("EBCN001");
			esblist.add("EBDDACNO01");
			esblist.add("EBHN001");
			esblist.add("EBHN002");
			esblist.add("EBHN003");
			esblist.add("EBHN004");
			esblist.add("EBHN005");
			esblist.add("EBHN006");
			esblist.add("EBLN001");
			esblist.add("EBLN002");
			esblist.add("EBLN003");
			esblist.add("EBLN007");
			esblist.add("EBLN008");
			esblist.add("EBLN009");
			esblist.add("EBLN010");
			esblist.add("EBLN011");
			esblist.add("EBLN012");
			esblist.add("EBPR30");
			
			
			//Input 單號
			
			for(Integer countTotal = 0;countTotal< esblist.size();countTotal++){
				
				System.out.println(esblist.get(countTotal));
				
				
				DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//*[@id='search_txn_code']"))).clear();
				
				Thread.sleep(1000);
				
				DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//*[@id='search_txn_code']"))).sendKeys(esblist.get(countTotal));
				//*[@id="viewSearchTxnList"]/input[10]
				
				Thread.sleep(1000);
				
				DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//*[@id='search_button']"))).click();
				//*[@id="search_button"]
				
				Thread.sleep(1000);
				
				DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//*[@id='content']/table/tbody[2]/tr/td[1]/input"))).click();
				//*[@id="content"]/table/tbody[2]/tr/td[1]/input
				
				Thread.sleep(1000);
				
				DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath("/html/body/div/div[3]/div/div[2]/p/button"))).click();
				//html/body/div/div[3]/div/div[2]/p/button
				
				
				Thread.sleep(2000);
				
			}
			
			
			//*[@id="channelTable"]/tbody/tr[2]/td
			
			
			
			
//			for(Integer countpage =1;countpage<20;countpage++){
//				
//				System.out.println("第"+countpage+"頁");
//				try {
//					for(int i=1;i<=10;i++){
//						
//						try {
//		//					System.out.println(i+"_");
//							String txt=DriverFactory.getDriver().findElement(By
//									.xpath("/html/body/div/div[3]/div/div[2]/div[1]/table/tbody[2]/tr["+i+"]/td[5]")).getText();
//							System.out.println(txt);
//							
//						} catch (Exception e) {
//							break;
//							
//						}
//						
//					}
//			
//				
//					if(countpage.equals(1)){
//						DriverFactory.getDriver().findElement(By
//								.xpath("//*[@id='content']/p/a")).click();
//								//html/body/div/div[3]/div/div[2]/div[1]/p/a
//					}else{
//						DriverFactory.getDriver().findElement(By
//								.xpath("//*[@id='content']/p/a[2]")).click();
//					}
//					
//						
//				} catch (Exception e) {
//						countpage=20;
//						System.out.println("第"+countpage+"頁了");
//				}
//				
//				Thread.sleep(1000);
//			}
//			for(int x=1;x<=10;x++){
//				
//				try {
////					System.out.println(x+"_");
//					String txt2=DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(By
//							.xpath("/html/body/div/div[3]/div/div[2]/div[1]/table/tbody[2]/tr["+x+"]/td[5]"))).getText();
//					
//					System.out.println(txt2);
//					
//				} catch (Exception e) {
//					countpage++;
//					break;
//					
//				}
//				
//			}
//			
//			//*[@id="content"]/p/a
//			
////			Thread.sleep(1000);
			
		} catch (Exception e) {
			String Err = "ESB0001";
			e.printStackTrace();
			throw new Exception(Err);
		}
	}

}
