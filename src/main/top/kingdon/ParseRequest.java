package main.top.kingdon;

import java.io.IOException;
import java.io.InputStream;

/**
 * 根据输入流，解析出Request对象
 */
public class ParseRequest {
    private HttpRequest request = new HttpRequest();
    public HttpRequest parse(InputStream inputStream){

        byte[] bytes = new byte[1024*16];
        int len = 0;
        try {
            len = inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String requestInfo = new String(bytes,0,len);
        String[] requestInfos = requestInfo.split("\r\n");

        parseFirstLine(requestInfos);
        parseHeaders(requestInfos);
        parseParams(requestInfos);


        return this.request;
    }
    private void parseFirstLine(String[] requestInfos){
        // 解析请求方式
        String requestMethod = requestInfos[0].substring(0, requestInfos[0].indexOf(" "));
        request.setRequestMothed(requestMethod);
        // 解析URI
        int requestPathBeginIndex = requestInfos[0].indexOf('/');
        int requetpathEndIndex = requestInfos[0].lastIndexOf(" ");
        String requetpath = requestInfos[0].substring(requestPathBeginIndex,requetpathEndIndex);
        request.setUri(requetpath);
    }
    private void parseHeaders(String[] requestInfos){
        // 解析请求头
        for(int i=1;i<requestInfos.length-2;i++){
            String[] key_value = requestInfos[i].split(":", 2);
            request.setHeaders(key_value[0],key_value[1]);
        }
    }
    private void parseParams(String[] requestInfos){
        String params = null;
        if (request.getRequestMothed().equals("GET")){
            int paramsBegin = requestInfos[0].indexOf("?");
            int paramsEnd = requestInfos[0].lastIndexOf(" ");
            if(paramsBegin!=-1){
                params = requestInfos[0].substring(paramsBegin,paramsEnd);
            }

        }else{
            params = requestInfos[requestInfos.length-1];
        }
        if(params!=null){
            String[] paramsArray = params.split("&");
            for (String s : paramsArray) {
                String[] key_value = s.split("=",2);
                request.setParams(key_value[0],key_value[1]);
            }
        }

    }
}
