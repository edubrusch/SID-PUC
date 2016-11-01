package com.eduardo.speculate.commons;

public enum Constants {

	//SERVER_PROPERTIES("H:/homewin/game.properties"),
	SERVER_PROPERTIES("C:/game.properties"),
	GAME_VERSION("Speculate V. 1.0"),
	PLAYER_ONE("Player One"),
	PLAYER_TWO("Player Two"),
	SERVICE_NAME("SPECULATE"),
	CLIENT_WAIT_TIME("5000");

	private final String string;

	Constants(String string) {
		this.string = string;
	}

	public String get() {
		return string;
	}

}
