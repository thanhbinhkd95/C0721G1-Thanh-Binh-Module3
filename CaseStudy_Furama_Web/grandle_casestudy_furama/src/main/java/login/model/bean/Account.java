package login.model.bean;

public class Account {
    public String username;
    public String password;
    public int idRole;

    public Account() {
    }

    public Account(String username, String password, int idRole) {
        this.username = username;
        this.password = password;
        this.idRole = idRole;
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

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
