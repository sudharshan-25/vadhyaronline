package in.ssi.vadhyaronline.web;

import in.ssi.vadhyaronline.exception.AlreadyLoggedInException;
import in.ssi.vadhyaronline.exception.NoAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VOControllerAdvice {

    @ExceptionHandler(AlreadyLoggedInException.class)
    public ResponseEntity<String> handleLoggedInException(AlreadyLoggedInException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoAccessException.class)
    public ResponseEntity<String> handleAccessException(NoAccessException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

}
