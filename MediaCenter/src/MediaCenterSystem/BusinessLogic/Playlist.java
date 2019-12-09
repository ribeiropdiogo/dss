package MediaCenterSystem.BusinessLogic;

import Exceptions.ConteudoRepetidoException;

import java.util.*;

public class Playlist {

    private int id;
    private String nome;
    private String descricao;
    private Map<Integer,Conteudo> conteudos;


    public Playlist(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
        this.conteudos = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void removeContent(int idContent){
        this.conteudos.remove(idContent);
    }

    public void addContent(int idContent, Conteudo cn) throws ConteudoRepetidoException {

        if (this.conteudos.containsKey(idContent)) {
            this.conteudos.put(idContent, cn);
        } else throw new ConteudoRepetidoException("Conteudo Repetido");
    }

    public void addContents(List<Conteudo> cVetor){

        for(Conteudo c : cVetor){
            this.conteudos.put(c.getId(),c);
        }
    }
}
