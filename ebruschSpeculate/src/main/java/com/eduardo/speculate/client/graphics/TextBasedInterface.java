package com.eduardo.speculate.client.graphics;

import com.eduardo.speculate.game.GameBoard;
import com.eduardo.speculate.server.GameState;

public class TextBasedInterface implements SpeculateInterface {

	private final String filledHole = "O";
	private final String emptyHole = "_";
	private String board =
			" _____________________________________________________________________\n"+
			"|                                                                     |\n"+
			"|         [%b1%]         [%b2%]         [%b3%]         [%b4%]         [%b5%]         |\n"+
			"|                                                         | |         |\n"+
			"|                                                         | |         |\n"+
			"|_________________________________________________________| |_________|\n"+
			"|                                               |                   | |\n"+
			"|        Balls left                             |      %b6%          | |\n"+
			"|        You:  %ya%                                  |                   | |\n"+
			"|        Opponent: %op%                         |___________________| |\n"+
			"|_____________________________________________________________________|\n"+
			" _____________________________________________________________________\n"+
			"|                                                                     |\n"+
			"| %opt1% |\n"+
			"| %opt2% |\n"+
			"| %opt3% |\n"+
			"|_____________________________________________________________________|\n"
       ;




	private void drawScreen(GameState s, String phrase1, String phrase2) {
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
				replace("%ya%", convertIS(s.getPlayerRemainingBalls())).
				replace("%op%", convertIS(s.getAdversaryRemainingBalls()));

		System.out.println(image);

	}

	private String convertIS(int number) {
		return Integer.toString(number);
	}


//	public void drawWaitingMenu() {
//		System.out.println("waiting second player to join");
////		drawScreen(currentGameState, String phrase1, String phrase2);
//	}



	public void drawWaitingMenu(GameState currentGameState) {
		System.out.println("Server is full at the moment. do you with to ty again?");
//		drawScreen(currentGameState, String phrase1, String phrase2);

	}

	public void victoryScreen(GameState currentGameState) {
		System.out.println("You won! play again?");
//		drawScreen(currentGameState, String phrase1, String phrase2);

	}

	public void looseScreen(GameState currentGameState) {
		System.out.println("you loose. Try again?");
//		drawScreen(currentGameState, String phrase1, String phrase2);

	}

	public void drawWaitingOpponent(GameState currentGameState) {
		System.out.println("waiting second player to join");
//		drawScreen(currentGameState, String phrase1, String phrase2);

	}

	public void drawMakeYourMove(GameState currentGameState) {
		System.out.println("how many rolls will your dice roll?");
		// TODO Auto-generated method stub
//		drawScreen(currentGameState, String phrase1, String phrase2);

	}

	public void drawImMoving(GameState currentGameState) {
		System.out.println("throwing dice, wait");
		// TODO Auto-generated method stub
//		drawScreen(currentGameState, String phrase1, String phrase2);

	}

}
