package app.ApiRest.Exceotions;

public class NullDirectoryException extends RuntimeException {

    public NullDirectoryException() {
        super("Direrório não encontrado");
    }

    public NullDirectoryException(String message) {
        super(message);
    }

}
