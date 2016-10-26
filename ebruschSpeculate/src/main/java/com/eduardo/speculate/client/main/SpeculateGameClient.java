package com.eduardo.speculate.client.main;

import java.rmi.Naming;
import java.rmi.RemoteException;

import com.eduardo.speculate.client.graphics.SpeculateInterface;
import com.eduardo.speculate.client.graphics.TextBasedInterface;
import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.server.GameState;
import com.eduardo.speculate.server.ServerProperties;
import com.eduardo.speculate.server.SpeculateRemote;


public class SpeculateGameClient {

	private int idClient = 0;
	private SpeculateRemote server;
	private final String bar = "/";

	public SpeculateGameClient() {

		String serviceFullName = bar + bar + ServerProperties.SERVER_ADDRESS.getString() + bar + "SPECULATE";

		try {
			server = (SpeculateRemote)Naming.lookup(serviceFullName);
			idClient = server.getPID();
			if(idClient == 0) {
				System.out.println("Server is full at the moment. Please try again later");
			}
			System.out.println("INFO: pid is"+idClient);
			beginClient();

		}  catch (Exception e) {

			throw new RuntimeException(Strings.GENERAL_NETWORK_ERROR.get(), e);
		}


	}

	private void beginClient() throws RemoteException {
		SpeculateInterface screen = new TextBasedInterface();
		boolean inAGame = true;

		while(inAGame) {
			GameState currentGameState = server.getNextMove(idClient);
			if(currentGameState == null) {

			}
			screen.drawGameState(currentGameState);

		}







	}

}



/**
 *
 *  public int getPID() throws RemoteException;
 *
 * 	public GameState getNextMove(int playerID)  throws RemoteException;
 *
 * 	public GameState makePlayerMove(int playerID , int numberOfThrows) throws RemoteException;
 *
*/