package com.eduardo.speculate.client.main;

import java.rmi.Naming;
import java.rmi.RemoteException;

import com.eduardo.speculate.client.graphics.SpeculateInterface;
import com.eduardo.speculate.client.graphics.TextBasedInterface;
import com.eduardo.speculate.commons.Constants;
import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.server.ServerProperties;
import com.eduardo.speculate.server.SpeculateRemote;


public class SpeculateGameClient {

	private int idClient = 0;
	private SpeculateRemote server;
	private final String bar = "/";

	public SpeculateGameClient(int playerID) {

		String serviceFullName = bar + bar + ServerProperties.SERVER_ADDRESS.getString() + bar + Constants.SERVICE_NAME;

		try {
			server = (SpeculateRemote)Naming.lookup(serviceFullName);
			idClient = server.getPID();
			beginClient();

		}  catch (Exception e) {

			throw new RuntimeException(Strings.GENERAL_NETWORK_ERROR.get(), e);
		}


	}

	private void beginClient() throws RemoteException {

		SpeculateInterface screen = new TextBasedInterface();

		server.getNextMove(idClient);


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