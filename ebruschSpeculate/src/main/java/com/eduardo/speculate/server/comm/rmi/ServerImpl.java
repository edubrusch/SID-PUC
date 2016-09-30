package com.eduardo.speculate.server.comm.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

	private static final long serialVersionUID = 1234L;
	static private Integer nextPID = 1;

	protected ServerImpl() throws RemoteException {
	}


	public int getPID() throws RemoteException {
		int pid;

		System.out.println("PidServer> Entrada");
		pid = nextPID;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		++nextPID;
		System.out.println("PidServer> Saida");
		return pid;
	}

}