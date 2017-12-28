package parser;


public class Shift implements Action {
    int state;
    @Override
    public void run(Parser p) {
       p.shift(state);
    }

    public Shift(int state) {
        this.state = state;
    }
}
