package MediaCenterSystem.DataAccess;

import MediaCenterSystem.BusinessLogic.Album;
import MediaCenterSystem.DataAccess.DBTools.DBAcess;
import MediaCenterSystem.DataAccess.DBTools.DBBaseQueries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlbumDAO {

    private static AlbumDAO inst = null;

    private AlbumDAO () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
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
        return (Integer)DBAcess.excuteQuery(sql, DBAcess::getSize);
    }

    public Album get(Object idAlbum){
        String sql = "SELECT * FROM Album WHERE id='" + idAlbum + "'";
        return (Album)DBAcess.excuteQuery(sql, this::getAlbum);
    }

    public void put(int idAlbum, Album al){
        String id = "id='" + al.getID() + "'";
        String params = "('"+al.getID()+"','"+al.getNome()+"')";
        DBAcess.putQuery("Album", id, params);
    }

    private Album getAlbum(ResultSet rs) {
        try {
            Album al = null;
            if(rs.next())
                al = new Album(rs.getInt(1), rs.getString(2));
            return al;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

}
