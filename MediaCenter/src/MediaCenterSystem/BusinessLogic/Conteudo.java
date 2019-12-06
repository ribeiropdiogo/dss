package MediaCenterSystem.BusinessLogic;

import Exceptions.CategoriaJaExistenteException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Conteudo {

    private Long id;
    private String nome;
    private Long tamanho;
    private Double duracao;
    private String autor;
    private String path;
    private Set<Long> donos;
    private HashMap<String,Categoria> categorias;

    Conteudo(Long id, String nome, Long tamanho, Double duracao, String autor, String path){
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.duracao = duracao;
        this.autor = autor;
        this.path = path;
        this.donos = new HashSet<>();
        this.categorias = new HashMap<>();
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void remove(Long idConta){

        this.donos.remove(idConta);

    }

    public String getPath(){
        return this.path;
    }

    public void setPath(String path){
        this.path = path;
    }


    public void adicionaCategoria(String idCat, Categoria ca) throws CategoriaJaExistenteException {

        if(!categorias.containsKey(idCat)){

            categorias.put(idCat,ca);

        }else throw new CategoriaJaExistenteException("Categoria Já existe");
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

    public HashMap<String,Categoria> getCategorias(){

        Map<String,Categoria> ret = new HashMap<>(categorias);

        return (HashMap<String, Categoria>) ret;
    }

}
