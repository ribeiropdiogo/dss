package MediaCenter;

import MediaCenter.Utils.MediaMailer;

public class main {

    public static void main(String[] args) {
        MediaMailer mm = new MediaMailer();

        try {
            mm.sendMessage("habbot1@hotmail.com", "teste1", "teste2");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
