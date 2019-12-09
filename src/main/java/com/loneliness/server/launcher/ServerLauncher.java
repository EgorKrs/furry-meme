package com.loneliness.server.launcher;


//mvn exec:java -Dexec.mainClass="com.loneliness.server.launcher.ServerLauncher" -Dexec.args"8000"

public class ServerLauncher {
    public static void main(String[] args)  {
        Server server;
        if(args.length!=0){
            try {
                server = new Server(Integer.parseInt(args[0]));
            }
            catch (NumberFormatException e){
                System.out.println("неверно заданный порт. Выбран порт 8000");
                server = new Server(8000);
            }
        }
        else server = new Server(8000);
        while (true){
            if(server.applyConnection()==1)
                break;
        }


    }
}
