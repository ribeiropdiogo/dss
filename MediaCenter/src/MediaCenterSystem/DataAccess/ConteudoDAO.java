package MediaCenterSystem.DataAccess;

import MediaCenterSystem.BusinessLogic.Cadastrado.Utilizador;
import MediaCenterSystem.BusinessLogic.Categoria;
import MediaCenterSystem.BusinessLogic.Conteudo;
import MediaCenterSystem.DataAccess.DBTools.DBAcess;

import java.sql.ResultSet;
import java.util.*;

public class ConteudoDAO {

    private static final String myt = "Conteudo";

    private static final String cCats = "Conteudo_has_Categoria";
    private static final String cOwns = "Utilizador_has_Conteudo";

    private static ConteudoDAO inst = null;

    private CadastradoDAO cadastrados = null;
    private CategoriaDAO categorias = null;

    private ConteudoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cadastrados = CadastradoDAO.getInstance();
            categorias = CategoriaDAO.getInstance();
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static ConteudoDAO getInstance() {
        if (inst == null) {
            inst = new ConteudoDAO();
        }
        return inst;
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
        String params = "('" + idContent + "','" + c.getNome() + "','" + c.getTamanho() + "','" + c.getAutor() + "')";
        DBAcess.putQuery(myt, idCont(idContent), params);
        this.removeContentEntries(idContent);
        Map<String, Categoria> cates = c.getCategorias();
        Map<String, Utilizador> urs = c.getDonos();

        String cats = this.convertContStrSet(idContent, cates.keySet());
        String own = this.convertContStrSet(idContent, urs.keySet());

        if (!cats.equals(""))
            DBAcess.putQuery(cCats, "", cats);

        if (!own.equals(""))
            DBAcess.putQuery(cOwns, "", own);

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
        Set<Integer> allC = DBAcess.getIds(myt, "Conteudo_id", ifs);

        allC.forEach(x -> ls.add(this.get(x)));

        return ls;
    }

    public Set<Integer> getAll() {
        return DBAcess.getIds(myt, "Conteudo_id");
    }

    private Conteudo getConteudo(ResultSet rs) {
        try {
            Conteudo al = null;
            if (rs.next())
                al = Conteudo.getInstance(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getDouble(4), rs.getString(5), rs.getString(6));
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
}
