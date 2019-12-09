package MediaCenterSystem.DataAccess;

import MediaCenterSystem.BusinessLogic.Categoria;
import MediaCenterSystem.DataAccess.DBTools.DBAcess;
import MediaCenterSystem.DataAccess.DBTools.DBBaseQueries;

import java.sql.ResultSet;

public class CategoriaDAO {

    private static final String myt = "Categoria";

    public boolean contains(String idCat){
        String sql = DBBaseQueries.count(myt,"nome='"+idCat+"'");
        return ((Integer)DBAcess.excuteQuery(sql,DBAcess::getSize)) > 0;
    }

    public Categoria get(String idCat){
        String sql = DBBaseQueries.select("nome='"+idCat+"'",myt);
        return (Categoria)DBAcess.excuteQuery(sql, this::getCategoria);
    }

    public void put(String idCat, Categoria ca) {
        String id = "nome='" + idCat + "'";
        String params = "('"+ca.getNome()+"')";
        DBAcess.putQuery(myt, id, params);
    }

    private Categoria getCategoria(ResultSet rs) {
        try {
            Categoria al = null;
            if(rs.next())
                al = new Categoria(rs.getString(1));
            return al;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }
}
