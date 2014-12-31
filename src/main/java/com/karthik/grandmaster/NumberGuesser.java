package com.karthik.grandmaster;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karthik.grandmaster.game.GuessNumberByFusion;
import com.karthik.grandmaster.util.Constants;
import com.karthik.grandmaster.util.Printer;

@Component
public class NumberGuesser {

	@Autowired
	private Printer printer;

	@Autowired
	private GuessNumberByFusion binarySearch;

	private Scanner scanner;

	public NumberGuesser(){
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

	public void stop(){
		printer.printMessage(Constants.EXIT_GREETING);
		System.exit(0);
	}

}
