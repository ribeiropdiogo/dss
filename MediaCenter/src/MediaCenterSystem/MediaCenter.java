package MediaCenterSystem;

import Exceptions.AmizadeException;
import Exceptions.PasswordFracaException;
import Exceptions.PasswordIncorretaException;
import Exceptions.UtilizadorInexistenteException;
import MediaCenterSystem.BusinessLogic.Album;
import MediaCenterSystem.BusinessLogic.Cadastrado.Cadastrado;
import MediaCenterSystem.BusinessLogic.Cadastrado.Utilizador;
import MediaCenterSystem.BusinessLogic.Conteudo;
import MediaCenterSystem.BusinessLogic.Playlist;
import MediaCenterSystem.DataAccess.*;

import java.util.List;
import java.util.Set;

public class MediaCenter {

    private CategoriaDAO categorias;
    private AlbumDAO albuns;
    private PlaylistDAO playlists;
    private CadastradoDAO membros;
    private ConteudoDAO conteudos;

    public void loginGuest(){}

    public void logout(){}

    public void removeContent(long idPlaylist, long idContent){
        Playlist pl = playlists.get(idPlaylist);

        pl.removeContent(idContent);

        playlists.put(idPlaylist, pl);
    }

    public void addAlbum(long idPlaylist, long idAlbum){
        Album al = albuns.get(idAlbum);
        Set<Long> sngs = al.getContents();
        Playlist pl = playlists.get(idPlaylist);
        List<Conteudo> cvector = conteudos.getAll(sngs);

        pl.addContents(cvector);

        playlists.put(idPlaylist, pl);
    }

    public void removerAmigo(String idConta, String idAmigo){
        Utilizador conta = (Utilizador)membros.get(idConta);

        conta.removeAmigo(idAmigo);

        membros.put(idConta, conta);
    }

    public void respondePedido(String idConta, String idAmigo, boolean resp){
        Utilizador conta = (Utilizador)membros.get(idConta);
        Utilizador amigo = (Utilizador)membros.get(idAmigo);

        conta.respondePedido(idAmigo, resp);

        amigo.removePedido(idAmigo);

        membros.put(idConta, conta);
        membros.put(idAmigo, amigo);
    }

    public void formConvite(String idConta, String idAmigo) throws UtilizadorInexistenteException, AmizadeException {

        if(!membros.contains(idAmigo))
            throw new UtilizadorInexistenteException("O utilizador " + idAmigo + " não existe no sistema!");

        Utilizador am;
        Utilizador c = (Utilizador)membros.get(idConta);

        if(c.temAmigo(idAmigo))
            throw new AmizadeException("O utilizador " + idAmigo + " já é seu amigo!");

        am = (Utilizador)membros.get(idAmigo);
        am.formConvite(idConta);

        membros.put(idAmigo, am);
    }

    public void removeContent(String idConta, long idContent){
        int sz;
        Conteudo con;
        List<Playlist> plays;
        Utilizador c = (Utilizador)membros.get(idConta);

        c.removeContent(idContent);

        membros.put(idConta, c);

        con = conteudos.get(idContent);

        sz = con.removeDono(idConta);

        if(sz == 0) {
            plays = playlists.getAllWith(idContent);

            for(Playlist pl : plays) {
                pl.removeContent(idContent);
            }

            playlists.put(plays);
            conteudos.remove(idContent);
        }
    }

    public void alteraPass(String idConta, String pOld, String pNew, String pNewC)
                throws PasswordFracaException, PasswordIncorretaException {

        if(!pNew.equals(pNewC))
            throw new PasswordIncorretaException("A confirmação da password está incorreta!");

        if(isWeakPassword(pNew))
            throw new PasswordFracaException("A sua password é demasiado fraca, deve colocar números e letras!");

        Cadastrado c = membros.get(idConta);

        c.alteraPass(pOld,pNew,pNewC);

        membros.put(idConta, c);
    }

    public void alteraEmail(String idConta, String newMl){
        Cadastrado c = membros.get(idConta);

        c.alteraEmail(newMl);

        membros.put(idConta, c);
    }

    public Set<String> getCategorias(long idContent){return null;}

    public void adicionarCategoria(long idContent, String idCat){}

    public void alterarCategoria(long idContent, String oldCat, String newCat){}

    public void removerCategoria(long idContent, String idCat){}

    public String getPath(String idContent){return null;}

    public void randomPlaylist(String idConta, String nome, String desc){}

    public void newPlaylist(String idConta, String nome, String desc, String cat){}

    public void artistPlaylist(String idConta, String nome, String desc, String artista){}

    public void newPlaylist(String idConta, String nome, String desc){}

    public void getPlaylists(String idConta){}

    public void removePlaylist(String idConta, long idPlaylist){}

    public void editPName(long idPlaylist, String pNome){}

    public void editPDesc(long idPlaylist, String pDesc){}

    public void adicionaPlaylist(long idPlaylist, long idContent){}

    public void checkPermissions(long idConta, long idContent){}

    public void download(long idContent){}

    public void upload(String idConta, String nome, String autor, String album, String path){}

    public void newConta(String tipo, String idConta, String email, String password){}

    public void removeAccount(String idConta){}

    public void forgottenPassword(String username, String email){}

    public void login(String username, String password) throws PasswordIncorretaException, UtilizadorInexistenteException{
        //para efeitos de teste
        throw new PasswordIncorretaException("");
        //---------------------
    }

    private boolean isWeakPassword(String pass) {
        return true;
    }

}
