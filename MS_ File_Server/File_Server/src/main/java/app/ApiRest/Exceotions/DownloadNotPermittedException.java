package app.ApiRest.Exceotions;

public class DownloadNotPermittedException extends RuntimeException {

    public DownloadNotPermittedException() {
        super("Erro ao efetuar o download do arquivo");
    }

    public DownloadNotPermittedException(String message) {
        super(message);
    }

}
