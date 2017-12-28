package lexer;

public class Num extends Token{
    public final double mantissa;
    public final int exponent;

    public Num(double m, int e) {
        super(Tag.NUM);
        mantissa = m;
        exponent = e;
    }
}
