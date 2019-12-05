import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;

public class MediaPlayer {
    private String line;
    private OutputStream stdin;
    private InputStream stderr;
    private InputStream stdout;

    public MediaPlayer(){
        // launch EXE and grab stdin/stdout and stderr
        Process process = null;
        try {
            process = Runtime.getRuntime ().exec ("vlc -Irc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        stdin = process.getOutputStream ();
        stderr = process.getErrorStream ();
        stdout = process.getInputStream ();
        System.out.println("VLC started.");
    }

    public void addToQueue(String path){
        // "write" the parms into stdin
        line = "add " + path + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        line = "pause" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        line = "play" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void emptyQueue(){
        line = "clear" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fullscreenON(){
        line = "fullscreen on" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fullscreenOFF(){
        line = "fullscreen off" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void incVolume(){
        line = "volup 1" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decVolume(){
        line = "voldown 1" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextContent(){
        line = "next" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void prevContent(){
        line = "prev" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shuffleON(){
        line = "random on" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shuffleOFF(){
        line = "random off" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(){
        line = "quit" + "\n";
        try {
            stdin.write(line.getBytes() );
            stdin.flush();
            stdin.close();
            // clean up if any output in stdout
            BufferedReader brCleanUp =
                    new BufferedReader (new InputStreamReader (stdout));
            while ((line = brCleanUp.readLine ()) != null) {
                //System.out.println ("[Stdout] " + line);
            }
            brCleanUp.close();

            // clean up if any output in stderr
            brCleanUp =
                    new BufferedReader (new InputStreamReader (stderr));
            while ((line = brCleanUp.readLine ()) != null) {
                //System.out.println ("[Stderr] " + line);
            }
            brCleanUp.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
