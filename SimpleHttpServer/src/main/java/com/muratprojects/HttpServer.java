package com.muratprojects;

import com.muratprojects.configuration.Configuration;
import com.muratprojects.configuration.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args){
        LOGGER.info("Server starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
       LOGGER.info("Port: " + conf.getPort() + " WebRoot: " +  conf.getWebroot());
        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(8080, "src/main/resources/http.json");
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
            // TODO: Handle!!!!
        }
    }
}
