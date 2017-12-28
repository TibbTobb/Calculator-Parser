package lexer;

public class InvalidInputException extends Exception {
    public final int index;
    public InvalidInputException(int index) {
        this.index = index;
    }
}
