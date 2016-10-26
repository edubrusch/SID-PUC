package com.eduardo.speculate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.eduardo.speculate.client.main.SpeculateGameClient;
import com.eduardo.speculate.commons.Constants;
import com.eduardo.speculate.commons.Strings;
import com.eduardo.speculate.server.SpeculateGameServer;
import com.eduardo.speculate.server.SpeculateRemote;

public class App {

	public static void main(String[] args) {
		final SpeculateGameClient client;

		if (args.length == 0 || args.length % 2 != 0 ) {

			System.out.println(Strings.PARAM_HELP.get());
			waitFor(1000);
			System.exit(-1);

		} else {
			if (args[0].equals("-m")) {

				if (args[1].equals("server")) {
					System.out.println(Strings.WELCOME_SERVER.get());
					try {
						SpeculateGameServer remote = new SpeculateGameServer();
						SpeculateRemote stub = (SpeculateRemote) UnicastRemoteObject.exportObject(remote, 0);
						java.rmi.registry.LocateRegistry.createRegistry(1099);
						Naming.rebind (Constants.SERVICE_NAME.get(), stub);
					} catch (MalformedURLException e) {
						System.out.println(Strings.GENERAL_NETWORK_ERROR.get());
						e.printStackTrace();
					} catch (RemoteException e) {
						System.out.println(Strings.GENERAL_NETWORK_ERROR.get());
						e.printStackTrace();
					} catch (NumberFormatException e) {
						System.out.println(Strings.GENERAL_IO_ERROR.get());
						e.printStackTrace();
					}

					System.out.println(Strings.ACTIVE_SERVER.get());

				}else {

					if (args[1].equals("client")) {
						client = new SpeculateGameClient();

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
			throw new RuntimeException(Strings.GENERAL_EXECUTION_ERROR.get(), e);
		}
	}

}


// http://docs.oracle.com/javase/7/docs/technotes/guides/rmi/hello/hello-world.html

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