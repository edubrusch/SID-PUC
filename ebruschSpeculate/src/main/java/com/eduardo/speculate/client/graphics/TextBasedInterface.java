package com.eduardo.speculate.client.graphics;

import com.eduardo.speculate.game.GameBoard;
import com.eduardo.speculate.server.GameState;

public class TextBasedInterface implements SpeculateInterface {

	private final int MAX_OPTION_SIZE = 66;
	private final String filledHole = "O";
	private final String emptyHole = "_";
	private final String indiscreteClean = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";


	private String board =
			" _____________________________________________________________________\n"+
			"|                                                                     |\n"+
			"|         [%b1%]      [%b2%]      [%b3%]      [%b4%]      [%b5%]             | |     |\n"+
			"|                                                             | |     |\n"+
			"|          1        2        3        4        5              | |     |\n"+
			"|_____________________________________________________________| |_____|\n"+
			"|                                               |                   | |\n"+
			"|        Balls left                             |     %b6%             | |\n"+
			"|        You:  %ya%                                |                   | |\n"+
			"|        Opponent: %op%                            |___________________| |\n"+
			"|_____________________________________________________________________|\n"+
			" _____________________________________________________________________\n"+
			"|                                                                     |\n"+
			"| %opt1%   |\n"+
			"| %opt2%   |\n"+
			"| %opt3%   |\n"+
			"|_____________________________________________________________________|\n"
       ;




	private void drawScreen(GameState s, String phrase1, String phrase2, String phrase3) {
		GameBoard b = s.getCurrentBoard();
		String one = emptyHole,
				two = emptyHole,
				three = emptyHole,
				four = emptyHole,
				five = emptyHole,
				six = null;

		if(b.oneHasBall())
			one = filledHole;

		if(b.twoHasBall())
			two = filledHole;

		if(b.threeHasBall())
			three = filledHole;

		if(b.fourHasBall())
			four = filledHole;

		if(b.fiveHasBall())
			five = filledHole;

		six = Integer.toString(b.getBallsInSix());

		String image = board.replace("%b1%", one).
				replace("%b2%", two).
				replace("%b3%", three).
				replace("%b4%", four).
				replace("%b5%", five).
				replace("%b6%", six).
				replace("%opt1%", align(phrase1)).
				replace("%opt2%", align(phrase2)).
				replace("%opt3%", align(phrase3)).
				replace("%ya%", convertIS(s.getPlayerRemainingBalls())).
				replace("%op%", convertIS(s.getAdversaryRemainingBalls()));

		System.out.println(indiscreteClean);
		System.out.println(image);

	}

	private String convertIS(int number) {
		return Integer.toString(number);
	}

	private String align(String phrase) {
		int remaining  = MAX_OPTION_SIZE - phrase.length();
		StringBuilder sb = new StringBuilder(phrase);
		for (int i = 0; i < remaining-1; i++ ) {
			sb.append(" ");
		}


		return sb.toString();

	}


	public void drawWaitingMenu(GameState currentGameState) {
		String phrase = "Waiting opponent to join";
		drawScreen(new GameState(new GameBoard(), 0, 0, 0, false), phrase, "      ", "      ");

	}

	public void victoryScreen(GameState currentGameState) {
		String phrase = "You won! play again?";
		String phrase2 = "leaving the game in 5 seconds.";		
		drawScreen(currentGameState, phrase, phrase2, "      ");

	}

	public void looseScreen(GameState currentGameState) {
		String phrase = "You loose. Try again?";
		String phrase2 = "leaving the game in 5 seconds.";		
		drawScreen(currentGameState, phrase, phrase2, "      ");

	}

	public void drawWaitingOpponent(GameState currentGameState) {
		String phrase = "waiting other player to play";
		drawScreen(currentGameState, phrase, "      ", "      ");

	}

	public void drawMakeYourMove(GameState currentGameState) {
		String phrase = "how many rolls will your dice roll?";
		drawScreen(currentGameState, phrase, "      ", "      ");

	}

	public void drawImMoving(GameState currentGameState) {
		String phrase = "throwing dice, wait";
		drawScreen(currentGameState, phrase, "      ", "      ");

	}

}
