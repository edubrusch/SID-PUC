package com.eduardo.speculate.commons;

public enum Constants {

	SERVER_PROPERTIES("game.properties"),
	GAME_VERSION("Speculate V. 1.0"),
	PLAYER_ONE("Player One"),
	PLAYER_TWO("Player Two"),
	SERVICE_NAME("SPECULATE");

	private final String string;

	Constants(String string) {
		this.string = string;
	}

	public String get() {
		return string;
	}

}
