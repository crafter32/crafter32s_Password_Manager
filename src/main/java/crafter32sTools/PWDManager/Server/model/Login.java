package crafter32sTools.PWDManager.Server.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "login", orphanRemoval = true)
    private List<LoginParam> loginParams = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LoginParam> getLoginParams() {
        return loginParams;
    }

    public void setLoginParams(List<LoginParam> loginParams) {
        this.loginParams = loginParams;
    }

    public static final class LoginBuilder {
        private UUID id;
        private User user;
        private List<LoginParam> loginParams;

        private LoginBuilder() {
        }

        public static LoginBuilder aLogin() {
            return new LoginBuilder();
        }

        public LoginBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public LoginBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public LoginBuilder withLoginParams(List<LoginParam> loginParams) {
            this.loginParams = loginParams;
            return this;
        }

        public Login build() {
            Login login = new Login();
            login.user = this.user;
            login.id = this.id;
            login.loginParams = this.loginParams;
            return login;
        }
    }
}