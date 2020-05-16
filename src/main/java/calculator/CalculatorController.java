package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);

        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           display.setText(String.format("%1$,.2f", result));
           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }

            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }
    }
    @FXML
    public void clearMemory(){
        operator = "";
        number1 = 0;
        display.setText("0");
        startNumber = true;
        System.out.println("Clear Pressed");
    }
    @FXML
    public void dotPressed(){
        System.out.println("Dot Pressed");
        if(!display.getText().endsWith(".")){
            display.setText(display.getText() + ".");
        }
    }
    @FXML
    public void plusMinus(){

        System.out.println("Plus minus change!");
        if(display.getText().startsWith("-")){
            display.setText(display.getText().replaceAll("-","" ));
        }
        else if(!display.getText().startsWith("-")){
            display.setText("-" + display.getText());
        }
    }

}
