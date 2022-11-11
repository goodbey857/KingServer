package main.top.kingdon;

import main.top.kingdon.servlets.StaticResourceServlet;

public  class MappingConfig {
    //将servlet映射注册到Dispatcher中
    public static void  registerMapping(){
        registerMapping("staticResourceServlet",new StaticResourceServlet());

    }
    //对Dispatcher.requestMapping.put(path,servlet);进行了封装
    public static void  registerMapping(String path,Servlet servlet){
        Dispatcher.requestMapping.put(path,servlet);
    }

}
