package Server;

import MediaCenterSystem.MediaCenter;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(16899);

        MediaCenter md = new MediaCenter();
        System.out.println("> Server is running and waiting for connection");

        try {
            //md.newConta("utilizador","test","test@test.xyz","test");
            md.newConta("admin","ruifcp","niggjoe@gmail.com","loles");
            System.out.println("Conta criada com sucesso");
        } catch(Exception exc) {
            System.out.println("Erro");
            System.out.println(exc.getMessage());
        }

        while (true) {
            Socket socket = ss.accept();

            ServerWorker sw = new ServerWorker(socket,md);
            new Thread(sw).start();
        }
    }
}
