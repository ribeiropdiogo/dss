import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*
        Player p = new Player();
        p.addToPlaylist("src/03-metallica-for_whom_the_bell_tolls.flac");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.pause();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.play();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.exit();
         */
        // take the menu bar off the jframe
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        // set the name of the application menu item
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "AppName");

        try {
            // set the look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){

        }

        LoginForm lf = new LoginForm();
    }
}
