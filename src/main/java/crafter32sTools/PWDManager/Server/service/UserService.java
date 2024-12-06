package crafter32sTools.PWDManager.Server.service;

import crafter32sTools.PWDManager.Server.DTO.UserDTO;
import crafter32sTools.PWDManager.Server.model.User;
import crafter32sTools.PWDManager.Server.repository.UserRepository;
import crafter32sTools.PWDManager.Server.service.Exceptions.UserException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private LoginService loginService;

    public UserDTO getUserByEmailOrUsername(String EmailOrUsername){
        User res = repository.getByUserNameOrEmail(EmailOrUsername);
        if(res == null) throw new UserException("User not found!", HttpStatus.NOT_FOUND);
        return UserDTO.fromUser(res);
    }

    protected boolean UserEqualsUser(User user, User user2){
        return repository.getByUserNameOrEmail(user.getEmail()) == repository.getByUserNameOrEmail(user2.getEmail());
    }
    protected boolean UserEqualsUser(UserDTO user, UserDTO user2){
        return UserEqualsUser(user.toUser(), user2.toUser());
    }

    public ResponseEntity<UserDTO> save(UserDTO userDTO) throws Exception {
        if(userDTO.username().isBlank()) throw new UserException("Username is empty!", HttpStatus.NOT_FOUND);
        if(userDTO.email().isBlank()) throw new UserException("Email is empty!", HttpStatus.NOT_FOUND);
        if(userDTO.password().isBlank()) throw new UserException("Password is empty!", HttpStatus.NOT_FOUND);
        if(getUserByEmailOrUsername(userDTO.username()) != null) throw new UserException("Username already taken!", HttpStatus.IM_USED);
        if(getUserByEmailOrUsername(userDTO.email()) != null) throw new UserException("An account is already linked to this email!", HttpStatus.IM_USED);

        return new ResponseEntity<>(UserDTO.fromUser(repository.save(encryptUser(userDTO).toUser())), HttpStatus.CREATED);
    }

    private UserDTO encryptUser(UserDTO userDTO) throws Exception {
        UserDTO encrypted = userDTO;
        if(encrypted.id() == null) encrypted = UserDTO.fromUser(repository.save(encrypted.toUser()));
        return new UserDTO(
                encrypted.id(),
                EncryptionHandler.encrypt(encrypted.username(), encrypted.id().toString()),
                EncryptionHandler.encrypt(encrypted.email(), encrypted.id().toString()),
                EncryptionHandler.hashPassword(encrypted.password()),
                encrypted.login()
        );
    }
}
