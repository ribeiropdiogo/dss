import MediaCenterSystem.MediaCenter;
import MediaCenter_GUI.LoginForm;
import MediaCenter_GUI.UploadForm;

public class Main {
    private static MediaCenter mediacenter;
    public static void main(String[] args) {
        mediacenter = new MediaCenter();
        LoginForm lf = new LoginForm(mediacenter);
    }
}
