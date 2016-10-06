package com.eduardo.speculate.server;

import com.eduardo.speculate.game.Player;

public class GameRoom {

	private Player playerOne;
	private Player playerTwo;

	public GameRoom(Player first){

	}

	public boolean full() {
		int playerCount = 0;


		if(playerCount ==2){
			return true;
		}
		return false;
	}

	public void addPlayer(Player player) {


	}

}
