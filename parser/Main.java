package parser;


import lexer.InvalidInputException;
import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

public class Main {

    public static void main(String[] args) throws InvalidInputException {
        int[][] gotoTable = new int[100][100];
        Action[][] actionTable = new Action[100][100];

        //initiate parser tables
        gotoTable[0][Tag.E.ordinal()] = 1;
        gotoTable[0][Tag.A.ordinal()] = 2;
        gotoTable[0][Tag.B.ordinal()] = 3;
        gotoTable[0][Tag.C.ordinal()] = 4;
        gotoTable[0][Tag.D.ordinal()] = 6;
        gotoTable[5][Tag.C.ordinal()] = 11;
        gotoTable[5][Tag.D.ordinal()] = 6;
        gotoTable[8][Tag.A.ordinal()] = 13;
        gotoTable[8][Tag.B.ordinal()] = 3;
        gotoTable[8][Tag.C.ordinal()] = 4;
        gotoTable[8][Tag.D.ordinal()] = 6;
        gotoTable[9][Tag.B.ordinal()] = 14;
        gotoTable[9][Tag.C.ordinal()] = 4;
        gotoTable[9][Tag.D.ordinal()] = 6;
        gotoTable[10][Tag.B.ordinal()] = 15;
        gotoTable[10][Tag.C.ordinal()] = 4;
        gotoTable[10][Tag.D.ordinal()] = 6;


        actionTable[0][Tag.COS.ordinal()] = new Shift(5);
        actionTable[5][Tag.COS.ordinal()] = new Shift(5);
        actionTable[8][Tag.COS.ordinal()] = new Shift(5);
        actionTable[9][Tag.COS.ordinal()] = new Shift(5);
        actionTable[10][Tag.COS.ordinal()] = new Shift(5);

        actionTable[1][Tag.PLUS.ordinal()] = new Shift(8);
        actionTable[2][Tag.PLUS.ordinal()] = new Reduce(1, new Token(Tag.E));
        actionTable[3][Tag.PLUS.ordinal()] = new Reduce(1, new Token(Tag.A));
        actionTable[4][Tag.PLUS.ordinal()] = new Reduce(1, new Token(Tag.B));
        actionTable[6][Tag.PLUS.ordinal()] = new Reduce(1, new Token(Tag.C));
        actionTable[7][Tag.PLUS.ordinal()] = new Reduce(1, new Token(Tag.D));
        actionTable[11][Tag.PLUS.ordinal()] = new Reduce(2, new Token(Tag.C));
        actionTable[12][Tag.PLUS.ordinal()] =  new Reduce(2, new Token(Tag.D));
        actionTable[13][Tag.PLUS.ordinal()] = new Reduce(3, new Token(Tag.E));
        actionTable[14][Tag.PLUS.ordinal()] = new Reduce(3, new Token(Tag.A));
        actionTable[15][Tag.PLUS.ordinal()] = new Reduce(3, new Token(Tag.B));

        actionTable[2][Tag.MINUS.ordinal()] = new Shift(9);
        actionTable[3][Tag.MINUS.ordinal()] = new Reduce(1, new Token(Tag.A));
        actionTable[4][Tag.MINUS.ordinal()] = new Reduce(1, new Token(Tag.B));
        actionTable[6][Tag.MINUS.ordinal()] = new Reduce(1, new Token(Tag.C));
        actionTable[7][Tag.MINUS.ordinal()] = new Reduce(1, new Token(Tag.D));
        actionTable[11][Tag.MINUS.ordinal()] = new Reduce(2, new Token(Tag.C));
        actionTable[12][Tag.MINUS.ordinal()] = new Reduce(2, new Token(Tag.D));
        actionTable[13][Tag.MINUS.ordinal()] = new Shift(9);
        actionTable[14][Tag.MINUS.ordinal()] = new Reduce(3, new Token(Tag.A));
        actionTable[15][Tag.MINUS.ordinal()] = new Reduce(3, new Token(Tag.B));

        actionTable[4][Tag.TIMES.ordinal()] = new Shift(10);
        actionTable[6][Tag.TIMES.ordinal()] = new Reduce(1, new Token(Tag.C));
        actionTable[7][Tag.TIMES.ordinal()] = new Reduce(1, new Token(Tag.D));
        actionTable[11][Tag.TIMES.ordinal()] = new Reduce(2, new Token(Tag.C));
        actionTable[12][Tag.TIMES.ordinal()] =  new Reduce(2, new Token(Tag.D));


        actionTable[0][Tag.COS.ordinal()] = new Shift(5);
        actionTable[5][Tag.COS.ordinal()] = new Shift(5);
        actionTable[8][Tag.COS.ordinal()] = new Shift(5);
        actionTable[9][Tag.COS.ordinal()] = new Shift(5);
        actionTable[10][Tag.COS.ordinal()] = new Shift(5);


        actionTable[6][Tag.FAC.ordinal()] = new Shift(12);
        actionTable[7][Tag.FAC.ordinal()] = new Reduce(1, new Token(Tag.D));
        actionTable[12][Tag.FAC.ordinal()] =  new Reduce(2, new Token(Tag.D));


        actionTable[0][Tag.NUM.ordinal()] = new Shift(7);
        actionTable[5][Tag.NUM.ordinal()] = new Shift(7);
        actionTable[8][Tag.NUM.ordinal()] = new Shift(7);
        actionTable[9][Tag.NUM.ordinal()] = new Shift(7);
        actionTable[10][Tag.NUM.ordinal()] = new Shift(7);

        actionTable[1][Tag.EOL.ordinal()] = new Accept();
        actionTable[2][Tag.EOL.ordinal()] = new Reduce(1, new Token(Tag.E));
        actionTable[3][Tag.EOL.ordinal()] = new Reduce(1, new Token(Tag.A));
        actionTable[4][Tag.EOL.ordinal()] = new Reduce(1, new Token(Tag.B));
        actionTable[6][Tag.EOL.ordinal()] = new Reduce(1, new Token(Tag.C));
        actionTable[7][Tag.EOL.ordinal()] = new Reduce(1, new Token(Tag.D));
        actionTable[11][Tag.EOL.ordinal()] = new Reduce(2, new Token(Tag.C));
        actionTable[12][Tag.EOL.ordinal()] =  new Reduce(2, new Token(Tag.D));
        actionTable[13][Tag.EOL.ordinal()] = new Reduce(3, new Token(Tag.E));
        actionTable[14][Tag.EOL.ordinal()] = new Reduce(3, new Token(Tag.A));
        actionTable[15][Tag.EOL.ordinal()] = new Reduce(3, new Token(Tag.B));

        //lex and parse input
        Parser parser = new Parser(gotoTable, actionTable);
        Lexer lexer = new Lexer();
        try {
            ParseTreeNode head = parser.parse(lexer.lex(args[0]));
            head.print();
        } catch (InvalidInputException e) {
            System.out.println("invalid input at character: " + e.index);
        }
    }
}
