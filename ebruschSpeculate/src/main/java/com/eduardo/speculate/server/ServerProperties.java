package com.eduardo.speculate.server;

import java.io.FileInputStream;
import java.util.Properties;

import com.eduardo.speculate.commons.Constants;
import com.eduardo.speculate.commons.Strings;

public enum ServerProperties {

	MAX_MATCH_COUNT,
	DEFAULT_PLAYER_BALL_COUNT,
	SERVER_ADDRESS;

	public String getString() {
        Properties server = new Properties();

        try {
            server.load(new FileInputStream(Constants.SERVER_PROPERTIES.get()));
        } catch (Exception e) {
            throw new RuntimeException(Strings.GENERAL_IO_ERROR.get(), e);
        }
        return server.getProperty(this.name());
    }

    public int getInt() {
        return Integer.parseInt(getString());
    }

}
