package crafter32sTools.PWDManager.Server.service.Exceptions;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException{
    HttpStatus status;

    public UserException(String message, HttpStatus status) {
        super(message + " " + status.toString());
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
