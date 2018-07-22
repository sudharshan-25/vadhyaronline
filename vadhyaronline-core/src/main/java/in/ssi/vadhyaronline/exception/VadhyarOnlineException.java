package in.ssi.vadhyaronline.exception;

public class VadhyarOnlineException extends Exception {

    public VadhyarOnlineException(String message) {
        super(message);
    }

    public VadhyarOnlineException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
