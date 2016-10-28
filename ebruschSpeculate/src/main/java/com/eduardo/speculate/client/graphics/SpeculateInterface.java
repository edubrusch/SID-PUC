package com.eduardo.speculate.client.graphics;

import com.eduardo.speculate.server.GameState;

public interface SpeculateInterface {	

	public void drawWaitingMenu(GameState currentGameState);

	public void victoryScreen(GameState currentGameState);

	public void looseScreen(GameState currentGameState);

	public void drawWaitingOpponent(GameState currentGameState);

	public void drawMakeYourMove(GameState currentGameState);

	public void drawImMoving(GameState currentGameState);	


}
