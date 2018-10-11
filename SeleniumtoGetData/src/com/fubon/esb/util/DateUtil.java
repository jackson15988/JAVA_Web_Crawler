package com.fubon.esb.util;

import java.text.SimpleDateFormat;
import java.util.Date;


//parents
public class DateUtil {

	

	public static String SetTime(){		
		
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
	}
//	public static void main(String[] args) {
//		System.out.println(SetTime());
//	}


	
}
