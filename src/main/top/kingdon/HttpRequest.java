package main.top.kingdon;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String uri;
    private Map<String,String> headers;
    private Map<String,String> params;
    private String requestMothed;

    public HttpRequest() {
        headers = new HashMap<>();
        params = new HashMap<>();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHeaders(String key) {
        return headers.get(key);
    }

    public void setHeaders(String key,String value) {
        headers.put(key, value);
    }

    public String getParams(String key) {
        return this.params.get(key);
    }

    public void setParams(String key,String value) {
        this.params.put(key,value);
    }

    public String getRequestMothed() {
        return requestMothed;
    }

    public void setRequestMothed(String requestMothed) {
        this.requestMothed = requestMothed;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "uri='" + uri + '\'' +
                ", headers=" + headers +
                ", params=" + params +
                ", requestMothed='" + requestMothed + '\'' +
                '}';
    }
}
