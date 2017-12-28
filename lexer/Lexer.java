package lexer;

import java.util.ArrayList;
import java.util.List;


public class Lexer {
    private int index;
    private String input;
    public List<Token> lex(String i) throws InvalidInputException {
       input = i;
       index = 0;
        ArrayList<Token> result = new ArrayList<Token>();
       while(index < input.length()) {
          result.add(scan());
       }
       result.add(new Token(Tag.EOL));
       return result;
    }
    private Token scan() throws InvalidInputException {
        while(input.charAt(index) == ' '){
            index++;
        }
        switch(input.charAt(index)) {
            case '~' :
                index++;
                if(Character.isDigit(input.charAt(index)))
                    return matchNum(-1);
                throw new InvalidInputException(index);
            case '+' :
                index++;
                return new Token(Tag.PLUS);

            case '-' :
                index++;
                return new Token(Tag.MINUS);
            case '*' :
                index++;
                return new Token(Tag.TIMES);
            case 'c':
                index++;
                if(input.charAt(index)=='o'){
                    index++;
                    if(input.charAt(index)=='s'){
                        index++;
                        return new Token(Tag.COS);
                    }
                }
                throw new InvalidInputException(index);
            case '!' :
                index++;
                return new Token(Tag.FAC);
        }
        if(Character.isDigit(input.charAt(index))){
            return matchNum(1);
        }

        throw new InvalidInputException(index);
    }

    public Token matchNum(int sign) throws InvalidInputException {
        //Lexer handles floats rather than parser
        int start = index;
        while(Character.isDigit(input.charAt(index))) {
            index++;
        }
        if(input.charAt(index) == '.') {
            index++;
            while(Character.isDigit(input.charAt(index))) {
                index++;
            }
           if(input.charAt(index)=='e') {
                index++;
               double mantissa = Double.parseDouble(input.substring(start, index-1));
               start = index;
               while(Character.isDigit(input.charAt(index))) {
                   index++;
                   if(index >= input.length()) break;
               }
               int exponent = Integer.parseInt(input.substring(start, index));
               return new Num(sign*mantissa, exponent);
           }
        }
        throw new InvalidInputException(index);
    }
}
