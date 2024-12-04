package crafter32sTools.PWDManager.Server.DTO;

import crafter32sTools.PWDManager.Server.model.Login;
import crafter32sTools.PWDManager.Server.model.LoginParam;

import java.util.List;
import java.util.UUID;

public record LoginDTO
        (
                UUID id,
                UserDTO userDTO,
                List<LoginParam> loginParams
        )
{
    public Login toLogin(){
        return Login.LoginBuilder.aLogin()
                .withId(id)
                .withUser(userDTO.toUser())
                .withLoginParams(loginParams)
                .build();
    }

    public static LoginDTO fromLogin(Login login){
        return new LoginDTO(login.getId(), UserDTO.fromUser(login.getUser()), login.getLoginParams());
    }
}
