package com.loneliness.client.launcher;

//mvn exec:java -Dexec.mainClass="com.loneliness.client.launcher.ClientLauncher"


public class ClientLauncher {
    public static void main(String[] args) {
        String ipAddress = "localhost";
        int port = 8000;
        Client client = new Client(ipAddress,port);
        client.start();


    }
}
