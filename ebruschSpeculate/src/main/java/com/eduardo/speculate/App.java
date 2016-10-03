package com.eduardo.speculate;

import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.server.main.SpeculateGameServer;
import com.eduardo.speculate.client.main.SpeculateGameClient;

public class App {

	public static void main(String[] args) {
		final SpeculateGameClient client;
		final SpeculateGameServer server;

		if (args.length == 0 || args.length % 2 != 0 ) {

			System.out.println(Strings.PARAM_HELP.get());
			waitFor(1000);
			System.exit(-1);

		} else {
			if (args[0].equals("-m")) {

				if (args[1].equals("server")) {
					System.out.println(Strings.WELCOME_SERVER.get());
					server = new SpeculateGameServer();
					server.begin();
					System.out.println(Strings.GOODBYE_SERVER.get());
					System.exit(0);

				}else {

					if (args[1].equals("client")) {
						System.out.println(Strings.WELCOME_CLIENT.get());
						client = new SpeculateGameClient();
						client.begin();
						System.out.println(Strings.GOODBYE_CLIENT.get());
						System.exit(0);

					} else {
						System.out.println(Strings.PARAM_HELP.get());
						waitFor(1000);
						System.exit(-1);
					}
				}

			}
		}

	}

	private static void waitFor(long timeMilis) {
		try {
			Thread.sleep(timeMilis);
		} catch (InterruptedException e) {
			throw new RuntimeException(Strings.GENERAL_EXECUTION_ERROR.get(), e);
		}
	}

}

// "m", "mode", true, "Mode: server or client"
// http://stackoverflow.com/questions/31373445/java-jar-no-main-manifest-attribute
// Options inputArgs = new Options();
// Option runMode = new Option("m", "mode", true, "");
// runMode.setRequired(true);
// inputArgs.addOption(runMode);
//
// CommandLineParser parser = new BasicParser();
// HelpFormatter formatter = new HelpFormatter();
// CommandLine cmd = null;
//
// try {
// cmd = parser.parse(inputArgs, args);
// } catch (org.apache.commons.cli.ParseException e) {
// System.out.println(Strings.GAME_VERSION);
// formatter.printHelp("lala", inputArgs);
// e.printStackTrace();
// }
//
// String inputFilePath = cmd.getOptionValue("mode");
//
// System.out.println(inputFilePath);