package Server;

import Exceptions.PasswordIncorretaException;
import Exceptions.UtilizadorInexistenteException;
import MediaCenterSystem.MediaCenter;

import java.io.*;
import java.net.Socket;

public class ServerWorker implements Runnable {
    private Socket socket;
    private MediaCenter md;

    public ServerWorker(Socket s, MediaCenter mediacenter) {
        this.socket = s;
        this.md = mediacenter;
    }

    public void run() {
        System.out.println("> New client has established connection from " + socket.getRemoteSocketAddress());

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

                    case "login":
                        try {
                            this.md.login(ops[1], ops[2]);
                            out.println("ok");
                        } catch (PasswordIncorretaException p) {
                            out.println("wrongpassword");
                        } catch (UtilizadorInexistenteException u) {
                            out.println("usernexists");
                        } finally {
                            out.flush();
                        }

                        System.out.println("> Login from "+ops[1]);
                        break;

                    case "logout":
                        this.md.logout();
                        System.out.println("> Logout");
                        break;

                    case "upload":
                        this.md.upload(ops[1],ops[2],ops[3],ops[4],ops[5]);
                        System.out.println("> Uploaded file from "+ops[1]);
                        break;

                    case "download":
                            this.md.download(Integer.parseInt(ops[1]));
                            System.out.println("> Downloading file");
                            //File ficheiro = new File(this.md.getPath(Integer.parseInt(ops[2])));
                            File ficheiro = new File("src/Server/media/08 - All The Small Things.flac");
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
                        //File ficheiro = new File(this.md.getPath(Integer.parseInt(ops[2])));
                        File ficheirolocal = new File("src/Server/media/08 - All The Small Things.flac");
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

                    case "permission":
                        System.out.println("> Checking permissions of "+ops[2]+" for "+ops[1]);
                        boolean r;// = this.md.checkPermissions(ops[1],Integer.parseInt(ops[2]));
                        r = true;
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
                        }
                        break;

                    case "getListaMusicas":
                        String[] c = data.split("_");
                        if (c.length == 1 || c[1].equals("All")) {
                            System.out.println("> List all");
                            String[][] a = this.md.getListaMusicas();
                            out.println(a.length);
                            out.flush();
                            for (int i = 0; i < a.length; i++)
                                for (int j = 0; j < 6; j++) {
                                    out.println(a[i][j]);
                                    out.flush();
                                }
                        } else if (c.length == 2){
                            System.out.println("> List all that is "+c[1]);
                            String[][] a = this.md.getListaMusicas(c[1]);
                            out.println(a.length);
                            out.flush();
                            for (int i = 0; i < a.length; i++)
                                for (int j = 0; j < 6; j++) {
                                    out.println(a[i][j]);
                                    out.flush();
                                }
                        }
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
}
