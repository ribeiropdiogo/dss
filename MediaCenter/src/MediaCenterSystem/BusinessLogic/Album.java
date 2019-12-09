package MediaCenterSystem.BusinessLogic;

import java.util.*;

public class Album {

    private static int nextID = 0;

    private int id;
    private String nome;
    private Map<Integer,Conteudo> conteudos;

    public static void setCurrentNextID(int nextID) {
        Album.nextID = nextID;
    }

    public Album(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Album(int id,String nome, Map<Integer,Conteudo> conts){
        this.id = id;
        this.nome = nome;
        conteudos = conts;
    }

    public Album(String nome) {
        this.id = ++nextID;
        this.nome = nome;
        conteudos = new HashMap<>();
    }

    public int getID() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Set<Integer> getContents(){
        return new HashSet<>(conteudos.keySet());
    }

}
