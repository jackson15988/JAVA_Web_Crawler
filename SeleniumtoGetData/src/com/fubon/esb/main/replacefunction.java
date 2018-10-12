package com.fubon.esb.main;

import java.util.ArrayList;

import org.openqa.selenium.By;

import com.fubon.esb.util.DriverFactory;

public class replacefunction {
			
	public void replace() {
		
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
//		//*[@id="board"]
		
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
		
	}
	
	
}
