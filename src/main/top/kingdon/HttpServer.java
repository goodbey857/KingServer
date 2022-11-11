package main.top.kingdon;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer implements Server{
    private int port = 18800;
    private ServerSocket serverSocket;
    @Override
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            Socket socket;
            while(true){
                socket = serverSocket.accept();
                Thread thread = new Thread(new Dispatcher(socket));
                thread.run();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exit() throws IOException {
        serverSocket.close();
    }

}
