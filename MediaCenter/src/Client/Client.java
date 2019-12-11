package Client;

import MediaCenter_GUI.LoginForm;

import java.io.IOException;

public class Client {
    private static MediaCenterInterface mci;

    public static void main(String[] args) throws InterruptedException, IOException {
        mci = new RemoteMediaCenter("localhost", 16899);

        LoginForm lf = new LoginForm(mci);
    }
}
