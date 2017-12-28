package parser;


public class Accept implements Action {
    @Override
    public void run(Parser p) {
        p.accept();
    }
}
