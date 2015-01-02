package com.karthik.grandmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.karthik.grandmaster.util.Printer;

/**
* Bootstrap class for the Number Guessing Game.
* Implements CommandLineRunner of SpringBoot to fasilitae Commandline Application.
*/
@SpringBootApplication
public class NumberGuessingGameApplication implements CommandLineRunner{

	@Autowired
	private NumberGuesser findNumber;
	
	@Autowired
	private Printer printer;
	
	public static void main(String[] args) {
		SpringApplication.run(NumberGuessingGameApplication.class, args);
		
	}
	
	/**
	* Method to start the game.
	*/
	@Override
	public void run(String... args) {
		printer.printBanner();
		findNumber.start();
	}
	
	/**
	* Optional Method to initialize Number Guesser Class
	*/
	@Bean
	public NumberGuesser getNumberGuess(){
		NumberGuesser findNumber =  new NumberGuesser();
		
		return findNumber;
	}
	
	/**
	* Optional method to initialize Custom Generic Commandline printer.
	*/
	@Bean Printer getPrinter(){
		return new Printer();
	}
}
