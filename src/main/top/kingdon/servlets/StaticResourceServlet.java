package main.top.kingdon.servlets;

import main.top.kingdon.AbstractServlet;
import main.top.kingdon.HttpRequest;
import main.top.kingdon.HttpResponse;
import main.top.kingdon.StatusCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StaticResourceServlet extends AbstractServlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        String uri = request.getUri();
        String userDir = this.getClass().getClassLoader().getResource("").getFile();
        String resourcePath = userDir+uri;
        File file = new File(resourcePath);
        if(!file.exists()){
            response.setStatusCode(StatusCode.NOT_FOUND);
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = fileInputStream.readAllBytes();
            response.setBody(bytes);
            response.setLength(bytes.length);
            response.setHeaders("Content-type","text/html;charset=utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
