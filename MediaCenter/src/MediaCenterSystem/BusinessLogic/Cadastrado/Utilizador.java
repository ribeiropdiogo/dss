package MediaCenterSystem.BusinessLogic.Cadastrado;

import Exceptions.AmizadeException;
import Exceptions.PasswordIncorretaException;
import MediaCenterSystem.BusinessLogic.Playlist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utilizador extends Cadastrado{

    private Set<Long> contents;
    private Set<Long> playlists;
    private Set<String> amigos;
    private Set<String> pedidos;

    public Utilizador(String id, String username, String email, String password) {
        super(id, username, email, password);
        contents = new HashSet<>();
        playlists = new HashSet<>();
        amigos = new HashSet<>();
        pedidos = new HashSet<>();
    }


    public void removeContent(Long idContent){

        this.contents.remove(idContent);

    }

    public void removePedido(String idAmigo){
        amigos.remove(idAmigo);
    }

    public void addContent(Long cid){
        this.contents.add(cid);
    }

    public List<Long> getPlaylists() {

        List<Long> ret = new ArrayList<>(playlists);

        return ret;
    }

    public void setPlaylists(Set<Long> playlists) {
        this.playlists = playlists;
    }

    public void removePlaylist(Long idPlaylist){

        this.playlists.remove(idPlaylist);
    }

    public void addPlaylist(Playlist pl){

        this.playlists.add(pl.getId());
    }

    /* CRIAR PLAYLIST falta o put */


    public boolean temAmigo(String idAmigo){

        return this.amigos.contains(idAmigo);
    }

    public void removeAmigo(String idAmigo){
        this.amigos.remove(idAmigo);
    }

    public void respondePedido(String idAmigo, boolean resp){

        if(resp){
            this.amigos.add(idAmigo);
        }
        this.pedidos.remove(idAmigo);

    }


    /*nova execao?*/
    public void formConvite(String idConta) throws AmizadeException {

        if(!this.pedidos.contains(idConta)){
            this.pedidos.add(idConta);
        }else throw new AmizadeException( "O utilizador " + super.getId() + " já foi convidado, aguarde resposta!");

    }

    public boolean checkOwnership(Long idContent){

        return(this.contents.contains(idContent));
        //EXCEPITON AQUI? OU NO MEDIA CENTER?
    }




}
