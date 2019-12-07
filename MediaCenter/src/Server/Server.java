package Server;

import MediaCenterSystem.MediaCenter;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(12345);
        
        MediaCenter md = new MediaCenter();
        //md.newConta("utitilizador","chuck","chuck@norris.xyz","kick");
        System.out.println("> Server is running and waiting for connection");

        while (true) {
            Socket socket = ss.accept();

            ServerWorker sw = new ServerWorker(socket,md);
            new Thread(sw).start();
        }
    }
}
