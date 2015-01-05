package com.karthik.grandmaster.game;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.karthik.grandmaster.util.Printer;

/**
* GuessNumberByFusion class performs the search for the guessed number
* This uses the instance of Fusion Search and gets the node based on the tree generated
* The class creates the tree based on the higher limit of the numbers.
* Provides reasonable guesses like 500,300 based on the input
* Ex. At any point, if the higher bound is 900 & lower bound is 1, then, 
* It uses the fusion tree and creates the nodes in variant of 100.
* Ex. At any point, if the higher bound is 90 and the lower bound is 1, then
* It uses the fusion tree and creates the nodes in variant of 10.
* Checks for the random number to minimize the guesses for the average case.
*/
@Component
public class GuessNumberByFusion{

	/**
	* Max value form the application.properties
	*/
	@Value("${maxvalue}")
	private int maxValue;

	/**
	* Instance of the printer class
	*/
	@Autowired
	private Printer printer;

	/**
	* Instance of the FusionSearch class
	*/
	@Autowired
	private FusionSearch fusionSearch;

	/**
	* lowerbould value at any given point
	*/
	private int lowerbound=1;

	/**
	* lowerbould value at any given point
	*/
	@Value("${maxvalue}")
	private int higherbound;

	/**
	* Instance of random number at any given point.
	*/
	Random randomGenerator = new Random();

	/**
	* Used to check bound values
	*/
	private int la = 0;

	/**
	* Used to check bound values
	*/
	private int ha = 0;

	/**
	* Used to check the variant for the tree creation.
	*/
	private int variant = 0;
	
	/**
	* Used to check for the random number generation.
	*/
	private int lastvariant = 0;

	/**
	* Method to get the max value if required.
	*/
	public int getMaxValue(){
		return this.maxValue;
	}

	/**
	* Method to provide random number based on the higherbound and lowerbound
	*/
	public int random(){
		return randomGenerator.nextInt( this.higherbound-this.lowerbound)+this.lowerbound;
	}

	/**
	* This method validates if the lowerbound and higherbound are the same.
	* If not sets the higherbound thats received.
	* If the method is called for the first time, checks on the bound conditions
	* Else, calls the computePossibleValue for the next guess.
	*/
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

	/**
	* This method validates if the lowerbound and higherbound are the same.
	* If not sets the lowerbound thats received.
	* If the method is called for the first time, checks on the bound conditions
	* Else, calls the computePossibleValue for the next guess.
	*/
	public int higher(int lowerbound){
		if(validate(lowerbound,higherbound)){
			ha++;
			setLowerbound(lowerbound+1);
			if(ha==1){
				return this.random();
			}
			return computePossibleValue();

		}else if(ha>1){
			printer.printMessage(higherbound+" should be your value.");
		}else{
			printer.printMessage("Number could not be higher than, "+higherbound+" your entry should be wrong. Try again");
		}
		return higherbound;

	}

	/**
	* This method, provides the next possible guess.
	* When ever the variant changes, it provides a random guess to minimize the chances.
	* If not, provides a reasonable guess based on the tree node.
	* Uses fusion Tree to provide the guess.
	*/
	private int computePossibleValue(){
		variant = computevariant(this.lowerbound,this.higherbound);

		if(lastvariant>variant && validate(lowerbound,higherbound)){
			lastvariant=variant;
			return this.random();

		}
		
		lastvariant=variant;
		return fusionSearch.getRoot(lowerbound, higherbound, computeMultiplier(variant));
	}

	/**
	* Sets the higherbound.
	*/
	public void setHigherbound(int higherbound){
		this.higherbound = higherbound;
	}

	/**
	* Sets the lowerbound
	*/
	public void setLowerbound(int lowerbound){
		this.lowerbound = lowerbound;
	}

	/**
	* returns the higher bound.
	*/
	public int getHigherbound(){
		return this.higherbound;
	}

	/**
	* returns the lowerbound.
	*/
	public int getLowerbound(){
		return this.lowerbound;
	}

	/**
	* Flush the values if the user would like to try again.
	*/
	public void flush(){
		this.higherbound=this.maxValue;
		this.lowerbound=1;
		this.ha=0;
		this.la=0;
		this.variant=0;
		this.lastvariant=0;
	}

	/**
	* validate if lowerbound and higherbound are same.
	* if same returns false
	* if not same returns true.
	*/
	private boolean validate(int a,int b){
		if(a==b){
			return false;
		}else{
			return true;
		}
	}

	/**
	* Computes the variant.
	* ex. if the numbder is 900,
	* It checks the log10 of the value to get the number of digits in the number.
	* This would help in providing number of digits in the number. 
	*/
	private int computevariant(int lowerbound,int higherbound){
		int variant = 0;
		int n = 0;;
		n = higherbound-lowerbound;
		variant = (int)(Math.log10(n));
		return variant;
	}

	/**
	* Computes the multiplier. if the variant is 2, the multiplier is 100.
	*/
	private int computeMultiplier(int variant){
		return (int)(Math.pow(10, variant));
	}

}
