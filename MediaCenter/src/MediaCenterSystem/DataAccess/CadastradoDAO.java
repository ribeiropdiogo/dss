package MediaCenterSystem.DataAccess;

import MediaCenterSystem.BusinessLogic.Cadastrado.Administrador;
import MediaCenterSystem.BusinessLogic.Cadastrado.Cadastrado;
import MediaCenterSystem.BusinessLogic.Cadastrado.Utilizador;
import MediaCenterSystem.DataAccess.DBTools.DBAcess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CadastradoDAO {

    private static final String mytC = "Cadastrado";
    private static final String mytU = "Utilizador";
    private static final String mytA = "Administrador";

    private static final String uContents = "has_Conteudo";
    private static final String uPlays = "has_Playlist";
    private static final String uAmigos = "Amigos";
    private static final String uPedidos = "Pedidos";

    private static CadastradoDAO inst = null;

    private CadastradoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CadastradoDAO getInstance() {
        if (inst == null) {
            inst = new CadastradoDAO();
        }
        return inst;
    }

    public Cadastrado get(String idConta) {
        Cadastrado al = null;


        if (DBAcess.countQuery(mytA, idUser(idConta)) > 0) {
            // e um admin.
            al = (Administrador) DBAcess.getQuery(mytC, idUser(idConta), this::getAdmin);
        } else if (DBAcess.countQuery(mytU, idUser(idConta)) > 0) {
            Utilizador ul = (Utilizador) DBAcess.getQuery(mytC, idUser(idConta), this::getUtilizador);
            // e um utilizador
            String ifs = idUser2(idConta);

            Set<Integer> conts = DBAcess.getIds(uContents, "Conteudo_id", ifs);
            Set<Integer> pls = DBAcess.getIds(uPlays, "Playlist_id", ifs);
            Set<String> amigos = DBAcess.getNames(uAmigos, "username1", ifs);
            Set<String> pedidos = DBAcess.getNames(uPedidos, "username1", ifs);

            ul.setContents(conts);
            ul.setPlaylists(pls);
            ul.setAmigos(amigos);
            ul.setPedidos(pedidos);
            al = ul;
        }

        return al;
    }

    public void put(String idConta, Cadastrado conta) {
        String params = "('" + conta.getUsername() + "','" + conta.getEmail() + "','" + conta.getPassword() + "')";
        DBAcess.putQuery(mytC, idUser(idConta), params);
    }

    public void put(String idConta, Utilizador conta) {
        this.put(idConta, (Cadastrado) conta);
        this.removeUserEntries(idConta);

        DBAcess.putQuery(mytU, idUser(idConta), "('" + idConta + "')");

        String conts = this.convertUserIntSet(idConta, conta.getContents());
        String pls = this.convertUserIntSet(idConta, conta.getPlaylist());
        String amigos = this.convertUserStrSet(idConta, conta.getAmigos());
        String pedidos = this.convertUserStrSet(idConta, conta.getPedidos());

        if (!conts.equals(""))
            DBAcess.putQuery(uContents, "", conts);

        if (!pls.equals(""))
            DBAcess.putQuery(uPlays, "", pls);

        if (!amigos.equals(""))
            DBAcess.putQuery(uAmigos, "", amigos);

        if (!pedidos.equals(""))
            DBAcess.putQuery(uPedidos, "", pedidos);
    }

    public void put(String idConta, Administrador conta) {
        this.put(idConta, (Cadastrado) conta);
        DBAcess.putQuery(mytA, idUser(idConta), "('" + idConta + "')");
    }

    public boolean contains(String idConta) {
        return DBAcess.countQuery(mytC, idUser(idConta)) > 0;
    }

    public void remove(String idConta) {
        String id = idUser(idConta);

        if (DBAcess.countQuery(mytA, id) > 0) {
            // e um admin.
            DBAcess.removeEntry(mytA, id);
        } else {
            // e um utilizador
            this.removeUserEntries(id);
            DBAcess.removeEntry(mytU, id);
        }

        DBAcess.removeEntry(mytC, id);
    }

    private Administrador getAdmin(ResultSet rs) {
        try {
            Administrador al = null;
            if (rs.next())
                al = new Administrador(rs.getString(1), rs.getString(2), rs.getString(3));
            return al;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    private Utilizador getUtilizador(ResultSet rs) {
        try {
            Utilizador al = null;
            if (rs.next())
                al = new Utilizador(rs.getString(1), rs.getString(2), rs.getString(3));
            return al;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    private String idUser(String idConta) {
        return "username='" + idConta + "'";
    }

    private String idUser2(String id) {
        return "username='" + id + "'";
    }

    private void removeUserEntries(String id) {
        //DBAcess.removeEntry(mytA, idUser(id));
        DBAcess.removeEntry(uContents, "username='" + id + "'");
        DBAcess.removeEntry(uPlays, "username='" + id + "'");
        DBAcess.removeEntry(uPedidos, "username='" + id + "'");
        DBAcess.removeEntry(uPedidos, "username1='" + id + "'");
        DBAcess.removeEntry(uAmigos, "username='" + id + "'");
        DBAcess.removeEntry(uAmigos, "username1='" + id + "'");
    }

    private String convertUserIntSet(String idConta, Set<Integer> inters) {
        List<String> ls = new ArrayList<>();

        for (Integer it : inters) {
            ls.add("('" + idConta + "','" + it + "')");
        }

        return DBAcess.makeVarStr(ls);
    }

    private String convertUserStrSet(String idConta, Set<String> inters) {
        List<String> ls = new ArrayList<>();

        for (String it : inters) {
            ls.add("('" + idConta + "','" + it + "')");
        }

        return DBAcess.makeVarStr(ls);
    }

}
