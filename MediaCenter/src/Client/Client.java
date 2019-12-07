package Client;

import MediaCenter_GUI.LoginForm;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static MediaCenterInterface mci;

    public static void main(String[] args) throws InterruptedException, IOException {
        mci = new RemoteMediaCenter("localhost", 12345);

        LoginForm lf = new LoginForm(mci);
    }
}
