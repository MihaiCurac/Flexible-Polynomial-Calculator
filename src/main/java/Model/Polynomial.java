package Model;

import java.util.ArrayList;

public class Polynomial {

    private String[] monomialAsStr;
    private ArrayList<Monomial> monomials = new ArrayList<>();
    private int x;

    public Polynomial(String[] monomialAsStr) {
        this.monomialAsStr = monomialAsStr;
        for(String mon : monomialAsStr){
            System.out.println(mon);
            monomials.add(new Monomial(mon));
        }
        //testing with outputs
        for (Monomial temp : monomials) {
            temp.display();
        }
        this.polySort();
        this.polySort();
    }

    private Polynomial(int x) {
        this.x = x;
    }

    public String display(){
        StringBuilder displayedPoly = new StringBuilder();
        for(int i = 0; i < monomials.size(); i++) {
            if(monomials.get(i).getCoefficient() < 0)
                displayedPoly.append(monomials.get(i).getMonomial());
             else
                displayedPoly.append((i == 0) ? monomials.get(i).getMonomial() : "+" + monomials.get(i).getMonomial());
        }
        return displayedPoly.toString();
    }

    public String doubleDisplay(){
        StringBuilder displayedPoly = new StringBuilder();
        for(int i = 0; i < monomials.size(); i++) {
            if(monomials.get(i).getDivCoefficient() < 0)
                displayedPoly.append(monomials.get(i).getDoubleMonomial());
            else
                displayedPoly.append((i == 0) ? monomials.get(i).getDoubleMonomial() : "+" + monomials.get(i).getDoubleMonomial());
        }
        return displayedPoly.toString();
    }

    public int getDegree() {
        int deg = -1;
        for (Monomial monomial : monomials) {
            if (monomial.getExponent() > deg)
                deg = monomial.getExponent();
        }
        return deg;
    }

    private void polySort(){
        for(int i = 0; i < monomials.size(); i++){
            int exp1 = monomials.get(i).getExponent();
            for(int j = i + 1; j < monomials.size(); j++){
                int exp2 = monomials.get(j).getExponent();
                if (exp1 == exp2){
                    monomials.get(i).addToCoefficient(monomials.get(j).getCoefficient());
                    this.monomials.remove(j);
                }
            }
        }
        this.monomials.sort(Monomial.compareByExponent().reversed());
    }

    private void clean() {
        for(int i = 0; i < monomials.size(); i++)
            if(monomials.get(i).getCoefficient() == 0)
                monomials.remove(i);
    }

    private int getExponentIndex(int exponent){
        for(int i = 0; i < monomials.size(); i++){
            Monomial mon = monomials.get(i);
            if(exponent == mon.getExponent())
                return i;
        }
        return -1;
    }

    public Polynomial add(Polynomial poly2) {
        Polynomial result = new Polynomial(x);
        for (int i = 0; i < this.monomials.size(); i++) {
            int exp1 = this.monomials.get(i).getExponent();
            int coeff1 = this.monomials.get(i).getCoefficient();
            int posFind = poly2.getExponentIndex(exp1);
            if (posFind == -1)
                result.monomials.add(new Monomial(coeff1, exp1));
            else {
                int coeff2 = poly2.monomials.get(posFind).getCoefficient();
                result.monomials.add(new Monomial(coeff1 + coeff2, exp1));
                poly2.monomials.remove(posFind);
            }
        }
        //Now we add what remained unchanged in the second polynomial
        for (int j = 0; j < poly2.monomials.size(); j++)
            result.monomials.add(new Monomial(poly2.monomials.get(j).getCoefficient(), poly2.monomials.get(j).getExponent()));
        result.polySort();
        result.clean();
        return result;
    }

    public Polynomial subtract(Polynomial poly2){
        Polynomial result;
        for(int i = 0; i < poly2.monomials.size(); i++)
            poly2.monomials.get(i).setCoefficient(poly2.monomials.get(i).getCoefficient() * (-1));
        result = this.add(poly2);
        result.clean();
        result.polySort();
        return result;
    }

    public Polynomial multiply(Polynomial poly2){
        Polynomial result = new Polynomial(x);
        for(int i = 0; i < this.monomials.size(); i++){
            Monomial mon1 = this.monomials.get(i);
            for(int j = 0; j < poly2.monomials.size(); j++){
                Monomial mon2 = poly2.monomials.get(j);
                Monomial monRes = mon1.multiply(mon2);
                result.monomials.add(monRes);
            }
        }
        result.polySort();
        result.polySort();
        return result;
    }

    public Polynomial derivate() {
        Polynomial result = new Polynomial(x);
        for (int i = 0; i < this.monomials.size(); i++)
            result.monomials.add(this.monomials.get(i).derivate());
        result.clean();
        return result;
    }

    public Polynomial integrate() {
        Polynomial result = new Polynomial(x);
        for (int i = 0; i < this.monomials.size(); i++)
            result.monomials.add(this.monomials.get(i).integrate());
        return result;
    }
}
