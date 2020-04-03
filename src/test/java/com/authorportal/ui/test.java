package com.authorportal.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH").format(new Date());
		System.out.println("=====" + timeStamp);
		

	}

}
