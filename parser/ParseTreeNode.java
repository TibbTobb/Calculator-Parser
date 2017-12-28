package parser;

import lexer.Num;
import lexer.Tag;
import lexer.Token;

import java.util.*;


public class ParseTreeNode {
    public ParseTreeNode parent;
    public List<ParseTreeNode> childList = new ArrayList<ParseTreeNode>();
    public Token token;

    public ParseTreeNode(Token token) {
        this.token = token;
    }
    public void print() {
        System.out.println();
        print("", true);
    }
    public void print(String prefix, boolean isTail) {
        //print parse tree
        System.out.print(prefix + (isTail ? "|-- " : "|-- "));
        if(token.tag == Tag.NUM) {
            System.out.println(((Num)token).mantissa + "e" + ((Num)token).exponent);
        } else {
            System.out.println(token.tag.toString());
        }

        for (int i = 0; i < childList.size() - 1; i++) {
            childList.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if (childList.size() > 0) {
            childList.get(childList.size() - 1)
                    .print(prefix + (isTail ?"    " : "|   "), true);
        }
    }

}
