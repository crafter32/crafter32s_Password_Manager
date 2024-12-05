package crafter32sTools.PWDManager.Server.service;

import crafter32sTools.PWDManager.Server.DTO.UserDTO;
import crafter32sTools.PWDManager.Server.model.User;
import crafter32sTools.PWDManager.Server.repository.UserRepository;
import crafter32sTools.PWDManager.Server.service.Exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    protected User getUserByEmailOrUsername(String EmailOrUsername){
        User res = repository.getByUserNameOrEmail(EmailOrUsername);
        if(res == null) throw new UserException("User not found!", HttpStatus.NOT_FOUND);
        return res;
    }

    protected boolean UserEqualsUser(User user, User user2){
        return repository.getByUserNameOrEmail(user.getEmail()) == repository.getByUserNameOrEmail(user2.getEmail());
    }
    protected boolean UserEqualsUser(UserDTO user, UserDTO user2){
        return UserEqualsUser(user.toUser(), user2.toUser());
    }
}
