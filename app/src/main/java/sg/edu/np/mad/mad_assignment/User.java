package sg.edu.np.mad.mad_assignment;

public class User {
    private String password;
    private String email;
    private String nickname;

    public User(){}

    public User(String pass, String e, String nick){
        password = pass;
        email = e;
        nickname = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
