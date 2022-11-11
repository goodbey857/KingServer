package main.top.kingdon;

import java.io.IOException;

public interface Server {
    void start();
    void exit() throws IOException;
}
