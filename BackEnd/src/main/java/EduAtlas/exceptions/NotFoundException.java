package EduAtlas.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("l'elemento con id "+id+" non è stato trovato");
    }
    public NotFoundException(String email) {
        super("l'elemento con email "+email+" non è stato trovato");
    }

}
