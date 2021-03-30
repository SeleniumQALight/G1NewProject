package api;

public class AuthorDTO {
    private String username;
    private String avatar;

    public AuthorDTO(String username){
        this.username = username;
    }

    //this empty constructor is for RestAssured to be able to map params automatically
    public AuthorDTO(){
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
