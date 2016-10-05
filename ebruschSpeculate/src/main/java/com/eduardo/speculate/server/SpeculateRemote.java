package com.eduardo.speculate.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpeculateRemote extends Remote{

	public int getPID() throws RemoteException;

}
