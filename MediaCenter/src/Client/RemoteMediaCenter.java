package Client;

import Exceptions.*;
import MediaCenter_GUI.GuestView;
import MediaCenter_GUI.LoginForm;
import MediaCenter_GUI.MainView;
import MediaCenter_GUI.MessageDialog;
import Utilities.Par;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.SecurityException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Set;

public class RemoteMediaCenter implements MediaCenterInterface{
    private Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public RemoteMediaCenter(String host, int port) throws IOException {
        this.socket = new Socket(host,port);
        System.out.println("> Connected to "+ socket.getRemoteSocketAddress());
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream());
    }

    public void loginGuest() {
        out.println("loginGuest");
        out.flush();
        GuestView mv = new GuestView(this);
    }

    public void logout() {
        out.println("logout");
        out.flush();

        LoginForm l = new LoginForm(this);
    }

    public void login(String username, String password) throws PasswordIncorretaException, UtilizadorInexistenteException{
        out.println("login "+username+" "+password);
        out.flush();
        try {
            String r = in.readLine();
            if(r.equals("wrongpassword")){
                //MessageDialog md = new MessageDialog("Error", "Incorrect Password");
                throw new PasswordIncorretaException("Incorrect Password");
            } else if (r.equals("usernexists")){
                //MessageDialog md = new MessageDialog("Error", "Username does not exist");
                throw new UtilizadorInexistenteException("User not found");
            } else if (r.equals("ok")){
                MainView md = new MainView(this);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void removeContent(long idPlaylist, long idContent) {

    }

    public void addAlbum(long idPlaylist, long idAlbum) {

    }

    public void removerAmigo(String idConta, String idAmigo) {

    }

    public void respondePedido(String idConta, String idAmigo, boolean resp) {

    }

    public void formConvite(String idConta, String idAmigo) throws UtilizadorInexistenteException, AmizadeException {

    }

    public void removeContent(String idConta, long idContent) {

    }

    public void alteraPass(String idConta, String pOld, String pNew, String pNewC) {

    }

    public void alteraEmail(String idConta, String newMl) {

    }

    public Set<String> getCategorias(long idContent) {
        return null;
    }

    public void adicionarCategoria(long idContent, String idCat) {

    }

    public void alterarCategoria(long idContent, String oldCat, String newCat) {

    }

    public void removerCategoria(long idContent, String idCat) {

    }

    public String getPath(long idContent) {
        return null;
    }

    public void randomPlaylist(String idConta, String nome, String desc) {

    }

    public void newPlaylist(String idConta, String nome, String desc, String cat) {

    }

    public void artistPlaylist(String idConta, String nome, String desc, String artista) {

    }

    public void newPlaylist(String idConta, String nome, String desc) {

    }

    public List<Par<Long, String>> getPlaylists(String idConta) {
        return null;
    }

    public void removePlaylist(String idConta, long idPlaylist) {

    }

    public void editPName(long idPlaylist, String pNome) {

    }

    public void editPDesc(long idPlaylist, String pDesc) {

    }

    public void adicionaPlaylist(long idPlaylist, long idContent) throws ConteudoRepetidoException {

    }

    public boolean checkPermissions(String idConta, long idContent) {
        return false;
    }

    public void download(long idContent) {

    }

    public void upload(String idConta, String nome, String autor, String album, String path) {

    }

    public void newConta(String tipo, String idConta, String email, String password) throws UtilizadorRepetidoException {

    }

    public void removeAccount(String idConta) {

    }

    public void forgottenPassword(String username, String email) throws UtilizadorInexistenteException, SecurityException {

    }



    public String generatePassword() {
        return null;
    }

    public void sendMail(String email, String password) {

    }
}
