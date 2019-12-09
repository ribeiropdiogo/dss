package MediaCenterSystem.BusinessLogic.Cadastrado;

import Exceptions.PasswordIncorretaException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
abstract
public class Cadastrado {

    private String username;
    private String email;
    private String password;

    public Cadastrado(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void alteraEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void alteraPass(String pOld, String pNew, String pNewC) throws PasswordIncorretaException {

        if(this.password.equals(pOld)){
            this.password = pNew;
        }else throw new PasswordIncorretaException("Password atual não igual");
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public Set<Integer> getContentList() {
        return new HashSet<>();
    }

    public boolean checkMail(String email){

        return this.email.equals(email);
    }

}
