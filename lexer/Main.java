package lexer;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InvalidInputException {
        Lexer lexer = new Lexer();
        List<Token> tokenList = lexer.lex(args[0]);
        for(Token t : tokenList) {
            System.out.println(t.tag);
        }
    }
}
