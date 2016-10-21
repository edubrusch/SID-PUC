package com.eduardo.speculate.server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.game.GameBoard;
import com.eduardo.speculate.game.Player;
import com.eduardo.speculate.game.SixFaceDice;

public class SpeculateGameServer implements SpeculateRemote {

	static private Integer nextPID = 1;
	private final GameBoard board;
	private final ArrayList<GameRoom> playerLobby;
	private final SixFaceDice dice;

	public SpeculateGameServer() throws RemoteException, NumberFormatException {

		board = new GameBoard();
		playerLobby = new ArrayList<GameRoom>(
				ServerProperties.MAX_MATCH_COUNT.getInt() * 2);
		dice = new SixFaceDice();

		dice.equals(new SixFaceDice());
		board.equals(new GameBoard());
		
	}

	/**
	 * Server Interface Methods available for the client to call
	 */

	public int getPID() throws RemoteException {

		int pid;
		pid = nextPID;
		boolean addPlayer = insertPlayer(pid);

		if (!addPlayer) {
			pid = 0;
			/*
			 * if client receives 0, it must say it servers were full.
			 */
		} else {
			++nextPID;
		}
		return pid;
	}

	// logic that determines whether it's the current player time based on their
	// ID
	// also controls the game state
	public GameState getNextMove(int playerID) throws RemoteException {
		
		//se a sala for null e pq o jogo nao existe

		if (!getGameRoom(playerID).full()) {
			// game didn't began yet
			return null;
		} else {
			// validade for game current state
			if (!getGameRoom(playerID).isOngoingGame()) {
				if (getGameRoom(playerID).isNext(playerID)) {
					return new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID), getAdversaryRemainingBalls(playerID), getPlayerRemainingBalls(playerID), true);
				} else {
					return new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID), getAdversaryRemainingBalls(playerID), getPlayerRemainingBalls(playerID), false);
				}
			}
		}

		return new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID), getAdversaryRemainingBalls(playerID), getPlayerRemainingBalls(playerID), getGameRoom(playerID).isNext(playerID));
	}

	public GameState makePlayerMove(int playerID, int numberOfThrows) throws RemoteException {
		SixFaceDice dice = new SixFaceDice();

		if (numberOfThrows > getGameRoom(playerID).getPlayer(playerID).getballCount() || numberOfThrows <= 0  || getGameRoom(playerID).getPlayer(playerID) == null)
			throw new RuntimeException(Strings.GENERAL_EXECUTION_ERROR.get(), new IllegalArgumentException());

		for (int i = numberOfThrows; i > 0; i--) {

			// dice roll must be a 1 to 6 number, should be defined in the dice.
			updateGameBoardsetNumber(dice.rollDice(), playerID);
//			waitTime(1000);
		}



		GameState output = new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID), getAdversaryRemainingBalls(playerID), getPlayerRemainingBalls(playerID), false);

		updateNextPlayer(getGameRoom(playerID).getAdversary(playerID).getPlayerID());

		if (getGameRoom(playerID).getPlayer(playerID).getballCount() == 0) {

			output.declareWinner();
		}

		return output;
	}


	/**
	 * Server utilities Private Methods to handle game objects according to the
	 * server needs
	 */


//	private void waitTime(long time) {
//		try{
//			Thread.sleep(time);
//
//		} catch (InterruptedException e) {
//			throw new RuntimeException(Strings.GENERAL_EXECUTION_ERROR.get(), e);
//		}
//	}

	private synchronized void updateNextPlayer(int playerID) {

		getGameRoom(playerID).nextPlayer(playerID);
	}


	private synchronized void updateGameBoardsetNumber(int i, int playerID) {

		switch (i) {
		case 1:
			if (getCurrentBoard().oneHasBall()) {

				getCurrentBoard().setOne(false);
				increaseBallPlayer(playerID);
			} else {

				getCurrentBoard().setOne(true);
				decreaseBallPlayer(playerID);
			}
			break;

		case 2:
			if (getCurrentBoard().twoHasBall()) {

				getCurrentBoard().setTwo(false);
				increaseBallPlayer(playerID);
			} else {

				getCurrentBoard().setTwo(true);
				decreaseBallPlayer(playerID);
			}
			break;

		case 3:
			if (getCurrentBoard().threeHasBall()) {

				getCurrentBoard().setThree(false);
				increaseBallPlayer(playerID);
			} else {

				getCurrentBoard().setThree(true);
				decreaseBallPlayer(playerID);
			}
			break;

		case 4:
			if (getCurrentBoard().fourHasBall()) {

				getCurrentBoard().setFour(false);
				increaseBallPlayer(playerID);
			} else {

				getCurrentBoard().setFour(true);
				decreaseBallPlayer(playerID);
			}
			break;

		case 5:
			if (getCurrentBoard().fiveHasBall()) {

				getCurrentBoard().setFive(false);
				increaseBallPlayer(playerID);
			} else {

				getCurrentBoard().setFive(true);
				decreaseBallPlayer(playerID);
			}
			break;

		case 6:
			getCurrentBoard().addSix();
			decreaseBallPlayer(playerID);
			break;
		}
	}

	private synchronized void increaseBallPlayer(int playerID) {
		getGameRoom(playerID).getPlayer(playerID).increaseBallcount();

	}

	private synchronized void decreaseBallPlayer(int playerID) {
		getGameRoom(playerID).getPlayer(playerID).decreaseBallcount();
	}

	private synchronized GameRoom getGameRoom(int playerID) {

		GameRoom foundRoom = null;
		for (int i = 0; i < playerLobby.size(); i++) {
			if (playerLobby.get(i).getPlayer(playerID) != null) {
				foundRoom = playerLobby.get(i);
			}

		}
		return foundRoom;
	}

	private synchronized GameBoard getCurrentBoard() {

		return board;
	}

	private synchronized int getAdversaryRemainingMoves(int playerID) {
		return getGameRoom(playerID).getAdversary(playerID).getRemainingRolls();
	}

	private synchronized int getAdversaryRemainingBalls(int playerID) {
		return getGameRoom(playerID).getAdversary(playerID).getballCount();
	}

	private synchronized int getPlayerRemainingBalls(int playerID) {
		return getGameRoom(playerID).getPlayer(playerID).getballCount();
	}



	private synchronized boolean insertPlayer(int playerID) {

		GameRoom insertPoint = null;
		boolean found = false;
		
		//TODO treat case in which player lobby is empty

		for (int i = 0; i <= playerLobby.size(); i++) {
			if (playerLobby.get(i) == null) {
				insertPoint = new GameRoom();
				playerLobby.add(insertPoint);
				found = true;
				break;
			}
			if (!playerLobby.get(i).full()) {
				insertPoint = playerLobby.get(i);
				found = true;
				break;
			}
		}

		if (found) {
			insertPoint.addPlayer(new Player(playerID));
		}

		return found;
	}

	// http://stackoverflow.com/questions/33476910/eclipse-mars-consistently-fails-resolving-imports-after-saving-but-cleaning-pro

}
