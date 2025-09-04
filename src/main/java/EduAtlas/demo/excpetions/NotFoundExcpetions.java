package EduAtlas.demo.excpetions;

public class NotFoundExcpetions extends RuntimeException {
    public NotFoundExcpetions(long id) {
        super("l'elemento con id "+id+" non è stato trovato");
    }
}
