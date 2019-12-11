package MediaCenterSystem.BusinessLogic;

import MediaCenterSystem.BusinessLogic.Cadastrado.Utilizador;

import java.util.HashMap;
import java.util.Map;

public class Conteudo {

    private int id;
    private String nome;
    private Long tamanho;
    private Double duracao;
    private String autor;
    private String path;
    private HashMap<String, Categoria> categorias;
    private HashMap<String, Utilizador> donos;

    private Conteudo(int id, String nome, Long tamanho, Double duracao, String autor, String path) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.duracao = duracao;
        this.autor = autor;
        this.path = path;
        this.donos = new HashMap<>();
        this.categorias = new HashMap<>();
    }

    public static Conteudo getInstance(int id, String nome, Long tamanho, Double duracao, String autor, String path) {
        return new Conteudo(id, nome, tamanho, duracao, autor, path);
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

    public double getDuracao() {
        return duracao;
    }

    public String getPath() {
        return this.path;
    }

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
