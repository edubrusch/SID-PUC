package com.eduardo.speculate.commons;

public enum Strings {

	PARAM_HELP("You didn't choose a mode to run. \"-m\" and \"client\" for client, \"server\" for dedicated server mode."),
	WELCOME_CLIENT("Starting Speculate client."),
	WELCOME_SERVER("Starting Speculate server."),
	GENERAL_IO_ERROR("ERROR: Failed to load game files. Please verify the integrty of all the game files.");

	private final String string;

	Strings(String string) {
		this.string = string;
	}

	public String get() {
		return string;
	}

}
