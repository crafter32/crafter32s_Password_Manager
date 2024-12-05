package crafter32sTools.PWDManager.Server.DTO;

import crafter32sTools.PWDManager.Server.model.Login;
import crafter32sTools.PWDManager.Server.model.LoginParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record LoginDTO
        (
                UUID id,
                UserDTO userDTO,
                List<LoginParam> loginParams,
                LocalDateTime timestamp
        )
{
    public Login toLogin(){
        return Login.LoginBuilder.aLogin()
                .withId(id)
                .withUser(userDTO.toUser())
                .withLoginParams(loginParams)
                .withLastDateOfChange(timestamp)
                .build();
    }

    public static LoginDTO fromLogin(Login login){
        return new LoginDTO(login.getId(), UserDTO.fromUser(login.getUser()), login.getLoginParams(), login.getLastDateOfChange());
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
}
