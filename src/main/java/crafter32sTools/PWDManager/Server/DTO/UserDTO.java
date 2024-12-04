package crafter32sTools.PWDManager.Server.DTO;

import crafter32sTools.PWDManager.Server.model.Login;
import crafter32sTools.PWDManager.Server.model.User;

import java.util.List;
import java.util.UUID;

public record UserDTO
        (
                UUID id,
                String username,
                String email,
                String password,
                List<Login> login
        )
{
    public User toUser(){
        return User.UserBuilder.anUser()
                .withId(id)
                .withUserName(username)
                .withEmail(email)
                .withLogins(login)
                .withPassword(password)
                .build();
    }

    public static UserDTO fromUser(User user){
        return new UserDTO(user.getId(), user.getUserName(), user.getEmail(), user.getPassword(), user.getLogins());
    }
}