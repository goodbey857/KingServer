package top.kingdon;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        InetSocketAddress localhost = new InetSocketAddress("localhost", 8806);
        Inet4Address.getLocalHost();
        System.out.println(localHost);

    }
}
