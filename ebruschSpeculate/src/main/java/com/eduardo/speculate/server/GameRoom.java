package com.eduardo.speculate.server;

import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.game.Player;

public class GameRoom {

	private Player playerOne = null;
	private Player playerTwo = null;
	private Player nextPlayer = null;
	private Player winner = null;
	private int playerCount = 0;
	private boolean isOngoingGame = false;



	public boolean full() {
		if (playerCount == 2) {
			return true;
		}
		return false;
	}

	public void addPlayer(Player player) {

		if (playerOne == null) {
			playerOne = player;
			playerCount++;
		} else {
			if (playerTwo == null) {
				playerTwo = player;
				nextPlayer = playerOne;
				playerCount++;
			} else {
				/*
				 * Code is not supposed to come to this point, since the user
				 * (server) should ask whether the room is full. ir hurts object
				 * orientation, but I think it's easier and the result will be
				 * the same anyway. Just in case, not to be caught off guard,
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

	public Player getAdversary(int playerID) {

		if (playerOne.getPlayerID() == playerID) {

			return playerTwo;
		} else {
			if (playerTwo.getPlayerID() == playerID) {

				return playerOne;
			} else {

				return null;
			}

		}

	}


	public void nextPlayer(int player) {
		nextPlayer = getPlayer(player);
	}


	public boolean isWinner(int somePlayer) {
		boolean winner = false;

		if(this.winner.getPlayerID() == somePlayer)
			winner = true;

		return winner;
	}


	public void markGameEnd(int playerID) {

		isOngoingGame = false;
		if (playerOne.getPlayerID() == playerID) {

			winner = playerOne;
		} else {

			winner = playerTwo;
		}
	}

	public boolean haveWin() {
		return !(winner==null);
	}

}
