package MediaCenterSystem.DataAccess;

import MediaCenterSystem.BusinessLogic.Conteudo;

import java.util.List;
import java.util.Set;

public class ConteudoDAO {

    private static final String myt = "Conteudo";

    private static final String cCats = "Conteudo_has_Categoria";
    private static final String cOwns = "Utilizador_has_Conteudo";

    private static ConteudoDAO inst = null;

    private ConteudoDAO () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static ConteudoDAO getInstance() {
        if (inst == null) {
            inst = new ConteudoDAO();
        }
        return inst;
    }


    public Conteudo get(int idConteudo){return null;}
    public List<Conteudo> getAll(Set<Integer> al){return null;}
    public List<Conteudo> getAll(List<Integer> al){return null;}
    public void remove(int idContent){}
    public void put(int idContent, Conteudo c){}
    public List<Conteudo> getWithArtist(String artista){return null;}
    public List<Conteudo> getWithCategoria(String categoria){return null;}
    public List<Integer> getAll(){return null;}
}
