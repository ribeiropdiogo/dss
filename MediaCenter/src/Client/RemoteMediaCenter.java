package Client;

import Exceptions.*;
import MediaCenter_GUI.*;
import Utilities.Par;

import javax.print.attribute.standard.Media;
import java.io.*;
import java.lang.SecurityException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RemoteMediaCenter implements MediaCenterInterface {
    private Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public RemoteMediaCenter(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        System.out.println("> Connected to " + socket.getRemoteSocketAddress());
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

    public void login(String username, String password) throws PasswordIncorretaException, UtilizadorInexistenteException {
        out.println("login " + username + " " + password);
        out.flush();
        try {
            String r = in.readLine();
            if (r.equals("wrongpassword")) {
                //MessageDialog md = new MessageDialog("Error", "Incorrect Password");
                throw new PasswordIncorretaException("Incorrect Password");
            } else if (r.equals("usernexists")) {
                //MessageDialog md = new MessageDialog("Error", "Username does not exist");
                throw new UtilizadorInexistenteException("User not found");
            } else if (r.equals("okuser")) {
                MainView md = new MainView(this, username);
            } else if (r.equals("okadmin")) {
                AdminViewForm md = new AdminViewForm(this);
            }
        } catch (IOException e) {
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

    public String[] getCategorias(long idContent) {
        out.println("categorias "+idContent);
        out.flush();
        String[] categorias = null;

        try {
            String answer = in.readLine();
            String[] ops = answer.split("_");
            int c = ops.length;
            c--;
            categorias = new String[c];
            for (int i = 0; i < c; i++){
                categorias[i] = ops[i+1];
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return categorias;
    }

    public String[] getCategorias() {
        out.println("categorias");
        out.flush();
        String[] categorias = null;

        try {
            String answer = in.readLine();
            String[] ops = answer.split("_");
            int c = ops.length;
            c--;
            categorias = new String[c];
            for (int i = 0; i < c; i++){
                categorias[i] = ops[i+1];
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return categorias;
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
        out.println("permission "+idConta+" "+idContent);
        out.flush();

        try {
            if (in.readLine().equals("true"));
                return true;
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public void download(long idContent) {
        out.println("download " + idContent);
        out.flush();

        try {
            String[] resposta = in.readLine().split(" ");
            StringBuilder builder = new StringBuilder();
            for (int i = 2; i < resposta.length; i++) {
                builder.append(resposta[i]);
                if (i < resposta.length - 1)
                    builder.append(" ");

            }
            String filename = builder.toString();

            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                FileOutputStream fos = new FileOutputStream("src/Client/media/" + filename);
                int filesize = Integer.parseInt(resposta[1]);
                byte[] buffer = new byte[filesize];

                int read = 0;
                int totalRead = 0;
                int remaining = filesize;
                while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                    totalRead += read;
                    remaining -= read;
                    //System.out.println("read " + totalRead + " bytes.");
                    fos.write(buffer, 0, read);
                }
                fos.close();
                //dis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
        }




    }

    public String downloadForReproduction(long idContent) {
        out.println("rep_download " + idContent);
        out.flush();

        String filename = null;

        try {
            String[] resposta = in.readLine().split(" ");
            StringBuilder builder = new StringBuilder();
            for (int i = 2; i < resposta.length; i++) {
                builder.append(resposta[i]);
                if (i < resposta.length - 1)
                    builder.append(" ");

            }
            filename = builder.toString();

            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                FileOutputStream fos = new FileOutputStream("src/Client/.reproduction/" + filename);
                int filesize = Integer.parseInt(resposta[1]);
                byte[] buffer = new byte[filesize];

                int read = 0;
                int totalRead = 0;
                int remaining = filesize;
                while ((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
                    totalRead += read;
                    remaining -= read;
                    //System.out.println("read " + totalRead + " bytes.");
                    fos.write(buffer, 0, read);
                }
                fos.close();
                //dis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return filename;
    }


    public void uploadFile(String idConta, String nome, String autor, String album, File ficheiro) {

        out.println("uploadFile " + ficheiro.length() + " " + ficheiro.getName());
        out.flush();

        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream(ficheiro);
            byte[] buffer = new byte[(int) ficheiro.length()];

            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }

            fis.close();
            //dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        this.upload(idConta,nome,autor,album,"src/Server/media/"+ficheiro.getName());
    }

    public void upload(String idConta, String nome, String autor, String album, String path) {
        out.println("upload _"+idConta+"_"+nome+"_"+autor+"_"+path+"_"+album);
        out.flush();
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

    public String[][] getAccounts() {
        out.println("getListUsers");
        out.flush();
        return this.getOp(3);
    }

    public String[][] getListaMusicas() {
        out.println("getListaMusicas");
        out.flush();
        return this.getOp(6);
    }

    public String[][] getListaMusicas(String idCat){
        out.println("getListaMusicas _"+idCat);
        out.flush();
        return this.getOp(6);
    }

    private String[][] getOp(int ncol) {
        String[][] r = null;
        try {
            int rows = Integer.parseInt(in.readLine());
            System.out.println("> Fetched "+rows+" rows");
            r = new String[rows][ncol];
            for (int i = 0; i < rows; i++){
                for (int j = 0; j < ncol; j++){
                    r[i][j] = in.readLine();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return r;
    }
}
