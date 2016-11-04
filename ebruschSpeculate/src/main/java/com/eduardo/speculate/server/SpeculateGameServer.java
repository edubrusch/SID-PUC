package com.eduardo.speculate.server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.game.GameBoard;
import com.eduardo.speculate.game.Player;
import com.eduardo.speculate.game.SixFaceDice;

public class SpeculateGameServer implements SpeculateRemote {

	static private Integer nextPID = 1;
	private int serverLimit;
	private final GameBoard board;
	private final ArrayList<GameRoom> playerLobby;	

	public SpeculateGameServer() throws RemoteException, NumberFormatException {
		
		serverLimit = ServerProperties.MAX_MATCH_COUNT.getInt();
		board = new GameBoard();				
		playerLobby = new ArrayList <GameRoom> ();		
		
		board.equals(new GameBoard());

	}

	/**
	 * Server Interface Methods available for the client to call
	 */

	public int getPID() throws RemoteException {

		int pid;
		pid = nextPID;
		boolean addPlayer = insertPlayer(pid);



//		if client receives 0, it must say it servers were full.
		if (!addPlayer) {

			pid = 0;

		} else {

			++nextPID;

		}


		System.out.println("DEBUG: received new pid order. delivered => "+pid+".");
		return pid;
	}


	// logic that determines whether it's the current player time based on their
	// ID
	// also controls the game state
	public GameState getNextMove(int playerID) throws RemoteException {


		System.out.println("DEBUG: client "+playerID+" required move.");


		// if the room is null it's because the game doesn't exists
		if (!getGameRoom(playerID).full()) {

			System.out.println("DEBUG: client "+playerID+" got null gameroom.");
			return null;

		}

		// validade for game current state
		if (!getGameRoom(playerID).isOngoingGame()) {

			if(getGameRoom(playerID).haveWin() ) {
				GameState gameover = new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID),
						getAdversaryRemainingBalls(playerID), getPlayerRemainingBalls(playerID), getGameRoom(playerID).isNext(playerID));
				if(getGameRoom(playerID).isWinner(playerID)) {
					gameover.declareWinner();
					// should not fall in here since I have already told the player he won
				} else {
					gameover.declareLooser();
				}

				return gameover;
			}

			System.out.println("DEBUG: server will start new game");

			getGameRoom(playerID).markGameStart();
		}

		System.out.println("DEBUG: return game state");
		System.out.println("DEBUG: is player "+playerID+ "time? "+getGameRoom(playerID).isNext(playerID)+".");


		return new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID),
				getAdversaryRemainingBalls(playerID), getPlayerRemainingBalls(playerID), getGameRoom(playerID).isNext(playerID));
	}


	public GameState makePlayerMove(int playerID, int numberOfThrows) throws RemoteException {


		System.out.println("DEBUG: player "+playerID+" asked to make a move.");
		SixFaceDice dice = new SixFaceDice();


		if (numberOfThrows > getGameRoom(playerID).getPlayer(playerID).getballCount() || numberOfThrows <= 0  || getGameRoom(playerID).getPlayer(playerID) == null)
			throw new RuntimeException(Strings.GENERAL_EXECUTION_ERROR.get(), new IllegalArgumentException());


		// dice roll must be a 1 to 6 number, should be defined in the dice.
		for (int i = numberOfThrows; i > 0; i--) {
			System.out.println("DEBUG: throwing "+i);
			updateGameBoardsetNumber(dice.rollDice(), playerID);
//			commented part above for testing purposes only
//			decreaseBallPlayer(playerID);
			System.out.println("player ball: "+getGameRoom(playerID).getPlayer(playerID).getballCount());
			waitTime(2000);
		}

//TODO create gamestate after deciding if there was a winner. is winner return ismytime = true, so that the other player do not want to play
		GameState output = new GameState(getCurrentBoard(), getAdversaryRemainingMoves(playerID), getAdversaryRemainingBalls(playerID), getPlayerRemainingBalls(playerID), false);

		updateNextPlayer(getGameRoom(playerID).getAdversary(playerID).getPlayerID());

		if (getGameRoom(playerID).getPlayer(playerID).getballCount() == 0) {

			output.declareWinner();
			getGameRoom(playerID).markGameEnd(playerID);
		}

		return output;
	}


	private void waitTime(int i) {
		try {
			Thread.sleep(i);

		} catch (InterruptedException e) {
			throw new RuntimeException(Strings.GENERAL_EXECUTION_ERROR.get(), e);
		}
	}

	/**
	 * Server utilities Private Methods to handle game objects according to the
	 * server needs
	 */

	private synchronized void updateNextPlayer(int playerID) {

		getGameRoom(playerID).nextPlayer(playerID);
	}


	private synchronized void updateGameBoardsetNumber(int i, int playerID) {

		System.out.println("DEBUG: game board: dice result is" + i);

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
		
		for (int i = 0; i <= playerLobby.size(); i++) {
			if (playerLobby.get(i).getPlayer(playerID) != null) {
				return playerLobby.get(i);				
			}
		}
		return null;
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

		// TODO refactor: treat list empty, consider it's going to the size. if
		// it went to the end of the for without finding and theres room then I
		// add another gameroon. if it's full inser is false

		
		
		if (playerLobby.isEmpty()) {

			insertPoint = new GameRoom();
			playerLobby.add(insertPoint);
			found = true;
		} else {

			for (int i = 0; i <= playerLobby.size(); i++) {
				
				if (i == playerLobby.size()) {
					
					if(i==serverLimit) {
						return false;
					} else {
						insertPoint = new GameRoom();
						playerLobby.add(insertPoint);
						found = true;
						break;
					}
				}
				
				if (!playerLobby.get(i).full()) {

					insertPoint = playerLobby.get(i);
					found = true;
					break;
				}				
			}
		}

		if (found) {
			insertPoint.addPlayer(new Player(playerID));
		}

		return found;
	}

	// http://stackoverflow.com/questions/33476910/eclipse-mars-consistently-fails-resolving-imports-after-saving-but-cleaning-pro

}
