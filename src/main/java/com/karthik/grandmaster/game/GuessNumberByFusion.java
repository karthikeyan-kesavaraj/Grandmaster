package com.karthik.grandmaster.game;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.karthik.grandmaster.util.Printer;

@Component
public class GuessNumberByFusion{

	@Value("${maxvalue}")
	private int maxValue;

	@Autowired
	private Printer printer;

	@Autowired
	private FusionSearch fusionSearch;

	private int lowerbound=0;

	@Value("${maxvalue}")
	private int higherbound;

	Random randomGenerator = new Random();

	private int la = 0;

	private int ha = 0;

	private int varient = 0;
	private int lastVarient = 0;

	public int getMaxValue(){
		return this.maxValue;
	}

	public int random(){
		return randomGenerator.nextInt( this.higherbound-this.lowerbound)+this.lowerbound;
	}

	public int lower(int higherbound){
		if(validate(lowerbound,higherbound)){
			la++;
			setHigherbound(higherbound-1);
			if(la==1){
				return lowerbound;
			}
			return computePossibleValue();
		}else if(la>0){
			printer.printMessage(lowerbound+" should be your value.");
		}else{
			printer.printMessage("Number could not be lower than, "+lowerbound+" your entry should be wrong. Try again");
		}
		return lowerbound;

	}

	public int higher(int lowerbound){
		if(validate(lowerbound,higherbound)){
			ha++;
			setLowerbound(lowerbound+1);
			if(ha==1){
				return this.random();
			}
			return computePossibleValue();

		}else if(ha>0){
			printer.printMessage(higherbound+" should be your value.");
		}else{
			printer.printMessage("Number could not be higher than, "+higherbound+" your entry should be wrong. Try again");
		}
		return higherbound;

	}

	private int computePossibleValue(){
		varient = computeVarient(this.lowerbound,this.higherbound);
		System.out.println("varient "+varient);
		System.out.println("lastvarient "+lastVarient);
		if(lastVarient>varient){
			
			lastVarient=varient;
			return this.random();

		}
		
		lastVarient=varient;
		return fusionSearch.getRoot(lowerbound, higherbound, computeMultiplier(varient));
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
		this.ha=0;
		this.la=0;
		this.varient=0;
		this.lastVarient=0;
	}

	private boolean validate(int a,int b){
		if(a==b){
			return false;
		}else{
			return true;
		}
	}

	private int computeVarient(int lowerbound,int higherbound){
		int varient = 0;
		int n = 0;;
		n = higherbound-lowerbound;
		varient = (int)(Math.log10(n)+1);
		return varient-1;
	}

	private int computeMultiplier(int varient){
		return (int)(Math.pow(10, varient));
	}

}
