package parser;

import lexer.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Parser {
    //SLR Parser
    private int[][] gotoTable;
    private Action[][] actionTable;
    private Stack<StackItem> stack;
    private int position;
    private List<Token> tokenList;
    private boolean accept;
    private ParseTreeNode root;

    public Parser(int[][] gotoTable, Action[][] actionTable) {
        this.gotoTable = gotoTable;
        this.actionTable = actionTable;
    }

    public ParseTreeNode parse(List<Token> tl) {
        tokenList = tl;
        accept = false;
        position = 0;
        stack = new Stack<StackItem>();
        stack.push(new StackItem(0, null, null));
        while(!accept) {
            //get and run action from actionTable
            actionTable[stack.peek().state][tokenList.get(position).tag.ordinal()].run(this);
        }
        return root;
    }

    public void shift(int state) {
       stack.push(new StackItem(state, tokenList.get(position), new ParseTreeNode(tokenList.get(position))));
       position++;
    }
    public void reduce(int length, Token reduction) {
        List<ParseTreeNode> reductionList = new ArrayList<ParseTreeNode>();
        ParseTreeNode parent = new ParseTreeNode(reduction);
        root = parent;
        for(int i=0; i<length; i++) {
            reductionList.add(stack.pop().node);
        }

        //Print reduction step
        System.out.print(reduction.tag.toString() + " ->");
        for(int i=length-1; i>=0; i--){
            ParseTreeNode child = reductionList.get(i);
            parent.childList.add(child);
            child.parent = parent;
           System.out.print(" " + reductionList.get(i).token.tag.toString());
        }
        System.out.println();

        stack.push(new StackItem(gotoTable[(stack.peek().state)][reduction.tag.ordinal()], reduction, parent));
    }
    public void accept(){
        accept = true;
    }
}
