package com.eduardo.speculate.server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.eduardo.speculate.game.GameBoard;
import com.eduardo.speculate.game.SixFaceDice;

public class SpeculateGameServer implements SpeculateRemote{


	static private Integer nextPID = 1;
	private GameBoard board;
	private ArrayList<Player> playerList;
	private SixFaceDice dice;

	public SpeculateGameServer() throws RemoteException {		
		
		board = new GameBoard();
		playerList = new ArrayList<Player>(50);
		dice = new SixFaceDice();

		dice.equals(new SixFaceDice());
		playerList.add(new Player());
		board.equals(new GameBoard());
	}

	
	
	public int getPID() throws RemoteException {
		int pid;

		pid = nextPID;
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		++nextPID;
		System.out.println("PidServer> Saida");
		return pid;
	}


	


}
