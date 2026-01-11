package Java.Learn.Network;

import java.net.*;

public class IP {
    public static void main(String[] args) throws Exception {
        InetAddress selfIP=InetAddress.getLocalHost();
        System.out.println(selfIP);

        InetAddress aliIP=InetAddress.getByName("www.aliyun.com");
        System.out.println(aliIP);

    }
}
