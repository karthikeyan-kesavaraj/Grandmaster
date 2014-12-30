package com.karthik.grandmaster.util;

import java.io.PrintStream;

import org.springframework.stereotype.Component;

@Component
public class Printer {
	
	
	static PrintStream ps = System.out;
	
	
	public void printMessage(String s){
		ps.println(Constants.MY_ID+s);
	}
	
	public void printBanner(){
		ps.println(Constants.BANNER);
	}
	
	public void printGreeting(){
		printMessage(Constants.MY_GREETING);
	}
	
	public void printRules(){
		
		printMessage(Constants.RULES);
	}
	
	public void printUser(){
		ps.print(Constants.YOUR_ID);
	}
	
	public String getInitialRule(){
		return Constants.INITIAL_INPUT;
	}

}
