import Client.MediaCenterInterface;
import Client.RemoteMediaCenter;
import MediaCenter_GUI.MainView;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        MediaCenterInterface mci = new RemoteMediaCenter("localhost", 16899);
        //MainView m = new MainView(mci, "asdasdasd");
        mci.download(1);
    }
}
