package com.muratprojects;

import com.muratprojects.configuration.Configuration;
import com.muratprojects.configuration.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args){
        System.out.println("Server starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Port: " + conf.getPort() + " WebRoot: " +  conf.getWebroot());

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // TODO: sdelat' reading
            String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was server with my simple java http server</h1></body></html>";
            final String CRLF = "\n\r"; //13, 10
            String response =
                    "HTTP/1.1 200 OK" +CRLF +//Status : HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
                        "Content-Length: " + html.getBytes().length + CRLF + //HEADER
                            CRLF+
                            html+
                            CRLF + CRLF;

            outputStream.write(response.getBytes());
            // TODO: sdelat' writing

            inputStream.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
