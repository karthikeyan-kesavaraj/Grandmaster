package com.karthik.grandmaster.game;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.karthik.grandmaster.util.Printer;

@Component
public class BinarySearch {

	@Value("${maxvalue}")
	private int maxValue;
	
	@Autowired
	private Printer printer;
	
	private int lowerbound=0;
	
	@Value("${maxvalue}")
	private int higherbound;
	
	Random randomGenerator = new Random();
	
	public int getMaxValue(){
		return this.maxValue;
	}
	
	public int random(){
		return randomGenerator.nextInt( this.higherbound-this.lowerbound)+this.lowerbound;
	}
	
	public int lower(int higherbound){
		if(validate(lowerbound,higherbound)){
			setHigherbound(higherbound);
		}else{
			printer.printMessage("Number could not be higher than, "+lowerbound+" your entry should be wrong. Try again");
			return lowerbound;
		}
		return lowerbound;
	}
	
	public int higher(int lowerbound){
		if(validate(lowerbound,higherbound)){
			setLowerbound(lowerbound);
		}else{
			printer.printMessage("Number could not be higher than, "+lowerbound+" your entry should be wrong. Try again");
			return higherbound;
		}
		
		return higherbound;
	}
	
	public void setHigherbound(int higherbound){
		this.higherbound = higherbound;
	}
	
	public void setLowerbound(int lowerbound){
		this.lowerbound = lowerbound;
	}
	
	public int getHigherbound(){
		return this.higherbound;
	}
	
	public int getLowerbound(){
		return this.lowerbound;
	}
	
	public void flush(){
		this.higherbound=this.maxValue;
		this.lowerbound=0;
	}
	
	private boolean validate(int a,int b){
		if(a==b){
			return false;
		}else{
			return true;
		}
	}
	
}
