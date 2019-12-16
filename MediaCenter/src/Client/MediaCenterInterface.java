package Client;

import Exceptions.*;
import Utilities.Par;

import java.io.File;
import java.lang.SecurityException;
import java.util.List;
import java.util.Set;

public interface MediaCenterInterface {
    void loginGuest();

    void logout();

    void removeContent(long idPlaylist, long idContent);

    void addAlbum(long idPlaylist, long idAlbum);

    void removerAmigo(String idConta, String idAmigo);

    void respondePedido(String idConta, String idAmigo, boolean resp);

    void formConvite(String idConta, String idAmigo) throws UtilizadorInexistenteException, AmizadeException;

    void removeContent(String idConta, long idContent);

    void alteraPass(String idConta, String pOld, String pNew, String pNewC);

    void alteraEmail(String idConta, String newMl);

    String[][] getAccounts();

    String[] getCategorias(long idContent);

    String[] getCategorias();

    void adicionarCategoria(long idContent, String idCat);

    void alterarCategoria(long idContent, String oldCat, String newCat);

    void removerCategoria(long idContent, String idCat);

    String getPath(long idContent);

    void randomPlaylist(String idConta, String nome, String desc);

    void newPlaylist(String idConta, String nome, String desc, String cat);

    void artistPlaylist(String idConta, String nome, String desc, String artista);

    void newPlaylist(String idConta, String nome, String desc);

    List<Par<Long, String>> getPlaylists(String idConta);

    void removePlaylist(String idConta, long idPlaylist);

    void editPName(long idPlaylist, String pNome);

    void editPDesc(long idPlaylist, String pDesc);

    void adicionaPlaylist(long idPlaylist, long idContent) throws ConteudoRepetidoException;

    boolean checkPermissions(String idConta, long idContent);

    void download(long idContent);

    public String downloadForReproduction(long idContent);

    void uploadFile(String idConta, String nome, String autor, String album, File file);

    void upload(String idConta, String nome, String autor, String album, String path);

    void newConta(String tipo, String idConta, String email, String password) throws UtilizadorRepetidoException;

    void removeAccount(String idConta);

    void forgottenPassword(String username, String email) throws UtilizadorInexistenteException, SecurityException, EmailException;

    void login(String username, String password) throws PasswordIncorretaException, UtilizadorInexistenteException;

    String[][] getListaMusicas();

    String[][] getListaMusicas(String idCat);

    String[][] getListaMusicas(int idPlaylist);

    String[][] getAllConteudoBasic(int idPlaylist);

    String[][] getAllAlbuns();

}
