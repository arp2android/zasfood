package zasfood.ec.edu.espol.zasfood;

import java.util.LinkedList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private List<String> retos;

    public User() {
        //vacio
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.retos = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<String> getRetos() {
        return retos;
    }

    public void setRetos(List<String> retos) {
        this.retos = retos;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", retos=" + retos +
                '}';
    }
}
