package dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "users")
public class UserProfile implements Serializable { // Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    private String password;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UserProfile() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UserProfile(long id, String login) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(login);
    }

    public UserProfile(String login) {
        this.setId(-1);
        this.setLogin(login);
        this.setPassword(login);
    }

    public UserProfile(String login,String password) {
        this.setId(-1);
        this.setLogin(login);
        this.setPassword(password);
    }


    @SuppressWarnings("UnusedDeclaration")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}