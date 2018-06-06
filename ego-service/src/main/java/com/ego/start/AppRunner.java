package com.ego.start;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppRunner {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
