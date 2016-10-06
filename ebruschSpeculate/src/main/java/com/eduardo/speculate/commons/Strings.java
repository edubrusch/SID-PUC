package com.eduardo.speculate.commons;


public enum Strings {

	PARAM_HELP("You didn't choose a mode to run. \"-m\" and \"client\" for client, \"server\" for dedicated server mode."),
	WELCOME_CLIENT("Starting Speculate client."),
	WELCOME_SERVER("Starting Speculate server."),
	GOODBYE_CLIENT("Leaving Speculate client."),
	ACTIVE_SERVER("Leaving Speculate is running."),
	GENERAL_EXECUTION_ERROR("Error: Some external factor cased this program to  stop. I'll need exit."),
	GENERAL_IO_ERROR("ERROR: Failed to load game files. Please verify the integrty of all the game files."),
	GENERAL_NETWORK_ERROR("ERROR: Failed to load game due to problems with conectivity. Please check if you have Internet connection. Also, please mind of any software wich blocks tcp ports.");

	private final String string;

	Strings(String string) {
		this.string = string;
	}

	public String get() {
		return string;
	}

}
