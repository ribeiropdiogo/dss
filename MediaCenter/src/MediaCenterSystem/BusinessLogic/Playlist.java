package MediaCenterSystem.BusinessLogic;

import java.util.*;

public class Playlist {

    private Long id;
    private String nome;
    private String descricao;
    private Map<Long,Conteudo> conteudos;


    Playlist(Long id, String nome, String descricao){

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.conteudos = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void removeContent(Long idContent){
        this.conteudos.remove(idContent);
    }

    public void addContent(Long idContent, Conteudo cn) throws ConteudoNaoEncontradoException {

        if (this.conteudos.containsKey(idContent)) {
            this.conteudos.put(idContent, cn);
        } else throw new ConteudoNaoEncontradoException("Conteudo Repetido");
    }

    public void addContents(List<Conteudo> cVetor){

        for(Conteudo c : cVetor){
            this.conteudos.put(c.getId(),c);
        }
    }
}
