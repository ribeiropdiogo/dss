package MediaCenterSystem.BusinessLogic;

import MediaCenterSystem.BusinessLogic.Cadastrado.Utilizador;

import java.util.HashMap;
import java.util.Map;

public class Conteudo {

    private static int nextID = 0;

    private int id;
    private String nome;
    private Long tamanho;
    private Long duracao;
    private String autor;
    private String path;
    private int album;
    private HashMap<String, Categoria> categorias;
    private HashMap<String, Utilizador> donos;

    public static void setCurrentNextID(int nextID) {
        Conteudo.nextID = nextID;
    }

    public static Conteudo getInstance(int id, String nome, Long tamanho, Long duracao, String autor, String path, int album) {
        return new Conteudo(id, nome, tamanho, duracao, autor, path, album);
    }

    private Conteudo(int id, String nome, Long tamanho, Long duracao, String autor, String path, int album) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.duracao = duracao;
        this.autor = autor;
        this.path = path;
        this.album = album;
        this.donos = new HashMap<>();
        this.categorias = new HashMap<>();
    }

    public Conteudo(String nome, Long tamanho, Long duracao, String autor, String path, int album) {
        this(nextID++,nome,tamanho,duracao,autor,path,album);
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public long getTamanho() {
        return this.tamanho;
    }

    public String getAutor() {
        return this.autor;
    }

    public int removeDono(String idConta) {

        this.donos.remove(idConta);
        return donos.size();

    }

    public long getDuracao() {
        return duracao;
    }

    public String getPath() {
        return this.path;
    }

    public int getAlbum() {return album;}

    public void adicionaCategoria(String idCat, Categoria ca) {
        categorias.put(idCat, ca);
    }

    public void adicionaDono(String idConta, Utilizador u) {
        donos.put(idConta, u);
    }

    public void alteraCategoria(String oldCat, String newCat, Categoria ca) {

        categorias.remove(oldCat);
        categorias.put(newCat, ca);

    }

    public void eliminaCategoria(String idCat) {

        categorias.remove(idCat);
    }

    public int size() {
        return this.donos.size();
    }

    public Map<String, Categoria> getCategorias() {
        return new HashMap<>(categorias);
    }

    public Map<String, Utilizador> getDonos() {
        return new HashMap<>(donos);
    }

}
