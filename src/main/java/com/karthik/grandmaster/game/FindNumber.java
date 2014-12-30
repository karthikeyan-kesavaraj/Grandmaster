package com.karthik.grandmaster.game;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karthik.grandmaster.util.Constants;
import com.karthik.grandmaster.util.Printer;

@Component
public class FindNumber {

	@Autowired
	private Printer printer;

	@Autowired
	private BinarySearch binarySearch;

	private Scanner scanner;

	public FindNumber(){
		scanner = new Scanner(System.in);
	}

	public void start(){
		printer.printGreeting();
		printer.printRules();


		this.ready();
	}

	public void ready(){
		String input;
		printer.printUser();
		while(scanner.hasNext()){
			input = scanner.next();
			switch(input.toLowerCase()){
			case Constants.READY:
				this.guessByFusion();
				printer.printMessage(Constants.RETURN_MSG);
				break;
			case Constants.EXIT:
				this.stop();
				break;
			default:
				printer.printMessage(Constants.INITIAL_INPUT);
				break;
			}
			printer.printUser();
		}

	}


	public void guessByFusion(){
		String input;

		int guess = binarySearch.getMaxValue();
		int previousGuess = guess;
		printer.printMessage("Is your number "+guess+"?");
		printer.printUser();

		while(scanner.hasNext()){
			input = scanner.next();
			switch(input.toLowerCase()){
			case Constants.YES:
				printer.printMessage(Constants.YES);
				return;
			case Constants.HIGHER:
				guess = binarySearch.higher(guess);
				break;
			case Constants.LOWER:
				guess = binarySearch.lower(guess);
				break;
			case Constants.EXIT:
				this.stop();
			default:
				printer.printMessage(Constants.GUESS_RULE);
				break;
			}
			
			if(previousGuess!=guess){
				printer.printMessage("Is your number "+guess+"?");
				previousGuess = guess;
			}
			printer.printUser();

		}
	}

	public void stop(){
		printer.printMessage(Constants.EXIT_GREETING);
		System.exit(0);
	}

}
