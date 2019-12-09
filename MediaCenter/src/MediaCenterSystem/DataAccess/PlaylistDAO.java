package MediaCenterSystem.DataAccess;

import MediaCenterSystem.BusinessLogic.Playlist;

import java.util.List;

public class PlaylistDAO {

    private static final String myt = "Playlist";

    private static final String pConts = "Conteudo_in_Playlist";

    private static PlaylistDAO inst = null;

    private PlaylistDAO () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static PlaylistDAO getInstance() {
        if (inst == null) {
            inst = new PlaylistDAO();
        }
        return inst;
    }

    public Playlist get(long idPlaylist){return null;}
    public void put(long idPlaylist, Playlist pl){}
    public void put(List<Playlist> plays){}
    public List<Playlist> getAllWith(long idContent){return null;}
}
