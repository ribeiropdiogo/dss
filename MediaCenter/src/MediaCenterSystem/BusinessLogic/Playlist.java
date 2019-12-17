package MediaCenterSystem.BusinessLogic;

import Exceptions.ConteudoRepetidoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Playlist {

    private static int nextID = 0;

    private int id;
    private String nome;
    private String descricao;
    private Map<Integer, Conteudo> conteudos;

    public static void setCurrentNextID(int nextID) {
        Playlist.nextID = nextID;
    }

    public static Playlist getInstance(int id, String nome, String descricao) {
        return new Playlist(id, nome, descricao);
    }

    private Playlist(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.conteudos = new HashMap<>();
    }

    public Playlist(String nome, String descricao) {
        this(nextID++,nome,descricao);
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

    public void removeContent(int idContent) {
        this.conteudos.remove(idContent);
    }

    public void addContent(int idContent, Conteudo cn) {
        this.conteudos.put(idContent, cn);
    }

    public void addDefContent(int idContent, Conteudo cn) {
        conteudos.put(idContent, cn);
    }

    public void addContents(List<Conteudo> cVetor) {

        for (Conteudo c : cVetor) {
            this.conteudos.put(c.getId(), c);
        }
    }

    public Map<Integer, Conteudo> getContents() {
        return new HashMap<>(conteudos);
    }
}
