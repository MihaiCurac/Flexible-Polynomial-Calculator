package Controller;

import Model.Polynomial;
import View.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolyController implements ActionListener {
    GUI gui;
    public PolyController(GUI gui){
        this.gui = gui;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("enter1")) {
            String buffer = gui.firstOperandTextField.getText();
            Polynomial poly = this.createPolynomial(buffer);
        } else if (command.equals("enter2")) {
            String buffer = gui.secondOperandTextField.getText();
            Polynomial poly = this.createPolynomial(buffer);
        } else if (command.equals("plus")) {
            String buffer1 = gui.firstOperandTextField.getText();
            String buffer2 = gui.secondOperandTextField.getText();
            Polynomial result = this.add(buffer1, buffer2);
            gui.resultLabel.setText(result.display());
        } else if (command.equals("minus")) {
            String buffer1 = gui.firstOperandTextField.getText();
            String buffer2 = gui.secondOperandTextField.getText();
            Polynomial result = this.subtract(buffer1, buffer2);
            gui.resultLabel.setText(result.display());
        } else if (command.equals("times")) {
            String buffer1 = gui.firstOperandTextField.getText();
            String buffer2 = gui.secondOperandTextField.getText();
            Polynomial result = this.multiply(buffer1, buffer2);
            gui.resultLabel.setText(result.display());
        }
        else if (command.equals("derive")) {
            String buffer = gui.firstOperandTextField.getText();
            Polynomial result = this.derive(buffer);
            gui.resultLabel.setText(result.display());
        }
        else if (command.equals("integrate")) {
            String buffer = gui.firstOperandTextField.getText();
            Polynomial result = this.integrate(buffer);
            gui.resultLabel.setText(result.doubleDisplay());
        }
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

    public Polynomial add(String buffer1, String buffer2) {
        Polynomial poly1, poly2, result;
        poly1 = createPolynomial(buffer1);
        poly2 = createPolynomial(buffer2);
        result = poly1.add(poly2);
        return result;
    }

    public Polynomial subtract(String buffer1, String buffer2) {
        Polynomial poly1, poly2, result;
        poly1 = createPolynomial(buffer1);
        poly2 = createPolynomial(buffer2);
        result = poly1.subtract(poly2);
        return result;
    }

    public Polynomial multiply(String buffer1, String buffer2) {
        Polynomial poly1, poly2, result;
        poly1 = createPolynomial(buffer1);
        poly2 = createPolynomial(buffer2);
        result = poly1.multiply(poly2);
        return result;
    }

    public Polynomial derive(String buffer){
        Polynomial poly;
        poly = createPolynomial(buffer);
        Polynomial result;
        result = poly.derivate();
        return result;
    }

    public Polynomial integrate(String buffer){
        Polynomial poly;
        poly = createPolynomial(buffer);
        Polynomial result;
        result = poly.integrate();
        return result;
    }

}
