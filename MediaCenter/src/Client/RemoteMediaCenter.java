package Client;

import Exceptions.*;
import MediaCenter_GUI.*;
import Utilities.Par;

import javax.print.attribute.standard.Media;
import java.io.*;
import java.lang.SecurityException;
import java.net.Socket;
import java.security.Security;
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

    public void addAlbum(int idPlaylist, int idAlbum) {
        out.println("addAlbum " + idPlaylist + " " + idAlbum);
        out.flush();
    }

    public void removerAmigo(String idConta, String idAmigo) {

    }

    public void respondePedido(String idConta, String idAmigo, boolean resp) {

    }

    public void formConvite(String idConta, String idAmigo) throws UtilizadorInexistenteException, AmizadeException {

    }

    public void removeContent(String idConta, int idContent) {
        out.println("removeContentUser " + idConta + " " + idContent);
        out.flush();
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
        out.println("addCategoria "+idContent+" "+idCat);
        out.flush();
        System.out.println("> Add category "+idCat+" to content "+idContent);
    }

    public void alterarCategoria(long idContent, String oldCat, String newCat) {
        out.println("alterarCategoria "+idContent+" "+oldCat+" "+newCat);
        out.flush();
        System.out.println("> Updating category from content "+idContent);
    }

    public void removerCategoria(long idContent, String idCat) {
        out.println("removeCategoria "+idContent+" "+idCat);
        out.flush();
        System.out.println("> Remove category "+idCat+" from content "+idContent);
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
            if (in.readLine().equals("true"))
                return true;
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public void download(long idContent) {
        out.println("download " + idContent);
        out.flush();

        System.out.println("> Downloading file");
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
        out.println("newConta " + tipo + " " + idConta + " " + email + " " + password);
        out.flush();
        MessageDialog md;
        try {
            String r = in.readLine();
            if(r.equals("userrep"))
                throw new UtilizadorRepetidoException("There is already a user with that username!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeAccount(String idConta) {
        out.println("removeAccount " + idConta);
        out.flush();
    }

    public void forgottenPassword(String username, String email) throws UtilizadorInexistenteException, SecurityException, EmailException {
        String code = "forgotPass "+username+" "+email;
        out.println(code);
        out.flush();
        MessageDialog md;
        try {
            String r = in.readLine();
            switch(r) {
                case "emailsent":
                    //md = new MessageDialog("Success", "A new password has been sent to your email sucessfully!");
                    break;
                case "usernexists":
                    //md = new MessageDialog("Error", "User doesn't exist in our system!");
                    throw new UtilizadorInexistenteException("User doesn't exist in our system!");
                case "wrongmail":
                    //md = new MessageDialog("Error", "Email doesn't match user's real email!");
                    throw new SecurityException("Email doesn't match user's real email!");
                case "mailerror":
                    //md = new MessageDialog("Error", "An error has occurred whilst trying to send your email!");
                    throw new EmailException("An error has occurred whilst trying to send your email!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String[][] getListaMusicas(int idPlaylist) {
        out.println("getListaMusicasPlay _"+idPlaylist);
        out.flush();
        return this.getOp(6);
    }

    public String[][] getAllConteudoBasic(int idPlaylist) {
        out.println("getBasicPlCont _" +  idPlaylist);
        out.flush();
        return this.getOp(4);
    }

    public String[][] getAllAlbuns() {
        out.println("getAllAlbuns");
        out.flush();
        return this.getOp(2);
    }

    public String[] getAmigos(String idConta) {
        out.println("getAmigosUser " + idConta);
        out.flush();
        return getVector();
    }

    public String[] getPedidos(String idConta) {
        out.println("getPedidosUser " +  idConta);
        out.flush();
        return getVector();
    }

    public String[][] getPlaylist(String idConta) {
        out.println("getPlaylistUser " + idConta);
        out.flush();
        return getOp(2);
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

    private String[] getVector() {
        String[] r = null;
        try {
            int size = Integer.parseInt(in.readLine());
            System.out.println("> Fetched " + size + " elements!");
            r = new String[size];

            for(int i = 0; i < size; i++) {
                r[i] = in.readLine();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return r;
    }


}
