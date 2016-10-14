package com.eduardo.speculate.client.main;

import java.rmi.Naming;
import java.rmi.RemoteException;

import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.server.SpeculateRemote;


public class SpeculateGameClient {

	private int idClient = 0;
	private SpeculateRemote server;

	public SpeculateGameClient(int playerID) {

		try {
			server = (SpeculateRemote)Naming.lookup("//localhost/PID");
			idClient = server.getPID();
			beginClient();

		}  catch (Exception e) {

			throw new RuntimeException(Strings.GENERAL_NETWORK_ERROR.get(), e);
		}


	}

	private void beginClient() throws RemoteException {

		server.getNextMove(idClient);


	}

}



/**
 * public int getPID() throws RemoteException;

	public GameState getNextMove(int playerID)  throws RemoteException;

	public GameState makePlayerMove(int playerID , int numberOfThrows) throws RemoteException;
 *
*/