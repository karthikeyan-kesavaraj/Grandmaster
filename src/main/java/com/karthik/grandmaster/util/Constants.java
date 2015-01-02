package com.karthik.grandmaster.util;

/**
* This interface contains the constant String values of strings used in the game.
*/
public interface Constants {
	public static final String INITIAL_INPUT = "You should type, ready to start the game or exit to quit.";
	
	public static final String RULES = "\n** Here are the rules of the game. **\n"
			+ "You should guess a number between 0 and 1000\n"
			+ "I would guess the number by asking \"Is the number ?\"\n"
			+ "If the guess is correct, type in yes.\n"
			+ "If not you should give me clue by typing, higher or lower.\n"
			+ "At any point of time during game, type exit to exit the game.\n"
			+ "Now, once you are ready, type in ready to begin game.";
	
	public static final String MY_GREETING = "Hi, I am Grandmaster";
	
	public static final String MY_ID = "Grandmaster> ";
	
	public static final String YOUR_ID = "Me> ";
	
	public static final String BANNER = "\n\n"
			+ "\n****************************\n"
			+ "    Number Guessing Game"
			+ "\n****************************\n";
	
	public static final String READY = "ready";
	
	public static final String EXIT = "exit";
	
	public static final String HIGHER = "higher";
	
	public static final String LOWER = "lower";
	
	public static final String YES = "yes";
			
	public static final String GUESS_RULE = "You should type yes, "+HIGHER+" or "+LOWER;
	
	public static final String RETURN_MSG ="Type ready to play again or type exit to exit the game.";
	
	public static final String EXIT_GREETING = "Thank you for playing.";
}
