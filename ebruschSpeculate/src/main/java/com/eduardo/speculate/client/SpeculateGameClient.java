package com.eduardo.speculate.client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import com.eduardo.speculate.client.graphics.SpeculateInterface;
import com.eduardo.speculate.client.graphics.TextBasedInterface;
import com.eduardo.speculate.commons.Constants;
import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.server.GameState;
import com.eduardo.speculate.server.ServerProperties;
import com.eduardo.speculate.server.SpeculateRemote;


public class SpeculateGameClient {

	private int idClient = 0;
	private SpeculateRemote server;
	private final String bar = "/";
	private Scanner input;

	public SpeculateGameClient() {

		String serviceFullName = bar + bar + ServerProperties.SERVER_ADDRESS.getString() + bar + "SPECULATE";
		input  = new Scanner(System.in);


		try {

			server = (SpeculateRemote)Naming.lookup(serviceFullName);
			idClient = server.getPID();
			if(idClient == 0) {
				System.out.println("Server is full at the moment. Please try again later");
			}
			System.out.println("INFO: pid is "+idClient);


			int opt = 1;

			while ( opt != 0 ) {

				opt = beginClient();
			}

		}  catch (Exception e) {

			throw new RuntimeException(Strings.GENERAL_NETWORK_ERROR.get(), e);
		}


	}

	private int beginClient() throws RemoteException {
		SpeculateInterface screen = new TextBasedInterface();
		boolean inAGame = true;
		int stillInTheGame = 1;

		while(inAGame) {

			System.out.println("DEBUG: get current state");

			GameState currentGameState = server.getNextMove(idClient);

			if(currentGameState == null) {
				screen.drawWaitingMenu(currentGameState);

			} else {

				if(currentGameState.isWinner()) {
					screen.victoryScreen(currentGameState);
					stillInTheGame = 0;
					inAGame = false;

				} else {

					if(currentGameState.isLooser()) {
						screen.looseScreen(currentGameState);
						stillInTheGame = 0;
						inAGame = false;

					} else {

						if(currentGameState.isMyTime()) {
							screen.drawMakeYourMove(currentGameState);
							int plays = gameGetPlayerInput();
							screen.drawImMoving(currentGameState);
							currentGameState = server.makePlayerMove(idClient, plays);

						} else {
							screen.drawWaitingOpponent(currentGameState);
						}

					}
				}
			}

			waitTime();

		}



		return stillInTheGame;
	}

	private int gameGetPlayerInput() {
		return input.nextInt();
	}



	private void waitTime() {
		try {
			Thread.sleep(Integer.parseInt(Constants.CLIENT_WAIT_TIME.get()));
		} catch (InterruptedException e) {
			throw new RuntimeException(Strings.GENERAL_EXECUTION_ERROR.get(), e);

		}

	}




}





