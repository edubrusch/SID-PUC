package com.eduardo.speculate.server;

import java.io.FileInputStream;
import java.util.Properties;

import com.eduardo.speculate.commons.Constants;
import com.eduardo.speculate.commons.Strings;

public enum ServerProperties {

	CONNECTION_PORT,
	MAX_LOBBY_SIZE;

	public String getProperty() {
        Properties server = new Properties();

        try {
            server.load(new FileInputStream(Constants.SERVER_PROPERTIES.get()));
        } catch (Exception e) { // TODO retornar para o main logar e sair
            throw new RuntimeException(Strings.GENERAL_IO_ERROR.get(), e);
        }
        return server.getProperty(this.name());
    }

    public String get() {
        return getProperty();
    }

}
