package com.eduardo.speculate.server;

import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.game.Player;

public class GameRoom {

	private Player playerOne = null;
	private Player playerTwo = null;
	private Player nextPlayer = null;
	private int playerCount = 0;
	private boolean isOngoingGame = false;
	private int playerOneNumberOfThrows = 0;
	private int playerTwoNumberOfThrows = 0;


	public boolean full() {
		if (playerCount == 2) {
			return true;
		}
		return false;
	}

	public void addPlayer(Player player) {

		if (playerOne == null) {
			playerOne = player;
			nextPlayer = playerOne;
			playerCount++;
		} else {
			if (playerTwo == null) {
				playerOne = player;
				playerCount++;
			} else {
				/*
				 * Code is not supposed to come to this point, since the user
				 * (server) should ask whether the room is full. ir hurts object
				 * orientation, but I think it's easier and the result will be
				 * the same anyway. Just in case, not to be caught offguard,
				 * I'll put here an exception, so I know it happened somewhere
				 * in time/space.
				 */
				throw new RuntimeException(
						Strings.GENERAL_EXECUTION_ERROR.get(),
						new IllegalStateException(
								Strings.ROOM_ERROR_MORE_THAN_TWO_PLAYERS.get()));
			}
		}
	}

	protected Player getPlayer(int ID) {

		if (playerOne.getPlayerID() == ID) {

			return playerOne;
		} else {
			if (playerTwo.getPlayerID() == ID) {

				return playerTwo;
			} else {

				return null;
			}

		}

	}
	
	public boolean isOngoingGame() {
		return isOngoingGame;
	}
	
	
	public void markGameStart() {
		isOngoingGame = true;
	}

	public boolean isNext(int playerID) {
		if(nextPlayer.getPlayerID() == playerID)
			return true;
		return false;
	}
	
	public void updateNumberOfThrowsPlus(int player, int number) {
		if(playerOne.getPlayerID() == player) {
			playerOneNumberOfThrows = number; 
		}
		playerTwoNumberOfThrows = number;
	}
	
	
	public void updateNumberOfThrowsMinus(int player) {
		Player p = getPlayerById(player);
		p.reduceBallcount();
	}

	private Player getPlayerById(int playerID) {
		if(playerOne.getPlayerID() == playerID) {
			return playerOne; 
		}
		return playerTwo;
	}

	

}
