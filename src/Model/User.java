package Model;

import Model.Enum.Role;

import java.io.Serializable;


public class User implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private String id;
    private String username;
    private String password;
    private String name;
    private Role role;

    public User() {

    }

    public User(String id, String username, String password, String name, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
