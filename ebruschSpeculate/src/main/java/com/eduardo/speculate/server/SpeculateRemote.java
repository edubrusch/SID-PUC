package com.eduardo.speculate.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpeculateRemote extends Remote{

	public int getPID() throws RemoteException;

	public int getNewGame(int playerID) throws RemoteException;

	public Object getNextMove(int playerID)  throws RemoteException;

}
