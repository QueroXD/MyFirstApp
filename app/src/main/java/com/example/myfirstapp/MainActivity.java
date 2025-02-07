package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private String currentInput = "";
    private double firstNumber = 0.0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultText);

        setNumericButtonClickListener();
        setOperatorButtonClickListener();

        findViewById(R.id.buttonClear).setOnClickListener(v -> clearCalculator());

        findViewById(R.id.buttonConvert).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ConverterActivity.class);
            startActivity(intent);
        });
    }

    private void setNumericButtonClickListener() {
        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            currentInput += button.getText().toString();
            resultText.setText(currentInput);
        };

        int[] numericButtons = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonDot
        };

        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtonClickListener() {
        View.OnClickListener operatorListener = v -> {
            Button button = (Button) v;
            if (!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
                operator = button.getText().toString();
                currentInput = "";
            }
        };

        int[] operatorButtons = {R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide};

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(operatorListener);
        }

        findViewById(R.id.buttonSquareRoot).setOnClickListener(v -> calculateSquareRoot());
        findViewById(R.id.buttonEqual).setOnClickListener(v -> calculateResult());
    }

    private void calculateResult() {
        if (!currentInput.isEmpty()) {
            double secondNumber = Double.parseDouble(currentInput);
            double result = 0.0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    result = secondNumber != 0 ? firstNumber / secondNumber : 0;
                    break;
            }

            resultText.setText(String.valueOf(result));
            currentInput = "";
        }
    }

    private void calculateSquareRoot() {
        if (!currentInput.isEmpty()) {
            double number = Double.parseDouble(currentInput);
            double result = Math.sqrt(number);
            resultText.setText(String.valueOf(result));
            currentInput = "";
        }
    }

    private void clearCalculator() {
        currentInput = "";
        firstNumber = 0.0;
        operator = "";
        resultText.setText("0");
    }
}
