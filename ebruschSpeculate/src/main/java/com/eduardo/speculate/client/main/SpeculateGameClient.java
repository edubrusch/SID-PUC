package com.eduardo.speculate.client.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.eduardo.speculate.server.main.SpeculateRemote;

public class SpeculateGameClient {

	public void begin() {
		try {
			SpeculateRemote spr = (SpeculateRemote)Naming.lookup("//localhost/PID");
			System.out.println("PID="+spr.getPID());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

}
