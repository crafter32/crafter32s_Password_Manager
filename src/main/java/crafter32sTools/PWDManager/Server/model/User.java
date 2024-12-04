package crafter32sTools.PWDManager.Server.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "u_user")
@EntityListeners(EncDecListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    private UUID id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Login> logins = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Login> getLogins() {
        return logins;
    }

    public void setLogins(List<Login> logins) {
        this.logins = logins;
    }

    public static final class UserBuilder {
        private UUID id;
        private String userName;
        private String email;
        private String password;
        private List<Login> logins;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public UserBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withLogins(List<Login> logins) {
            this.logins = logins;
            return this;
        }

        public User build() {
            User user = new User();
            user.logins = this.logins;
            user.id = this.id;
            user.userName = this.userName;
            user.email = this.email;
            user.password = this.password;
            return user;
        }
    }
}

