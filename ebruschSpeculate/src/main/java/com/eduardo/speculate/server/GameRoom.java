package com.eduardo.speculate.server;

import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.game.Player;

public class GameRoom {

	private Player playerOne = null;
	private Player playerTwo = null;
	int playerCount = 0;
	boolean isOngoingGame = false;


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

}
