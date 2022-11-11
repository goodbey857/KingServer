package main.top.kingdon;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class HttpResponse {
    private String statusCode = StatusCode.OK;
    private String httpVersion = "HTTP/1.1";
    private Map<String,String> headers = new HashMap<>();
    private OutputStream outputStream;
    private Socket socket;
    private byte[] body = new byte[0];
    private long length;

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public HttpResponse(Socket socket) {
        this.socket = socket;
        try {
            this.outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HttpResponse(String statusCode, String httpVersion, Map<String, String> headers, OutputStream outputStream) {
        this.statusCode = statusCode;
        this.httpVersion = httpVersion;
        this.headers = headers;
        this.outputStream = outputStream;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public  String getHeader(String key) {
        return headers.get(key);
    }

    public void setHeaders(String key,String value) {
        this.headers.put(key, value);
    }

    public void setBody(byte[] body){
        this.body = body;
    }

    private void fillDefaultHeaders(){
        setHeaders("Date",new Date().toString());
        setHeaders("Content-length",String.valueOf(length));
        setHeaders("Connection","close");
    }
    public void doResponse() throws IOException {
        fillResponse();
        this.outputStream.flush();
    }


    public void fillResponse(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(httpVersion);
        stringBuffer.append(" ");
        stringBuffer.append(statusCode);
        stringBuffer.append("\r\n");

        headers.forEach((String k,String v)->{
            stringBuffer.append(k);
            stringBuffer.append(": ");
            stringBuffer.append(v);
            stringBuffer.append("\r\n");
        });
        stringBuffer.append("\r\n");
        try {
            outputStream.write(stringBuffer.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ;

    }
}

