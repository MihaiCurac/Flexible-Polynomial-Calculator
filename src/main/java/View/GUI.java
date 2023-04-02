package View;

import Controller.PolyController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {

    // buttons for the various operations
    public JFrame mainFrame;
    public JLabel operationLabel;
    public JButton addBtn;
    public JButton subtractBtn;
    public JButton multiplyBtn;
    public JButton deriveBtn;
    public JButton integrateBtn;

    // Labels and TextFields for the two operands
    public JLabel firstOperandLabel;
    public JTextField firstOperandTextField;

    public JLabel secondOperandLabel;
    public JTextField secondOperandTextField;

    public JLabel resultLabel;
    public JLabel guideLabel;

    public GUI() {
        prepareGUI();
    }
    private void prepareGUI(){
        mainFrame = new JFrame("Polynomial Calculator");
        mainFrame.setBounds(300, 300, 700, 500);
        mainFrame.setLayout(null);
        
        Font visibleFont = new Font("Times New Roman", Font.PLAIN, 22);
        // big font for buttons
        Font bigFont = new Font("Times New Roman", Font.PLAIN, 40);

        // instructions label
        guideLabel = new JLabel(" ");
        guideLabel.setFont(visibleFont);
        guideLabel.setBounds(20,470,850,100);
        mainFrame.add(guideLabel);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        // operand 1 label + text field
        firstOperandLabel = new JLabel("First Polynomial: ");
        firstOperandLabel.setBounds(50, 20, 700, 50);
        firstOperandLabel.setFont(visibleFont);
        //firstOperandLabel.setLayout(new FlowLayout());
        mainFrame.add(firstOperandLabel);

        firstOperandTextField = new JTextField();
        firstOperandTextField.setBounds(200, 10, 250, 30);
        firstOperandTextField.setFont(visibleFont);
        firstOperandLabel.add(firstOperandTextField);

        // operand 1 label + text field
        secondOperandLabel = new JLabel("Second Polynomial: ");
        secondOperandLabel.setBounds(50, 80, 700, 50);
        secondOperandLabel.setFont(visibleFont);
        //secondOperandLabel.setLayout(new FlowLayout());
        mainFrame.add(secondOperandLabel);

        secondOperandTextField = new JTextField();
        secondOperandTextField.setBounds(200, 10, 250, 30);
        secondOperandTextField.setFont(visibleFont);
        secondOperandLabel.add(secondOperandTextField);

        // label for result
        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(visibleFont);
        resultLabel.setBounds(90, 320, 700, 50);
        mainFrame.add(resultLabel);

        // buttons + 1 label
        operationLabel = new JLabel("Operation: ");
        operationLabel.setFont(visibleFont);
        operationLabel.setBounds(90, 90, 800, 200);
        mainFrame.add(operationLabel);

        // addition button
        addBtn = new JButton("+");
        addBtn.setFont(bigFont);
        addBtn.setBounds(0, 120, 80, 60);
        operationLabel.add(addBtn);

        // substraction button
        subtractBtn = new JButton("-");
        subtractBtn.setFont(bigFont);
        subtractBtn.setBounds(100, 120, 80, 60);
        operationLabel.add(subtractBtn);

        // multiplication button
        multiplyBtn = new JButton("*");
        multiplyBtn.setFont(bigFont);
        multiplyBtn.setBounds(200, 120, 80, 60);
        operationLabel.add(multiplyBtn);

        // derivation button
        deriveBtn = new JButton("'");
        deriveBtn.setFont(bigFont);
        deriveBtn.setBounds(300, 120, 80, 60);
        operationLabel.add(deriveBtn);

        // integration button
        integrateBtn = new JButton("\u222B");
        integrateBtn.setFont(bigFont);
        integrateBtn.setBounds(400, 120, 80, 60);
        operationLabel.add(integrateBtn);
    }

    public void showEventDemo(){
        JButton firstOk = new JButton("OK");
        JButton secondOk = new JButton("OK");
        firstOk.setActionCommand("enter1");
        secondOk.setActionCommand("enter2");
        addBtn.setActionCommand("plus");
        subtractBtn.setActionCommand("minus");
        multiplyBtn.setActionCommand("times");
        deriveBtn.setActionCommand("derive");
        integrateBtn.setActionCommand("integrate");
        firstOk.addActionListener(new PolyController(this));
        secondOk.addActionListener(new PolyController(this));
        addBtn.addActionListener(new PolyController(this));
        subtractBtn.addActionListener(new PolyController(this));
        multiplyBtn.addActionListener(new PolyController(this));
        deriveBtn.addActionListener(new PolyController(this));
        integrateBtn.addActionListener(new PolyController(this));
        firstOk.setBounds(470, 10, 80, 30);
        secondOk.setBounds(470, 10, 80, 30);
        firstOperandLabel.add(firstOk);
        secondOperandLabel.add(secondOk);
        mainFrame.setVisible(true);
    }
}
