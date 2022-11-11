package main.top.kingdon;

public abstract class AbstractServlet implements Servlet {
    @Override
    public void service(HttpRequest request,HttpResponse response){
        switch(request.getRequestMothed()){
            case "GET":doGet(request,response); break;
            case  "POST":doPost(request,response); break;
            default:doDefault(request,response);
        }

    }
    public void doGet(HttpRequest request,HttpResponse response){
        doDefault(request,response);
    }
    public void doPost(HttpRequest request,HttpResponse response){
        doDefault(request,response);
    }
    public void doDefault(HttpRequest request,HttpResponse response){
        response.setStatusCode(StatusCode.METHOD_NOT_ALLOWED);
    }
}
