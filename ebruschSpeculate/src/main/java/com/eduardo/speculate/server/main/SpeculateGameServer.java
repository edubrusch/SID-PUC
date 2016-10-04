package com.eduardo.speculate.server.main;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.eduardo.speculate.game.GameBoard;
import com.eduardo.speculate.game.SixFaceDice;

import java.rmi.Naming;

public class SpeculateGameServer{




	public SpeculateGameServer(){
		super();
		// http://www.javacamp.org/moreclasses/rmi/rmi4.html
	}


	private GameBoard board;
	private ArrayList<Player> playerList;
	private SixFaceDice dice;


	public void begin() {

		board = new GameBoard();
		playerList = new ArrayList<Player>(50);
		dice = new SixFaceDice();

		dice.equals(new SixFaceDice());
		playerList.add(new Player());
		board.equals(new GameBoard());

		try {

			java.rmi.registry.LocateRegistry.createRegistry(1099);
			Naming.rebind ("PID", new SpeculateRemoteImpl());
			System.out.println ("PidServer is ready.");
		} catch (Exception e) {
			System.out.println ("PidServer failed:");
		}

	}



}
