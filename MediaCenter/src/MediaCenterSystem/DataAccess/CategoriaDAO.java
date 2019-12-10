package MediaCenterSystem.DataAccess;

import MediaCenterSystem.BusinessLogic.Categoria;
import MediaCenterSystem.DataAccess.DBTools.DBAcess;
import MediaCenterSystem.DataAccess.DBTools.DBBaseQueries;

import java.sql.ResultSet;

public class CategoriaDAO {

    private static final String myt = "Categoria";

    private static CategoriaDAO inst = null;

    private CategoriaDAO () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CategoriaDAO getInstance() {
        if (inst == null) {
            inst = new CategoriaDAO();
        }
        return inst;
    }

    public boolean contains(String idCat){
        return DBAcess.countQuery(myt, "Categoria_nome='"+idCat+"'") > 0;
    }

    public Categoria get(String idCat){
        return (Categoria)DBAcess.getQuery(myt, "Categoria_nome='"+idCat+"'", this::getCategoria);
    }

    public void put(String idCat, Categoria ca) {
        String id = "Categoria_nome='" + idCat + "'";
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
