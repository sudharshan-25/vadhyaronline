package in.ssi.vadhyar.web.exception;

public class VadhyarOnlineException extends RuntimeException {

    public VadhyarOnlineException(String message) {
        super(message);
    }

    public VadhyarOnlineException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
