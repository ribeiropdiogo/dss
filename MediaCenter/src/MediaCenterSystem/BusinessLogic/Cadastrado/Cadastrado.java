package MediaCenterSystem.BusinessLogic.Cadastrado;

import MediaCenterSystem.BusinessLogic.PasswordIncorretaException;

public class Cadastrado {

    private Long id;
    private String username;
    private String email;
    private String password;

    public Cadastrado(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
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

            if(pNew.equals(pNewC)) {
                this.password = pNew;
            }else throw new PasswordIncorretaException("Novas passwords não são iguais");

        }else throw new PasswordIncorretaException("Password atual não igual");
    }

    public boolean checkPassword(String password){

        return this.password.equals(password);
    }

    public boolean checkMail(String email){

        return this.email.equals(email);
    }

}
