package crafter32sTools.PWDManager.Server.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "login_param")
public class LoginParam {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "v_value", nullable = false)
    private String value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "login_id", nullable = false)
    private Login login;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }


    public static final class LoginParamBuilder {
        private UUID id;
        private String name;
        private String value;
        private Login login;

        private LoginParamBuilder() {
        }

        public static LoginParamBuilder aLoginParam() {
            return new LoginParamBuilder();
        }

        public LoginParamBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public LoginParamBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public LoginParamBuilder withValue(String value) {
            this.value = value;
            return this;
        }

        public LoginParamBuilder withLogin(Login login) {
            this.login = login;
            return this;
        }

        public LoginParam build() {
            LoginParam loginParam = new LoginParam();
            loginParam.setName(name);
            loginParam.setValue(value);
            loginParam.setLogin(login);
            loginParam.id = this.id;
            return loginParam;
        }
    }
}