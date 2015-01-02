package com.karthik.grandmaster;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karthik.grandmaster.game.GuessNumberByFusion;
import com.karthik.grandmaster.util.Constants;
import com.karthik.grandmaster.util.Printer;

/**
* NumberGuesser Class interacts with the user to get inputs and provide the output
* Responsible for providing inputs for the Game itself
*/
@Component
public class NumberGuesser {

	/**
	* Instance of Printer
	*/
	@Autowired
	private Printer printer;

	/**
	* Instance of Guessing number by Fusion Search.
	*/
	@Autowired
	private GuessNumberByFusion binarySearch;

	/**
	* Instance of scanner to get inputs
	*/
	private Scanner scanner;

	public NumberGuesser(){
		scanner = new Scanner(System.in);
	}

	/**
	* Method to start the game.
	*/
	public void start(){
		printer.printGreeting();
		printer.printRules();
		this.ready();
	}

	/**
	* This method provides the rules of the game
	* Gets inputs from the user, if he/she would like to start the game
	* Starts the game based on the user input "ready"
	* Stops the game based on the user input "exit"
	*/
	public void ready(){
		String input;
		printer.printUser();
		while(scanner.hasNext()){
			input = scanner.next();
			if(input!=null && input.trim().length()>0){
				if(Constants.READY.equalsIgnoreCase(input)){
					this.guessByFusion();
					
					printer.printMessage(Constants.RETURN_MSG);
				}else if(Constants.EXIT.equalsIgnoreCase(input)){
					this.stop();
				}else{
					printer.printMessage(Constants.INITIAL_INPUT);
				}
			}else{
				printer.printMessage(Constants.INITIAL_INPUT);
			}
			printer.printUser();
		}

	}

	/**
	* Uses the instance of GuessNumberByFusion to guess the number
	* Checks the Bound value check for 0 and higher number
	* Gets input from the user "higher" or "lower" and prints the guess number
	*/
	public void guessByFusion(){
		String input;
		binarySearch.flush();
		int guess = binarySearch.getMaxValue();

		printer.printMessage("Is your number "+guess+"?");
		printer.printUser();

		while(scanner.hasNext()){
			input = scanner.next();
			if(input!=null && input.trim().length()>0){
				if(Constants.YES.equalsIgnoreCase(input)){
					printer.printMessage(Constants.YES);
					return;
				}else if(Constants.HIGHER.equalsIgnoreCase(input)){
					guess = binarySearch.higher(guess);
				}else if(Constants.LOWER.equalsIgnoreCase(input)){
					guess = binarySearch.lower(guess);
				}else if(Constants.EXIT.equalsIgnoreCase(input)){
					this.stop();
				}else{
					printer.printMessage(Constants.GUESS_RULE);
				}
			}else{
				printer.printMessage(Constants.GUESS_RULE);
			}
			
				printer.printMessage("Is your number "+guess+"?");
			
			printer.printUser();

		}
	}

	/**
	* Stops the game.
	*/
	public void stop(){
		printer.printMessage(Constants.EXIT_GREETING);
		System.exit(0);
	}

}
