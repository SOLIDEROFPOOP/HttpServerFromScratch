package com.muratprojects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
    private int port;
    private String webroot;
    ServerSocket serverSocket;
    public ServerListenerThread(Integer port , String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }
    @Override
    public void run(){
        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                LOGGER.info(" * Connection accepted: " + socket.getInetAddress());
                HttpConnectionWorker connectionWorker = new HttpConnectionWorker(socket);
                connectionWorker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Problem with setting socket " , e);
        } finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {

                }
            }
        }
    }
}
