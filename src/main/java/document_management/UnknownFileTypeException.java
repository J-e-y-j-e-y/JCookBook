package document_management;

public class UnknownFileTypeException extends RuntimeException{
    public UnknownFileTypeException(String message) {
        super(message);
    }
}
