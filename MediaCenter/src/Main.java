public class Main {
    public static void main(String[] args) {
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
    }
}
