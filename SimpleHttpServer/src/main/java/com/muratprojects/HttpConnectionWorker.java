package com.muratprojects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorker extends Thread {
    private Socket socket;
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorker.class);
    private HttpConnectionWorker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was server with my simple java http server</h1></body></html>";
            final String CRLF = "\n\r"; //13, 10
            String response =
                    "HTTP/1.1 200 OK" + CRLF +//Status : HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
                            "Content-Length: " + html.getBytes().length + CRLF + //HEADER
                            CRLF +
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());
            // TODO: sdelat' writing
            inputStream.close();
            outputStream.close();
            socket.close();
            LOGGER.info("* Connection Processing finished!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
