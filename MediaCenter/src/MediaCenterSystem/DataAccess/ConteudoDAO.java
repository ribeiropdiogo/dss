package MediaCenterSystem.DataAccess;

import MediaCenterSystem.BusinessLogic.Cadastrado.Utilizador;
import MediaCenterSystem.BusinessLogic.Categoria;
import MediaCenterSystem.BusinessLogic.Conteudo;
import MediaCenterSystem.DataAccess.DBTools.DBAcess;
import MediaCenterSystem.DataAccess.DBTools.DBBaseQueries;

import java.sql.ResultSet;
import java.util.*;

public class ConteudoDAO {

    private static final String myt = "Conteudo";

    private static final String cCats = "Conteudo_has_Categoria";
    private static final String cOwns = "Utilizador_has_Conteudo";

    private static ConteudoDAO inst = null;

    private CadastradoDAO cadastrados = null;
    private CategoriaDAO categorias = null;
    private AlbumDAO albuns;

    private ConteudoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cadastrados = CadastradoDAO.getInstance();
            categorias = CategoriaDAO.getInstance();
            Conteudo.setCurrentNextID(DBAcess.maxIds(myt,"Conteudo_id") + 1);
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static ConteudoDAO getInstance() {
        if (inst == null) {
            inst = new ConteudoDAO();
            inst.setAlbumDAO(AlbumDAO.getInstance(inst));
        }
        return inst;
    }

    public void setAlbumDAO(AlbumDAO album) {
        this.albuns = album;
    }

    public Conteudo get(int idConteudo) {
        String ifs = idCont(idConteudo);
        Conteudo cont = (Conteudo) DBAcess.getQuery(myt, ifs, this::getConteudo);
        Set<String> cats = DBAcess.getNames(cCats, "Categoria_nome", ifs);
        Set<String> owns = DBAcess.getNames(cOwns, "username", ifs);
        Categoria tmp1;
        Utilizador tmp2;


        for (String s : cats) {
            cont.adicionaCategoria(s, categorias.get(s));
        }

        for (String s : owns) {
            cont.adicionaDono(s, (Utilizador) cadastrados.get(s));
        }

        return cont;
    }

    public List<Conteudo> getAllConts() {
        return this.getAll(DBAcess.getIds(myt, "Conteudo_id"));
    }

    public List<Conteudo> getAll(Collection<Integer> al) {
        List<Conteudo> ls = new ArrayList<>();

        al.forEach(x -> ls.add(this.get(x)));

        return ls;
    }

    public void remove(int idContent) {
        this.removeContentEntries(idContent);
        DBAcess.removeEntry(myt, idCont(idContent));
    }

    public void put(int idContent, Conteudo c) {
        String ids = "(Conteudo_id,nome,tamanho,duracao,autor,path";
        String params = "('" + idContent + "','" + c.getNome() + "','" + c.getTamanho() + "','" + c.getDuracao() + "','" + c.getAutor() + "','" + c.getPath() +"'";


        if(c.getAlbum() == -1) {
            params += ")";
            ids += ")";
        } else {
            params += ",'" + c.getAlbum() + "')";
            ids += ",Album_id)";
        }

        DBAcess.putQuery(myt, idCont(idContent), params, ids);
        this.removeContentEntries(idContent);
        Map<String, Categoria> cates = c.getCategorias();
        Map<String, Utilizador> urs = c.getDonos();

        String cats = this.convertContStrSet(idContent, cates.keySet());
        String own = this.convertContStrSet2(idContent, urs.keySet());

        if (!cats.equals(""))
            DBAcess.putQuery(cCats, "", cats);

        if (!own.equals("")) {
            System.out.println(own);
            DBAcess.putQuery(cOwns, "", own);
        }

        cates.forEach((k, v) -> categorias.put(k, v));
        urs.forEach((k, v) -> cadastrados.put(k, v));
    }

    public List<Conteudo> getWithArtist(String artista) {
        String ifs = "autor='" + artista + "'";
        List<Conteudo> ls = new ArrayList<>();
        Set<Integer> allC = DBAcess.getIds(myt, "Conteudo_id", ifs);

        allC.forEach(x -> ls.add(this.get(x)));

        return ls;
    }

    public List<Conteudo> getWithCategoria(String categoria) {
        String ifs = "Categoria_nome='" + categoria + "'";
        List<Conteudo> ls = new ArrayList<>();
        Set<Integer> allC = DBAcess.getIds(cCats, "Conteudo_id", ifs);

        allC.forEach(x -> ls.add(this.get(x)));

        return ls;
    }

    public Set<Integer> getAll() {
        return DBAcess.getIds(myt, "Conteudo_id");
    }

    public int checkDup(String nome, String autor){
        String ifs = "nome='" + nome + "' AND autor='" + autor + "'";
        return (Integer)DBAcess.getQuery(myt,"Conteudo_id",ifs,this::getContID);
    }

    private Conteudo getConteudo(ResultSet rs) {
        try {
            Conteudo al = null;
            if(rs.next()) {
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                long tamanho = rs.getLong(3);
                long duracao = rs.getLong(4);
                String autor = rs.getString(5);
                String path = rs.getString(6);
                int album = rs.getInt(7);
                if(rs.wasNull())
                    album = -1;
                al = Conteudo.getInstance(id,nome,tamanho,duracao,autor,path,album);
            }
                return al;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    private String idCont(int idConteudo) {
        return "Conteudo_id='" + idConteudo + "'";
    }

    private void removeContentEntries(int idConteudo) {
        String ifs = idCont(idConteudo);
        DBAcess.removeEntry(cCats, ifs);
        DBAcess.removeEntry(cOwns, ifs);
    }

    private String convertContStrSet(int idCont, Set<String> inters) {
        List<String> ls = new ArrayList<>();

        for (String it : inters) {
            ls.add("('" + idCont + "','" + it + "')");
        }

        return DBAcess.makeVarStr(ls);
    }

    private String convertContStrSet2(int idCont, Set<String> inters) {
        List<String> ls = new ArrayList<>();

        for (String it : inters) {
            ls.add("('" + it + "','" + idCont + "')");
        }

        return DBAcess.makeVarStr(ls);
    }

    private Integer getContID(ResultSet rs) {
        try {
            int r = -1;

            if(rs.next())
                r = rs.getInt(1);

            return r;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }
}
