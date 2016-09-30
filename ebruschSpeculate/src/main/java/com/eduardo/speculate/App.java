package com.eduardo.speculate;

import com.eduardo.speculate.commons.Strings;

public class App {

	public static void main(String[] args) {
		System.out.println("Hello World!");

		if (args.length == 0 || args.length % 2 != 0 ) {

			System.out.println(Strings.PARAM_HELP.get());
			waitFor(1000);
			System.exit(-1);

		} else {
			if (args[0].equals("-m")) {
				if (args[1].equals("server")) {
					System.out.println(Strings.WELCOME_SERVER.get());

				}else {
					if (args[1].equals("client")) {
						System.out.println(Strings.WELCOME_CLIENT.get());

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
			throw new RuntimeException(
					"Error: Something caused my thread to  stop. I'll need exit.",
					e);
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