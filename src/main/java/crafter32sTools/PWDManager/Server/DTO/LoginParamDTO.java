package crafter32sTools.PWDManager.Server.DTO;

import crafter32sTools.PWDManager.Server.model.LoginParam;
import crafter32sTools.PWDManager.Server.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class LoginParamDTO
{
    private UUID id;
    private String name;
    private String value;

    @Autowired
    private LoginRepository repository;

    public LoginParam toLoginParam(){
        return LoginParam.LoginParamBuilder.aLoginParam()
                .withId(id)
                .withLogin(repository.findByLoginParams_Id(id))
                .withName(name)
                .withValue(value)
                .build();
    }

    public static LoginParamDTO fromLoginParam(LoginParam loginParam){
        return new LoginParamDTO(loginParam.getId(), loginParam.getName(), loginParam.getValue());
    }

    public LoginParamDTO(UUID id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
