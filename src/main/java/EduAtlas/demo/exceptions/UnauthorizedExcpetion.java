package EduAtlas.demo.exceptions;

public class UnauthorizedExcpetion extends RuntimeException {
    public UnauthorizedExcpetion() {
        super("credenziali errate");
    }
}
