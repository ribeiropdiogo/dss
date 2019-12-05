package MediaCenterSystem;

import java.util.Set;

public class MediaCenter {

    public void loginGuest(){}

    public void logout(){}

    public void removeContent(long idPlaylist, long idContent){}

    public void addAlbum(long idPlaylist, long idAlbum){}

    public void removerAmigo(String idConta, String idAmigo){}

    public void respondePedido(String idConta, String idAmigo, boolean resp){}

    public void formConvite(String idConta, String idAmigo){}

    public void removeContent(String idConta, long idContent){}

    public void alteraPass(String idConta, String pOld, String pNew, String pNewC){}

    public void alteraEmail(String idConta, String newMl){}

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

    public void login(String username, String password){}

}
