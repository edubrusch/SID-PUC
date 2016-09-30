package com.eduardo.speculate.server.comm.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
	public int getPID() throws RemoteException;
}