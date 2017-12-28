package parser;

import lexer.Token;

public class Reduce implements Action {
    int length;
    Token reduction;
    @Override
    public void run(Parser p) {
        p.reduce(length, reduction);
    }

    public Reduce(int length, Token reduction) {
        this.length = length;
        this.reduction = reduction;
    }
}
