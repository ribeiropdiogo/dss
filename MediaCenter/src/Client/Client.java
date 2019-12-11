package Client;

import MediaCenter_GUI.LoginForm;

import java.io.File;
import java.io.IOException;

public class Client {
    private static MediaCenterInterface mci;

    public static void main(String[] args) throws InterruptedException, IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                File folder = new File("src/Client/.reproduction/");
                deleteFolder(folder);
            }
        }));

        mci = new RemoteMediaCenter("localhost", 16899);

        LoginForm lf = new LoginForm(mci);
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                System.out.println(f.getName());
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
    }


}
