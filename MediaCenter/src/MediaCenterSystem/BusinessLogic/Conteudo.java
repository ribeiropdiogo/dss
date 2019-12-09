package MediaCenterSystem.BusinessLogic;

import Exceptions.CategoriaJaExistenteException;
import MediaCenterSystem.BusinessLogic.Cadastrado.Utilizador;
import jdk.jshell.execution.Util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Conteudo {

    private int id;
    private String nome;
    private Long tamanho;
    private Double duracao;
    private String autor;
    private String path;
    private HashMap<String, Categoria> categorias;
    private HashMap<String, Utilizador> donos;

    Conteudo(int id, String nome, Long tamanho, Double duracao, String autor, String path){
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.duracao = duracao;
        this.autor = autor;
        this.path = path;
        this.donos = new HashMap<>();
        this.categorias = new HashMap<>();
    }

    public int getId(){
        return this.id;
    }

    public int removeDono(String idConta){

        this.donos.remove(idConta);
        return donos.size();

    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path){
        this.path = path;
    }


    public void adicionaCategoria(String idCat, Categoria ca) {
        categorias.put(idCat,ca);
    }

    public void alteraCategoria(String oldCat, String newCat,Categoria ca){

         categorias.remove(oldCat);
         categorias.put(newCat,ca);

    }

    public void eliminaCategoria(String idCat){

        categorias.remove(idCat);
    }

    public int size(){
        return this.donos.size();
    }

    public Set<String> getCategorias(){
        return new HashSet<>();
    }

}
