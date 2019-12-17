package MediaCenterSystem;

import Exceptions.*;
import MediaCenterSystem.BusinessLogic.Album;
import MediaCenterSystem.BusinessLogic.Cadastrado.Administrador;
import MediaCenterSystem.BusinessLogic.Cadastrado.Cadastrado;
import MediaCenterSystem.BusinessLogic.Cadastrado.Utilizador;
import MediaCenterSystem.BusinessLogic.Categoria;
import MediaCenterSystem.BusinessLogic.Conteudo;
import MediaCenterSystem.BusinessLogic.Playlist;
import MediaCenterSystem.DataAccess.*;
import Utilities.MediaMailer;
import Utilities.Par;
import Utilities.Util;

import javax.mail.MessagingException;
import java.lang.SecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Collections.shuffle;

public class MediaCenter {

    public interface ArrMaker {
        String[] toArr(Object o);
    }

    private static final int MAX_RAND_SONGS = 25;

    private CategoriaDAO categorias;
    private AlbumDAO albuns;
    private PlaylistDAO playlists;
    private CadastradoDAO membros;
    private ConteudoDAO conteudos;
    private MediaMailer mailer;

    public MediaCenter() {
        categorias = CategoriaDAO.getInstance();
        playlists = PlaylistDAO.getInstance();
        membros = CadastradoDAO.getInstance();
        conteudos = ConteudoDAO.getInstance();
        albuns = AlbumDAO.getInstance(conteudos);
        mailer = new MediaMailer();
    }

    public void loginGuest() {
    }

    public void logout() {
    }

    public void removeContent(int idPlaylist, int idContent) {
        Playlist pl = playlists.get(idPlaylist);

        pl.removeContent(idContent);

        playlists.put(idPlaylist, pl);
    }

    public void addAlbum(int idPlaylist, int idAlbum) {
        Album al = albuns.get(idAlbum);
        Set<Integer> sngs = al.getContents();
        Playlist pl = playlists.get(idPlaylist);
        List<Conteudo> cvector = conteudos.getAll(sngs);

        pl.addContents(cvector);

        playlists.put(idPlaylist, pl);
    }

    public void removerAmigo(String idConta, String idAmigo) {
        Utilizador conta = (Utilizador) membros.get(idConta);

        conta.removeAmigo(idAmigo);

        membros.put(idConta, conta);
    }

    public void respondePedido(String idConta, String idAmigo, boolean resp) {
        Utilizador conta = (Utilizador) membros.get(idConta);
        Utilizador amigo = (Utilizador) membros.get(idAmigo);

        conta.respondePedido(idAmigo, resp);

        amigo.removePedido(idAmigo);
        if(resp)
            amigo.addAmigo(idConta);

        membros.put(idConta, conta);
        membros.put(idAmigo, amigo);
    }

    public void formConvite(String idConta, String idAmigo) throws UtilizadorInexistenteException, AmizadeException {

        if (!membros.contains(idAmigo))
            throw new UtilizadorInexistenteException("O utilizador " + idAmigo + " não existe no sistema!");

        Utilizador am;
        Utilizador c = (Utilizador) membros.get(idConta);

        if (c.temAmigo(idAmigo))
            throw new AmizadeException("O utilizador " + idAmigo + " já é seu amigo!");

        am = (Utilizador) membros.get(idAmigo);
        am.formConvite(idConta);

        membros.put(idAmigo, am);
    }

    public void removeContent(String idConta, int idContent) throws SecurityException {
        int sz;
        Conteudo con;
        List<Playlist> plays;
        Utilizador c = (Utilizador) membros.get(idConta);

        c.removeContent(idContent);

        membros.put(idConta, c);

        con = conteudos.get(idContent);

        sz = con.removeDono(idConta);

        if (sz == 0) {
            plays = playlists.getAllWith(idContent);

            for (Playlist pl : plays) {
                pl.removeContent(idContent);
            }

            playlists.put(plays);
            conteudos.remove(idContent);
        }
    }

    public void alteraPass(String idConta, String pOld, String pNew, String pNewC)
            throws PasswordFracaException, PasswordIncorretaException {

        if (isWeakPassword(pNew))
            throw new PasswordFracaException("A sua password é demasiado fraca, deve colocar números e letras!");

        Cadastrado c = membros.get(idConta);

        c.alteraPass(pOld, pNew, pNewC);

        membros.put(idConta, c);
    }

    public void alteraEmail(String idConta, String newMl) {
        Cadastrado c = membros.get(idConta);

        c.alteraEmail(newMl);

        membros.put(idConta, c);
    }

    public Set<String> getCategorias(int idContent) {
        return conteudos.get(idContent).getCategorias().keySet();
    }

    public void adicionarCategoria(int idContent, String idCat) {
        Categoria ca = reassureCategory(idCat);
        Conteudo c = conteudos.get(idContent);

        c.adicionaCategoria(idCat, ca);

        conteudos.put(idContent, c);
    }

    public void alterarCategoria(int idContent, String oldCat, String newCat) {
        Categoria ca = reassureCategory(newCat);
        Conteudo c = conteudos.get(idContent);

        c.alteraCategoria(oldCat, newCat, ca);

        conteudos.put(idContent, c);
    }

    public void removerCategoria(int idContent, String idCat) {
        Conteudo c = conteudos.get(idContent);

        c.eliminaCategoria(idCat);

        conteudos.put(idContent, c);
    }

    public String getPath(int idContent) {
        return conteudos.get(idContent).getPath();
    }

    public void randomPlaylist(String idConta, String nome, String desc) {
        this.addPlaylist(idConta, this.randomPlaylist(nome, desc));
    }

    public void newPlaylist(String idConta, String nome, String desc, String cat) {
        this.addPlaylist(idConta, this.newCatPlaylist(nome, desc, cat));
    }

    public void artistPlaylist(String idConta, String nome, String desc, String artista) {
        this.addPlaylist(idConta, this.artistPlaylist(nome, desc, artista));
    }

    public void newPlaylist(String idConta, String nome, String desc) {
        this.addPlaylist(idConta, this.newPlaylist(nome, desc));
    }

    public List<Par<Integer, String>> getPlaylists(String idConta) {
        return ((Utilizador) membros.get(idConta)).getPlaylists();
    }

    public void removePlaylist(String idConta, int idPlaylist) {
        Utilizador u = (Utilizador) membros.get(idConta);

        u.removePlaylist(idPlaylist);

        membros.put(idConta, u);
    }

    public void editPName(int idPlaylist, String pNome) {
        Playlist p = playlists.get(idPlaylist);

        p.setNome(pNome);

        playlists.put(idPlaylist, p);
    }

    public void editPDesc(int idPlaylist, String pDesc) {
        Playlist p = playlists.get(idPlaylist);

        p.setDescricao(pDesc);

        playlists.put(idPlaylist, p);
    }

    public void adicionaPlaylist(int idPlaylist, int idContent){
        Playlist p = playlists.get(idPlaylist);
        Conteudo cn = conteudos.get(idContent);

        p.addContent(idContent, cn);

        playlists.put(idPlaylist, p);
    }

    public boolean checkPermissions(String idConta, int idContent) {
        return ((Utilizador) membros.get(idConta)).checkOwnership(idContent);
    }

    public String download(int idContent) {
        return conteudos.get(idContent).getPath();
    }

    public void upload(String idConta, String nome, long tamanho, long duracao, String autor, String path, String album) {
        int cid, r = conteudos.checkDup(nome, autor);
        int aid = -1;
        Conteudo c;
        Album a = null;
        Utilizador u;

        if(!album.equals("")) {
            if (albuns.contains(album))
                a = albuns.get(album);
            else
                a = new Album(album);
            aid = a.getID();
        }


        if(r == -1) {
            //System.out.println("FUCKING PATH + " + path);
            c = new Conteudo(nome,tamanho,duracao,autor,path,aid);
        } else {
            c = conteudos.get(r);
        }

        if(!album.equals("")) {
            a.addContuedo(c.getId(), c);
            albuns.put(aid, a);
        }

        //System.out.println(c.getPath());

        u = (Utilizador)membros.get(idConta);
        c.adicionaDono(u.getUsername(), u);
        u.addContent(c.getId());
        membros.put(u.getUsername(), u);
        conteudos.put(c.getId(), c);
    }

    public void newConta(String tipo, String idConta, String email, String password) throws UtilizadorRepetidoException {
        if (membros.contains(idConta))
            throw new UtilizadorRepetidoException("Já existe um cadastrado com o username " + idConta + "!");

        if (tipo.equals("admin"))
            membros.put(idConta, new Administrador(idConta, email, password));
        else
            membros.put(idConta, new Utilizador(idConta, email, password));
    }

    public void removeAccount(String idConta) {
        Cadastrado c = membros.get(idConta);

        Set<Integer> list = c.getContentList();

        list.forEach(it -> this.removeContent(idConta, it));

        membros.remove(idConta);
    }

    public void forgottenPassword(String username, String email) throws UtilizadorInexistenteException, SecurityException, EmailException {
        String password;
        Cadastrado c = this.getUser(username);

        if (!c.checkMail(email))
            throw new SecurityException("O email indicado não corresponde à conta indicada.");

        password = Util.generatePassword();

        c.setPassword(password);

        this.sendMail(email, password);

        membros.put(username, c);
    }

    public boolean login(String username, String password) throws UtilizadorInexistenteException, PasswordIncorretaException {
        Cadastrado c = this.getUser(username);

        if (!c.checkPassword(password))
            throw new PasswordIncorretaException("A password indica está incorreta!");

        return c instanceof Utilizador;
    }

    public String[] getCategorias() {
        List<String> cats = categorias.getCategorias();
        String[] arr = new String[cats.size()];

        for (int i =0; i < cats.size(); i++)
            arr[i] = cats.get(i);

        return arr;
    }

    public String[][] getAccounts() {return this.constructMat(membros.getAll(), this::cadast2arr);}

    public String[][] getListaMusicas(){
        return constructMat(conteudos.getAllConts(), this::content2arr);
    }

    public String[][] getListaMusicasBasic(){
        return constructMat(conteudos.getAllConts(), this::basic_content2arr);
    }

    public String[][] getListaMusicas(String idCat){
        return constructMat(conteudos.getWithCategoria(idCat), this::content2arr);
    }

    public String[][] getListaMusicas(int idPlaylist){
        return constructMat(new ArrayList<>(playlists.get(idPlaylist).getContents().values()), this::content2arr);
    }

    public String[][] getAllConteudoBasic(int idPlaylist) {
        List<Conteudo> playContent = new ArrayList<>(playlists.get(idPlaylist).getContents().values());
        return this.constructMat(playContent, this::basic_content2arr);
    }

    public String[][] getAllAlbuns() {
        return this.constructMat(albuns.getAll(), this::album2arr);
    }

    public String[] getAmigos(String idConta) {
        Utilizador u = (Utilizador)membros.get(idConta);
        return this.colltoarr(new ArrayList<>(u.getAmigos()));
    }

    public String[] getPedidos(String idConta) {
        Utilizador u = (Utilizador)membros.get(idConta);
        return this.colltoarr(new ArrayList<>(u.getPedidos()));
    }

    public String[][] getPlaylist(String idConta) {
        List<Playlist> plays = playlists.getAllFrom(idConta);
        return this.constructMat(plays, this::playlist2arr);
    }

    private void sendMail(String email, String password) throws EmailException {
        String subject = "[MediaCenter] Nova password.";
        String msg = "A sua nova password é " + password;

        try {
            mailer.sendMessage(email, subject, msg);
        } catch (MessagingException me) {
            throw new EmailException("Ocorreu um erro ao enviar o email!");
        }
    }

    private boolean isWeakPassword(String pass) {
        return false;
    }

    private Cadastrado getUser(String id) throws UtilizadorInexistenteException {
        if (!membros.contains(id))
            throw new UtilizadorInexistenteException("Não existe um membro com o username \"" + id + "\"!");
        return membros.get(id);
    }

    private Categoria reassureCategory(String idCat) {
        Categoria ca;

        if (categorias.contains(idCat))
            ca = categorias.get(idCat);
        else {
            ca = new Categoria(idCat);
            categorias.put(idCat, ca);
        }

        return ca;
    }

    // Playlists
    private Playlist randomPlaylist(String nome, String desc) {
        Playlist pl = new Playlist(nome, desc);
        List<Integer> ls = new ArrayList<>(conteudos.getAll());
        int li = ls.size() < MAX_RAND_SONGS ? ls.size() : MAX_RAND_SONGS;

        shuffle(ls);
        ls = ls.subList(0, li);

        pl.addContents(conteudos.getAll(ls));

        return pl;
    }

    private Playlist newCatPlaylist(String nome, String desc, String cat) {
        Playlist pl = new Playlist(nome, desc);
        List<Conteudo> ls = conteudos.getWithCategoria(cat);
        int li = ls.size() < MAX_RAND_SONGS ? ls.size() : MAX_RAND_SONGS;

        shuffle(ls);
        ls = ls.subList(0, li);

        pl.addContents(ls);

        return pl;
    }

    private Playlist artistPlaylist(String nome, String desc, String artista) {
        Playlist pl = new Playlist(nome, desc);
        List<Conteudo> ls = conteudos.getWithArtist(artista);
        int li = ls.size() < MAX_RAND_SONGS ? ls.size() : MAX_RAND_SONGS;

        shuffle(ls);
        ls = ls.subList(0, li);

        pl.addContents(ls);

        return pl;
    }

    private Playlist newPlaylist(String nome, String desc) {
        return new Playlist(nome, desc);
    }

    private void addPlaylist(String idConta, Playlist pl) {
        Utilizador u = (Utilizador) membros.get(idConta);

        u.addPlaylist(pl);

        membros.put(idConta, u);
        playlists.put(pl.getId(), pl);
    }

    private String[] playlist2arr(Object o) {
        Playlist l = (Playlist)o;
        return new String[]{l.getNome(), Integer.toString(l.getId())};
    }

    private String[] content2arr(Object o) {
        Conteudo c = (Conteudo)o;
        return new String[]{c.getNome(), c.getAutor(), Util.convertDuration(c.getDuracao()), "+","...",Integer.toString(c.getId()),c.getPath()};
    }

    private String[] cadast2arr(Object o) {
        Cadastrado c = (Cadastrado)o;
        return new String[]{c.getUsername(), c.getEmail(), "Remove"};
    }

    private String[] basic_content2arr(Object o) {
        Conteudo c = (Conteudo)o;
        return new String[]{c.getNome(), c.getAutor(), Integer.toString(c.getId())};
    }

    private String[] album2arr(Object o) {
        Album al = (Album)o;
        return new String[]{Integer.toString(al.getID()), al.getNome()};
    }

    private String[][] constructMat(List<? extends Object> objs, ArrMaker am) {
        String[][] arr = new String[objs.size()][];

        for(int i = 0; i < objs.size(); i++)
            arr[i] = am.toArr(objs.get(i));

        return arr;
    }

    private String[] colltoarr(List<String> l) {
        String[] s = new String[l.size()];

        for(int i = 0; i < l.size(); i++) {
            s[i] = l.get(i);
        }

        return s;
    }

}
