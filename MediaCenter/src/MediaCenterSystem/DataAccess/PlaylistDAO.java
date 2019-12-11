package MediaCenterSystem.DataAccess;

import MediaCenterSystem.BusinessLogic.Playlist;
import MediaCenterSystem.DataAccess.DBTools.DBAcess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlaylistDAO {

    private static final String myt = "Playlist";

    private static final String pConts = "Conteudo_in_Playlist";

    private static PlaylistDAO inst = null;

    private ConteudoDAO conteudos;

    private PlaylistDAO () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conteudos = ConteudoDAO.getInstance();
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

    public Playlist get(int idPlaylist) {
        String ifs = "Playlist_id='"+idPlaylist+"'";
        Playlist pl = (Playlist) DBAcess.getQuery(myt, ifs, this::getPlaylist);
        Set<Integer> conts = DBAcess.getIds(pConts, "Conteudo_id", ifs);

        conts.forEach(x -> pl.addDefContent(x, conteudos.get(x)));

        return pl;
    }

    public void put(int idPlaylist, Playlist pl){
        String id = "Playlist_id='"+idPlaylist+"'";
        String params = "('" + idPlaylist + "','" + pl.getNome() + "','" + pl.getDescricao() + "')";
        DBAcess.putQuery(myt, id, params);

        pl.getContents().forEach((k,v) -> conteudos.put(k, v));

    }

    public void put(List<Playlist> plays) {
        plays.forEach(x -> this.put(x.getId(), x));
    }

    public List<Playlist> getAllWith(int idContent) {
        Set<Integer> plays = DBAcess.getIds(myt,"Playlist_id","Conteudo_id='" + idContent + "'");
        List<Playlist> ls = new ArrayList<>();

        plays.forEach(x -> ls.add(this.get(x)));

        return ls;
    }

    private Playlist getPlaylist(ResultSet rs) {
        try {
            Playlist al = null;
            if(rs.next())
                al = Playlist.getInstance(rs.getInt(1),rs.getString(2),rs.getString(3));
            return al;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }
}
