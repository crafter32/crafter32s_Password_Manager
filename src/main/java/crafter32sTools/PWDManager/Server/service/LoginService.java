package crafter32sTools.PWDManager.Server.service;

import crafter32sTools.PWDManager.Server.DTO.LoginDTO;
import crafter32sTools.PWDManager.Server.DTO.UserDTO;
import crafter32sTools.PWDManager.Server.repository.LoginRepository;
import crafter32sTools.PWDManager.Server.service.Exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private LoginRepository repository;
    @Autowired
    private UserService userService;

    public ResponseEntity<List<LoginDTO>> getCurrentLogins(UserDTO user){
        try{
            return new ResponseEntity<>(LoginDTO.fromList(repository.getByUserOrderByLast_date_of_changeDesc(user.toUser())), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> deleteLogin(UserDTO userDTO, LoginDTO login){
        String body = "Login deleted successfully!";
        HttpStatus status = HttpStatus.OK;
        try{
            if(!userService.UserEqualsUser(userDTO, login.userDTO())) throw new UserException("User does not own Login!", HttpStatus.UNAUTHORIZED);
            repository.delete(login.toLogin());
        }
        catch (UserException e){
            body = e.getMessage();
            status = e.getStatus();
        }
        return new ResponseEntity<>(body, status);
    }

    public ResponseEntity<LoginDTO> createLogin(LoginDTO loginDTO){
        userService.getUserByEmailOrUsername(loginDTO.userDTO().username());
        return new ResponseEntity<>(LoginDTO.fromLogin(repository.save(loginDTO.toLogin())), HttpStatus.CREATED);
    }
}
