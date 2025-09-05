package EduAtlas.demo.exceptions;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(long id) {
        super("l'elemento con id "+id+" non è stato trovato");
    }
    public NotFoundExceptions(String email) {
        super("l'elemento con email "+email+" non è stato trovato");
    }

}
