package crafter32sTools.PWDManager.Server.DTO;

import crafter32sTools.PWDManager.Server.model.LoginParam;

import java.util.UUID;

public record LoginParamDTO
        (
                UUID id,
                String name,
                String value,
                LoginDTO loginDTO
        )
{
    public LoginParam toLoginParam(){
        return LoginParam.LoginParamBuilder.aLoginParam()
                .withId(id)
                .withLogin(loginDTO.toLogin())
                .withName(name)
                .withValue(value)
                .build();
    }

    public static LoginParamDTO fromLoginParam(LoginParam loginParam){
        return new LoginParamDTO(loginParam.getId(), loginParam.getName(), loginParam.getValue(), LoginDTO.fromLogin(loginParam.getLogin()));
    }
}
