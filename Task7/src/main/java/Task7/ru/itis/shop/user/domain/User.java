package Task7.ru.itis.shop.user.domain;

public class User {
    private Integer id;
    private String firstName;
    private String email;
    private String password;
    private String profileDescription;

    public User(Integer id, String name, String email, String password, String profileDescription) {
        this.id = id;
        this.email = email.trim();
        this.firstName = name.trim();
        this.password = password.trim();
        this.profileDescription = profileDescription.trim();
    }

    public User(String name, String email, String password, String profileDescription) {
        this.email = email.trim();
        this.firstName = name.trim();
        this.password = password.trim();
        this.profileDescription = profileDescription.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                '}';
    }
}
