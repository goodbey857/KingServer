package main.top.kingdon;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher implements Runnable{
    private Socket socket;
    static Map<String,Servlet> requestMapping = new HashMap();
    private HttpRequest request;
    private HttpResponse response;

    static{
        MappingConfig.registerMapping();
    }
    public Dispatcher(Socket socket) {
        this.socket = socket;
        request = new HttpRequest();
        response = new HttpResponse(socket);
    }

    @Override
    public void run() {


        try {
            // 解析请求
            ParseRequest parseRequest = new ParseRequest();
            HttpRequest request = parseRequest.parse(socket.getInputStream());
            // 交给对应的service处理
            Servlet servlet = requestMapping.get(request.getUri());
            if (servlet==null){     //如果没有对应的Servlet就交给静态资源Servlet去处理；
                servlet = requestMapping.get("staticResourceServlet");
            }
            servlet.service(request,response);
            // 响应结果;
            doResponse();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void doResponse(){
        try {
            response.doResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
