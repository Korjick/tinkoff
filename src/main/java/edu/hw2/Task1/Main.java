package edu.hw2.Task1;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {

    private Main() { }

    private static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));

        LOGGER.printf(Level.INFO, res + " = " + res.evaluate());
    }
}
