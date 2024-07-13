package com.muratprojects.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);
    private static final int SP = 0x20; //32
    private static final int CR = 0x0D; //13
    private static final int LF = 0x0A; //10
    public HttpMessage parseHttpRequest(InputStream inputStream) throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        HttpRequest request = new HttpRequest();

        parseRequestLine(inputStream, request);
        parseHeaders(inputStream, request);
        parseBody(inputStream, request);

        return request;
    }

    private void parseRequestLine(InputStream reader, HttpMessage request) throws IOException {
        StringBuilder processingDataBuffer = new StringBuilder();
        boolean methodParsed = false;
        boolean requestTargetParsed = false;
        int _byte;
        while ((_byte = reader.read()) >= 0){
            if (_byte == CR){
                _byte = reader.read();
                if (_byte == LF){
                    LOGGER.debug("Request line VERSION to Process : {}", processingDataBuffer.toString());
                    return;
                }
            }
            if (_byte == SP){
                if (!methodParsed){
                    LOGGER.debug("Request METHOD to process : {}", processingDataBuffer.toString());
                    methodParsed = true;
                } else if(!requestTargetParsed){
                    LOGGER.debug("Request Line REQ TARGET to Process : {}", processingDataBuffer.toString());

                }
                processingDataBuffer.delete(0, processingDataBuffer.length());
                // TODO: Process previous data
            } else {
                processingDataBuffer.append((char) _byte);
            }
        }
    }

    private void parseHeaders(InputStream reader, HttpMessage request) {

    }

    private void parseBody(InputStream reader, HttpMessage request) {

    }

}
