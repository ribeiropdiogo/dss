package MediaCenterSystem.BusinessLogic;

import java.util.*;

public class Album {

    private Long id;
    private String nome;
    private HashMap<Long,Conteudo> conteudos;

    Album(Long id,String nome){
        this.id = id;
        this.nome = nome;
        conteudos = new HashMap<>();
    }

    public Set<Long> getContents(){

        Set<Long> ret = new HashSet<>(conteudos.keySet());

        return ret;
    }

}
