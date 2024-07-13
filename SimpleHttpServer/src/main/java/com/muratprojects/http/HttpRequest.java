package com.muratprojects.http;

public class HttpRequest extends HttpMessage{
    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest(){

    }
    void setMethod(String methodName){
        this.method = HttpMethod.valueOf(methodName);
    }
}
