package Task4.ru.itis.shop.user.domain;

public class User {
    private String id;
    private String firstName;
    private String email;
    private String password;
    private String profileDescription;

    public User(String id, String firstName, String email, String password, String profileDescription) {
        this.id = id;
        this.firstName = firstName.trim();
        this.email = email.trim();
        this.password = password.trim();
        this.profileDescription = profileDescription.trim();
    }

    public User(String firstName, String email, String password, String profileDescription) {
        this.firstName = firstName.trim();
        this.email = email.trim();
        this.password = password.trim();
        this.profileDescription = profileDescription.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {return firstName;}

    public void setFirstName(String name) {
        this.firstName = name;
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
}
