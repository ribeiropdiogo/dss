package Server;

import Exceptions.*;
import MediaCenterSystem.MediaCenter;

import java.io.*;
import java.lang.SecurityException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Set;

public class ServerWorker implements Runnable {
    private Socket socket;
    private MediaCenter md;

    public ServerWorker(Socket s, MediaCenter mediacenter) {
        this.socket = s;
        this.md = mediacenter;
    }

    public void run() {
        System.out.println("> New client has established connection from " + socket.getRemoteSocketAddress());
        String[][] a;
        String[] vec;
        String[] parametros;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String data = in.readLine();

            while (data != null && !data.equals("exit")) {
                String[] ops = data.split(" ");

                switch (ops[0]) {
                    case "loginGuest":
                        this.md.loginGuest();
                        System.out.println("> Login as Guest");
                        break;
                    case "removeContentUser":
                        this.md.removeContent(ops[1], Integer.parseInt(ops[2]));
                        break;
                    case "removeAccount":
                        this.md.removeAccount(ops[1]);
                        System.out.println("> Account " + ops[1] + " removed");
                        break;
                    case "forgotPass":
                        try {
                            this.md.forgottenPassword(ops[1],ops[2]);
                            out.println("emailsent");
                        } catch(UtilizadorInexistenteException e) {
                            out.println("usernexists");
                        } catch (SecurityException e) {
                            out.println("wrongmail");
                        } catch (EmailException e) {
                            out.println("mailerror");
                        } finally {
                            out.flush();
                        }
                        break;
                    case "login":
                        try {
                            boolean isu = this.md.login(ops[1], ops[2]);
                            if(isu)
                                out.println("okuser");
                            else
                                out.println("okadmin");
                        } catch (PasswordIncorretaException p) {
                            out.println("wrongpassword");
                        } catch (UtilizadorInexistenteException u) {
                            out.println("usernexists");
                        } finally {
                            out.flush();
                        }

                        System.out.println("> Login from "+ops[1]);
                        break;
                    case "alteraPass":
                        try {
                            this.md.alteraPass(ops[1],ops[2],ops[3],ops[4]);
                            out.println("allok");
                        } catch (PasswordIncorretaException exc) {
                            out.println("nopassmatch");
                        } catch (PasswordFracaException exc) {
                            out.println("weakpass");
                        } finally {
                            out.flush();
                        }
                        break;
                    case "alteraEmail":
                        this.md.alteraEmail(ops[1],ops[2]);
                        break;
                    case "logout":
                        this.md.logout();
                        System.out.println("> Logout");
                        break;

                    case "upload":
                        parametros = data.split("_");
                        this.md.upload(parametros[1],parametros[2],1,1,parametros[3],parametros[4],parametros[5]);
                        //System.out.println("> Uploaded file from "+ops[1]);
                        break;
                    case "randomPlaylist":
                        parametros = data.split("_");
                        this.md.randomPlaylist(parametros[1],parametros[2],parametros[3]);
                        break;
                    case "newPlaylistCat":
                        parametros = data.split("_");
                        this.md.newPlaylist(parametros[1],parametros[2],parametros[3],parametros[4]);
                        break;
                    case "artistPlaylist":
                        parametros = data.split("_");
                        this.md.artistPlaylist(parametros[1],parametros[2],parametros[3],parametros[4]);
                        break;
                    case "newPlaylist":
                        parametros = data.split("_");
                        this.md.newPlaylist(parametros[1],parametros[2],parametros[3]);
                        break;
                    case "download":
                            this.md.download(Integer.parseInt(ops[1]));
                            System.out.println("> Downloading file");
                            File ficheiro = new File(this.md.getPath(Integer.parseInt(ops[1])));
                            //File ficheiro = new File("src/Server/media/08 - All The Small Things.flac");
                            out.println("download "+ficheiro.length()+" "+ficheiro.getName());
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
                        break;

                    case "rep_download":
                        System.out.println("> Downloading file for local reproduction");
                        File ficheirolocal = new File(this.md.getPath(Integer.parseInt(ops[1])));
                        //System.out.println(this.md.getPath(Integer.parseInt(ops[1])));
                        //File ficheirolocal = new File("src/Server/media/08 - All The Small Things.flac");
                        out.println("local_download "+ficheirolocal.length()+" "+ficheirolocal.getName());
                        out.flush();

                        try {
                            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                            FileInputStream fis = new FileInputStream(ficheirolocal);
                            byte[] buffer = new byte[(int) ficheirolocal.length()];

                            while (fis.read(buffer) > 0) {
                                dos.write(buffer);
                            }

                            fis.close();
                            //dos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "newConta":
                        if(ops.length == 5) {
                            try {
                                this.md.newConta(ops[1],ops[2],ops[3],ops[4]);
                                out.println("allok");
                            } catch (UtilizadorRepetidoException ex) {
                                out.println("userrep");
                            } finally {
                                out.flush();
                            }
                        }
                        break;
                    case "removerAmigo":
                        this.md.removerAmigo(ops[1], ops[2]);
                        break;
                    case "respondePedido":
                        this.md.respondePedido(ops[1], ops[2], Boolean.parseBoolean(ops[3]));
                        break;
                    case "formConvite":
                        try {
                            this.md.formConvite(ops[1],ops[2]);
                            out.println("allok");
                        } catch(UtilizadorInexistenteException e) {
                            out.println("usernexists");
                        } catch (AmizadeException e) {
                            out.println("existrequest");
                        } finally {
                            out.flush();
                        }
                        break;
                    case "permission":
                        System.out.println("> Checking permissions of "+ops[2]+" for "+ops[1]);
                        boolean r = this.md.checkPermissions(ops[1],Integer.parseInt(ops[2]));
                        //r = true;
                        if (r)
                            out.println("true");
                        else
                            out.println("false");

                        out.flush();
                        break;

                    case "uploadFile":
                        StringBuilder builder = new StringBuilder();
                        for (int i = 2; i < ops.length; i++) {
                            builder.append(ops[i]);
                            if (i < ops.length - 1)
                                builder.append(" ");

                        }
                        String filename = builder.toString();


                        System.out.println("> Uploading file " + filename);
                        try {
                            DataInputStream dis = new DataInputStream(socket.getInputStream());
                            FileOutputStream fos = new FileOutputStream("src/Server/media/" + filename);
                            int filesize = Integer.parseInt(ops[1]);
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

                        break;

                    case "categorias":
                        if (ops.length==1){
                            String[] cats = this.md.getCategorias();
                            StringBuilder build = new StringBuilder();
                            build.append("categorias_");
                            for (int i = 0; i < cats.length; i++) {
                                build.append(cats[i]);
                                if (i < cats.length - 1)
                                    build.append("_");

                            }
                            String answer = build.toString();

                            out.println(answer);
                            out.flush();
                        } else if (ops.length == 2 ){
                            Set<String> s = this.md.getCategorias(Integer.parseInt(ops[1]));
                            String[] cats = Arrays.copyOf(s.toArray(), s.size(), String[].class);
                            StringBuilder build = new StringBuilder();
                            build.append("categorias_");
                            for (int i = 0; i < cats.length; i++) {
                                build.append(cats[i]);
                                if (i < cats.length - 1)
                                    build.append("_");

                            }
                            String answer = build.toString();

                            out.println(answer);
                            out.flush();
                        }
                        break;

                    case "getListaMusicas":
                        String[] c = data.split("_");
                        if (c.length == 1 || c[1].equals("All")) {
                            System.out.println("> List all");
                            a = this.md.getListaMusicas();
                            out.println(a.length);
                            this.pushMatrix(out, a, a.length, 6);
                        } else if (c.length == 2){
                            System.out.println("> List all that is "+c[1]);
                            a = this.md.getListaMusicas(c[1]);
                            out.println(a.length);
                            this.pushMatrix(out, a, a.length, 6);
                        }
                        break;
                    case "getAmigosUser":
                        System.out.print("> List friends of " + ops[1]);
                        vec = this.md.getAmigos(ops[1]);
                        out.println(vec.length);
                        this.pushVec(out, vec);
                        break;
                    case "getPedidosUser":
                        System.out.print("> List requests of " + ops[1]);
                        vec = this.md.getPedidos(ops[1]);
                        out.println(vec.length);
                        this.pushVec(out, vec);
                        break;
                    case "getPlaylistUser":
                        System.out.println("> List playslists of "+ ops[1]);
                        a = this.md.getPlaylist(ops[1]);
                        out.println(a.length);
                        this.pushMatrix(out, a, a.length, 2);
                        break;
                    case "getListUsers":
                        System.out.println("> List all users");
                        a = this.md.getAccounts();
                        out.println(a.length);
                        this.pushMatrix(out, a, a.length, 3);
                        break;

                    case "getListaMusicasPlay":
                        String[] d = data.split("_");
                        if(d.length == 2) {
                            System.out.println("> List all content from " + d[1]);
                            a = this.md.getListaMusicas(Integer.parseInt(d[1]));
                            out.println(a.length);
                            this.pushMatrix(out, a, a.length, 6);
                        }
                        break;
                    case "getListMusicasBasic":
                        a = this.md.getListaMusicasBasic();
                        out.println(a.length);
                        this.pushMatrix(out, a, a.length, 3);
                        break;
                    case "getBasicPlCont":
                        String[] ee = data.split("_");
                        if(ee.length == 2) {
                            System.out.println("> List all basic content from " + ee[1]);
                            a = this.md.getAllConteudoBasic(Integer.parseInt(ee[1]));
                            out.println(a.length);
                            this.pushMatrix(out, a, a.length, 3);
                        }
                        break;
                    case "removePlaylist":
                        this.md.removePlaylist(ops[1], Integer.parseInt(ops[2]));
                        break;
                    case "editPName":
                        vec = data.split("_");
                        this.md.editPName(Integer.parseInt(vec[1]),vec[2]);
                        break;
                    case "editPDesc":
                        vec = data.split("_");
                        this.md.editPDesc(Integer.parseInt(vec[1]),vec[2]);
                        break;
                    case "adicionaPlaylistCont":
                        this.md.adicionaPlaylist(Integer.parseInt(ops[1]),Integer.parseInt(ops[2]));
                        break;
                    case "removeContentPlay":
                        this.md.removeContent(Integer.parseInt(ops[1]),Integer.parseInt(ops[2]));
                        break;
                    case "getAllAlbuns":
                        System.out.println("> List all albuns");
                        a = this.md.getAllAlbuns();
                        out.println(a.length);
                        this.pushMatrix(out, a, a.length, 2);
                        break;
                    case "addAlbum":
                        System.out.println("> Adding album to playlist");
                        this.md.addAlbum(Integer.parseInt(ops[1]), Integer.parseInt(ops[2]));
                        break;
                    case "addCategoria":
                        System.out.println("> Adding new category to file");
                        md.adicionarCategoria(Integer.parseInt(ops[1]),ops[2]);
                        break;
                    case "removeCategoria":
                        System.out.println("> Removing category from file");
                        md.removerCategoria(Integer.parseInt(ops[1]),ops[2]);
                        break;

                    case "alterarCategoria":
                        System.out.println("> Changing category from file");
                        md.alterarCategoria(Integer.parseInt(ops[1]),ops[2],ops[3]);
                        break;
                    default:
                        System.out.println("> Unrecognized operation");
                        break;
                }
                data = in.readLine();
            }

            socket.close();
            out.flush();
            System.out.println("> Connection ended");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pushMatrix(PrintWriter out, String[][] a, int nrow, int ncol) {
        out.flush();
        for (int i = 0; i < nrow; i++)
            for (int j = 0; j < ncol; j++) {
                out.println(a[i][j]);
                out.flush();
            }
    }

    private void pushVec(PrintWriter out, String[] a) {
        out.flush();
        String[][] tmp = new String[1][];
        tmp[0] = a;
        this.pushMatrix(out, tmp, 1, a.length);
    }
}
