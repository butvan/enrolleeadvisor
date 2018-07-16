package enrolleeadvisor.controller.dataprovider;

public class DataProviderException extends Exception {
    public DataProviderException(String message) {
        super(message);
    }

    public DataProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataProviderException(Throwable cause) {
        super(cause);
    }
}
