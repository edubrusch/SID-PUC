package com.eduardo.speculate.commons;


public enum Strings {

	PARAM_HELP("You didn't choose a mode to run. \"-m\" and \"client\" for client, \"server\" for dedicated server mode."),
	WELCOME_CLIENT("Starting Speculate client."),
	WELCOME_SERVER("Starting Speculate server."),
	GOODBYE_CLIENT("Leaving Speculate client."),
	ACTIVE_SERVER("Leaving Speculate is running."),
	GENERAL_EXECUTION_ERROR("Error: Some external factor cased this program to  stop. I'll need exit."),
	GENERAL_IO_ERROR("ERROR: Failed to load game files. Please verify the integrty of all the game files."),
	GENERAL_NETWORK_ERROR("ERROR: Failed to load game due to problems with conectivity. Please check if you have Internet connection. Also, please mind of any software wich blocks tcp ports."),
	ROOM_ERROR_MORE_THAN_TWO_PLAYERS("Error: Server is trying to insert more than two players in a room"),
	PLAYER_WAITING_GAME("Waiting for a new player to join the game."),
	PLAYER_BEGIN_GAME("A new player has joined. begin game."),
	PLAYER_WAITING_ADVERSARY_MOVES("Waiting opponent to play..."),
	PLAYER_MAKE_MOVE("Choose how many times to roll the dice"),
	PLAYER_WIN("YOU WON!! Let's try our luck again?"),
	PLAYER_LOOSE("You lost. Continue?");

	private final String string;

	Strings(String string) {
		this.string = string;
	}

	public String get() {
		return string;
	}

}
