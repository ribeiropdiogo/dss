package Server;

import Exceptions.PasswordIncorretaException;
import Exceptions.UtilizadorInexistenteException;
import MediaCenterSystem.MediaCenter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerWorker implements Runnable{
    private Socket socket;
    private MediaCenter md;

    public ServerWorker(Socket s, MediaCenter mediacenter) {
        this.socket = s;
        this.md = mediacenter;
    }

    public void run() {
        System.out.println("> New client has established connection from " + socket.getRemoteSocketAddress());

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String data = in.readLine();

            while (data != null && !data.equals("exit")) {
                String[] ops = data.split(" ");
                switch (ops[0]){
                    case "loginGuest":
                        this.md.loginGuest();
                        System.out.println("> Login as Guest");
                        break;

                    case "login":
                        try {
                            this.md.login(ops[1],ops[2]);
                            out.println("ok");
                            System.out.println("> Login");
                        } catch (PasswordIncorretaException p){
                            out.println("wrongpassword");
                        } catch (UtilizadorInexistenteException u){
                            out.println("usernexists");
                        } finally {
                            out.flush();
                        }

                        System.out.println("> Login");
                        break;

                    case "logout":
                        this.md.logout();
                        System.out.println("> Logout");
                        break;

                    default:
                        System.out.println("> Unrecognized operation");
                        break;
                }
                data = in.readLine();
            }

            socket.close();
            out.flush();
            System.out.println("> Connection ended");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
