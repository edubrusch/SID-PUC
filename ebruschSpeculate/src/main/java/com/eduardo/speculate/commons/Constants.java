package com.eduardo.speculate.commons;

public enum Constants {

	SERVER_PROPERTIES("game.properties"),
	GAME_VERSION("Speculate V. 1.0");

	private final String string;

	Constants(String string) {
		this.string = string;
	}

	public String get() {
		return string;
	}

}
