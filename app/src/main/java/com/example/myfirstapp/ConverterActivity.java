package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConverterActivity extends AppCompatActivity {

    private EditText inputValue;
    private TextView resultConverted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        inputValue = findViewById(R.id.inputValue);
        resultConverted = findViewById(R.id.resultConverted);

        Button buttonConvertToKm = findViewById(R.id.buttonConvertToKm);
        Button buttonConvertToMiles = findViewById(R.id.buttonConvertToMiles);
        Button buttonBack = findViewById(R.id.buttonBack);

        buttonConvertToKm.setOnClickListener(v -> convertUnits(0.001, " km"));
        buttonConvertToMiles.setOnClickListener(v -> convertUnits(0.000621371, " millas"));
        buttonBack.setOnClickListener(v -> finish());
    }

    private void convertUnits(double factor, String unit) {
        String inputText = inputValue.getText().toString();
        if (!inputText.isEmpty()) {
            double meters = Double.parseDouble(inputText);
            double result = meters * factor;
            resultConverted.setText(String.format("%.4f%s", result, unit));
        } else {
            resultConverted.setText("Introduce un valor");
        }
    }
}
