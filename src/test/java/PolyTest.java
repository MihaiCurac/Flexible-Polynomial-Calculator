import Model.Polynomial;
import Controller.PolyController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PolyTest {

    @Test
    public void Test()
    {

        Polynomial poly1 = createPolynomial("3x^2+5x-4");
        Polynomial poly2 = createPolynomial("x^3-2x+1");
        Polynomial sumResult = poly1.add(poly2);
        System.out.println("Sum: " + sumResult.display());
        assertEquals("x^3+3x^2+3x-3", sumResult.display());
        poly1 = createPolynomial("3x^2+5x-4");
        poly2 = createPolynomial("x^3-2x+1");
        Polynomial subResult = poly1.subtract(poly2);
        System.out.println("Subtraction: " + subResult.display());
        assertEquals("-x^3+3x^2+7x-5", subResult.display());
        poly1 = createPolynomial("3x^2+5x-4");
        poly2 = createPolynomial("x^3-2x+1");
        Polynomial productResult = poly1.multiply(poly2);
        System.out.println("Product: " + productResult.display());
        assertEquals("3x^5+5x^4-10x^3-7x^2+13x-4", productResult.display());
        poly1 = createPolynomial("3x^2+5x-4");
        poly2 = createPolynomial("x^3-2x+1");
        Polynomial derivResult1 = poly1.derivate();
        System.out.println("First polynomial derived: " + derivResult1.display());
        assertEquals("6x+5", derivResult1.display());
        Polynomial derivResult2 = poly2.derivate();
        System.out.println("Second polynomial derived: " + derivResult2.display());
        assertEquals("3x^2-2", derivResult2.display());
        poly1 = createPolynomial("3x^2+5x-4");
        poly2 = createPolynomial("x^3-2x+1");
        Polynomial integrateResult1 = poly1.integrate();
        System.out.println("First polynomial integral: " + integrateResult1.doubleDisplay());
        assertEquals("x^3+2.5x^2-4x", integrateResult1.doubleDisplay());
        Polynomial integrateResult2 = poly2.integrate();
        System.out.println("Second polynomial integral: " + integrateResult2.doubleDisplay());
        assertEquals("0.25x^4-x^2+x", integrateResult2.doubleDisplay());

    }

    public Polynomial createPolynomial(String buffer){
        String[] monomials;
        String finBuffer;
        String actBuffer = (buffer.contains("-")) ? buffer.replace("-", "+-") : buffer;
        finBuffer = (actBuffer.charAt(0) == '+') ? actBuffer.substring(1) : actBuffer;
        monomials = finBuffer.split("\\+");
        Polynomial poly = new Polynomial(monomials);
        return poly;
    }
}
