package MediaCenterSystem.BusinessLogic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Album {

    private static int nextID = 0;

    private int id;
    private String nome;
    private Map<Integer, Conteudo> conteudos;

    public static void setCurrentNextID(int nextID) {
        Album.nextID = nextID;
    }

    private Album(int id, String nome) {
        this.id = id;
        this.nome = nome;
        conteudos = new HashMap<>();
    }

    public Album(String nome) {
        this(nextID++,nome);
    }

    public static Album getInstance(int id, String nome) {
        return new Album(id, nome);
    }

    public void addContuedo(int idConteudo, Conteudo c) {
        conteudos.put(idConteudo, c);
    }

    public int getID() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Set<Integer> getContents() {
        return new HashSet<>(conteudos.keySet());
    }

    public Map<Integer, Conteudo> getMapContents() {
        return new HashMap<>(conteudos);
    }

}
