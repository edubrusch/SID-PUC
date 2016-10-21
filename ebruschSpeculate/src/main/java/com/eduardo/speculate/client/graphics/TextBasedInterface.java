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
			"|_____________________________________________________________________|\n"
       ;

	public void drawGameState(GameState s) {
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

}
