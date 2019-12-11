package MediaCenterSystem.DataAccess;

import MediaCenterSystem.BusinessLogic.Album;
import MediaCenterSystem.DataAccess.DBTools.DBAcess;
import MediaCenterSystem.DataAccess.DBTools.DBBaseQueries;

import java.sql.ResultSet;
import java.util.Set;

public class AlbumDAO {

    private static final String myt = "Album";

    private static final String aConts = "Conteudo";

    private static AlbumDAO inst = null;

    private ConteudoDAO conteudos;

    private AlbumDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conteudos = ConteudoDAO.getInstance();
            Album.setCurrentNextID(DBAcess.maxIds(myt,"Album_id") + 1);
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static AlbumDAO getInstance() {
        if (inst == null) {
            inst = new AlbumDAO();
        }
        return inst;
    }

    public int size() {
        String sql = DBBaseQueries.count("Album");
        return (Integer) DBAcess.excuteQuery(sql, DBAcess::getSize);
    }

    public Album get(int idAlbum) {
        Album al = (Album) DBAcess.getQuery(myt, "Album_id='" + idAlbum + "'", this::getAlbum);
        Set<Integer> conts = DBAcess.getIds("Conteudo", "Conteudo_id", "Album_id='" + idAlbum + "'");

        conts.forEach(x -> al.addContuedo(x, conteudos.get(x)));

        return al;
    }

    public void put(int idAlbum, Album al) {
        String id = "Album_id='" + al.getID() + "'";
        String params = "('" + al.getID() + "','" + al.getNome() + "')";
        DBAcess.putQuery(myt, id, params);

        al.getMapContents().forEach((k, v) -> conteudos.put(k, v));
    }

    private Album getAlbum(ResultSet rs) {
        try {
            Album al = null;
            if (rs.next())
                al = Album.getInstance(rs.getInt(1), rs.getString(2));
            return al;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

}
