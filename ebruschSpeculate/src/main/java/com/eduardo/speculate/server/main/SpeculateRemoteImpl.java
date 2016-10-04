package com.eduardo.speculate.server.main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SpeculateRemoteImpl  extends UnicastRemoteObject implements SpeculateRemote{

	private static final long serialVersionUID = 1234L;
	static private Integer nextPID = 1;

	protected SpeculateRemoteImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
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
