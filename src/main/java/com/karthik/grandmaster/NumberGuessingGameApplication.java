package com.karthik.grandmaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.karthik.grandmaster.util.Printer;

@SpringBootApplication
public class NumberGuessingGameApplication implements CommandLineRunner{

	@Autowired
	private NumberGuesser findNumber;
	
	@Autowired
	private Printer printer;
	
	public static void main(String[] args) {
		SpringApplication.run(NumberGuessingGameApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) {
		printer.printBanner();
		findNumber.start();
	}
	
	@Bean
	public NumberGuesser getNumberGuess(){
		NumberGuesser findNumber =  new NumberGuesser();
		
		return findNumber;
	}
	
	@Bean Printer getPrinter(){
		return new Printer();
	}
}
