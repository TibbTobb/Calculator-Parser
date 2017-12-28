package parser;

import lexer.Token;


public class StackItem {
    public final int state;
    public final Token symbol;
    public final ParseTreeNode node;

    public StackItem(int state, Token symbol, ParseTreeNode node) {
        this.state = state;
        this.symbol = symbol;
        this.node = node;
    }

}
