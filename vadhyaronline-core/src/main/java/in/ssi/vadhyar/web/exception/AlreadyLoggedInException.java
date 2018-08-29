package in.ssi.vadhyar.web.exception;

public class AlreadyLoggedInException extends RuntimeException {

    public AlreadyLoggedInException(String message){
        super(message);
    }
}
