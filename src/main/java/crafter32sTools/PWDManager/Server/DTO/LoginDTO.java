package crafter32sTools.PWDManager.Server.DTO;

import crafter32sTools.PWDManager.Server.model.Login;
import crafter32sTools.PWDManager.Server.model.LoginParam;
import crafter32sTools.PWDManager.Server.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoginDTO
{
    private final UUID id;
    private final List<LoginParam> loginParams;
    private final LocalDateTime timestamp;

    @Autowired
    private UserRepository repository;

    public LoginDTO(UUID id, List<LoginParam> loginParams, LocalDateTime timestamp) {
        this.id = id;
        this.loginParams = loginParams;
        this.timestamp = timestamp;
    }

    public Login toLogin(){
        return Login.LoginBuilder.aLogin()
                .withId(id)
                .withUser(repository.findByLogins_Id(id))
                .withLoginParams(loginParams)
                .withLastDateOfChange(timestamp)
                .build();
    }

    public static LoginDTO fromLogin(Login login){
        return new LoginDTO(login.getId(), login.getLoginParams(), login.getLastDateOfChange());
    }

    public static List<LoginDTO> fromList(List<Login> logins){
        List<LoginDTO> res = new ArrayList<>();
        for(var x : logins){
            res.add(fromLogin(x));
        }
        return res;
    }

    public static List<Login> toList(List<LoginDTO> DTOs){
        List<Login> res = new ArrayList<>();
        for(var x : DTOs){
            res.add(x.toLogin());
        }
        return res;
    }

    public UUID getId() {
        return id;
    }

    public List<LoginParam> getLoginParams() {
        return loginParams;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
