package Model;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monomial {
    private int coefficient;
    private double divCoefficient;
    private int exponent;
    private String[] buffer;
    private String monomial;

    public Monomial(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public Monomial(String mon) {
        if(checkFormat(mon))
            this.monomial = mon;
        divCoefficient = (double)coefficient;
    }

    public Monomial(double divCoefficient, int coefficient, int exponent) {
        this.exponent = exponent;
        this.coefficient = coefficient;
        this.divCoefficient = divCoefficient;
        this.monomial = format();
    }
    //We check for characters that we do not need, except for '^'
    private boolean checkFormat(String monomial){
        if(!monomial.matches("^[a-zA-Z0-9\\^\\*\\- ]*"))
            return false;
        Pattern polynomialFormat = Pattern.compile("\\^");
        Matcher match = polynomialFormat.matcher(monomial);
        String s = "";
        while(match.find())
            s = match.group();
        //Case in which there is no '^'
        if(s.isEmpty()) {
            buffer = monomial.split("[a-zA-Z\\*]");
            if (buffer.length != 0) {
                if(buffer[0].equals("-"))
                    coefficient = -1;
                else
                    coefficient = Integer.parseInt(buffer[0]);
                exponent = (buffer[0].equals(monomial)) ? 0 : 1;
            } else {
                coefficient = 1;
                exponent = 1;
            }
        }
        //Case in which there is '^'
            else{
            buffer = monomial.split("[\\^]");
            String coef[] = monomial.split("[a-zA-Z\\*]");
            if (!coef[0].equals("")) {
                if (coef[0].equals("-"))
                    coefficient = -1;
                else
                    coefficient = Integer.parseInt(coef[0]);
            }
            else
                coefficient = 1;
            exponent = Integer.parseInt(buffer[1]);

        }
            return true;
    }
    private String format() {
        String strTemp = new String(this.divCoefficient + "x^" + this.exponent);
        return strTemp;
    }

    public String getMonomial(){
        if(exponent == 0)
            return Integer.toString(coefficient);
        else if (exponent == 1){
            if(coefficient == 1)
                return "x";
            else if(coefficient == -1)
                return "-x";
            else
                return coefficient + "x";
        }
        else {
            if(coefficient == 1)
                return "x^" + exponent;
            else if (coefficient == -1)
                return "-x^" + exponent;
            else
                return coefficient + "x^" + exponent;
        }
    }

    public String getDoubleMonomial(){
        if(exponent == 0)
            return Double.toString(divCoefficient);
        else if (exponent == 1){
            if(divCoefficient == 1.0)
                return "x";
            else if(divCoefficient == -1.0)
                return "-x";
            else{
                if(divCoefficient == (int)divCoefficient)
                    return coefficient + "x";
                else
                    return divCoefficient + "x";
            }
        }
        else {
            if(divCoefficient == 1.0)
                return "x^" + exponent;
            else if (divCoefficient == -1.0)
                return "-x^" + exponent;
            else{
                if(divCoefficient == (int)divCoefficient)
                    return coefficient + "x^" + exponent;
                else
                    return divCoefficient + "x^" + exponent;
            }
        }
    }

    public void display() {
        System.out.println("Power: " + exponent + " Coefficient: " + coefficient + "\n");
    }

    public int value(int x) {
        return (int) (divCoefficient * Math.pow(x, exponent));
    }

    public double dValue(double x) {
        return divCoefficient * Math.pow(x, exponent);
    }

    public void addToCoefficient(int x) {
        this.coefficient += x;
        this.divCoefficient = (double)coefficient;
    }

    public Monomial multiply(Monomial mon2) {
        Monomial result;
        double dcoeff = this.divCoefficient * mon2.divCoefficient;
        int coeff = (int)dcoeff;
        int exp = this.getExponent() + mon2.getExponent();
        result = new Monomial(dcoeff, coeff, exp);
        return result;
    }

    public Monomial derivate() {
        Monomial result = new Monomial(0, 0, 0);
        if (this.exponent != 0) {
            double dcoeff = this.divCoefficient * this.exponent;
            int coeff = (int)dcoeff;
            int exp = this.exponent - 1;
            result = new Monomial(dcoeff, coeff, exp);
        }
        return result;
    }

    public Monomial integrate() {
        Monomial result;
        int exp = this.exponent + 1;
        double dcoeff = this.divCoefficient / exp;
        int coeff = (int)dcoeff;
        result = new Monomial(dcoeff, coeff, exp);
        return result;
    }

    public static Comparator<Monomial> compareByExponent() {
        return Comparator.comparingInt(s -> s.exponent);
    }

    public int getExponent() {
        return exponent;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public double getDivCoefficient() {
        return divCoefficient;
    }

    public void setDivCoefficient(double divCoefficient) {
        this.divCoefficient = divCoefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }
}
