package com.example.seiieinzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendServer(View view){
        // do something
    }

    public void calculation(View view){
        // alternierende Quersumme bilden und ausgeben ob diese gerade oder ungerade ist

        // get Layout Elements
        TextView tvAntwort = findViewById(R.id.tvAntwort);
        EditText etEingabe = findViewById(R.id.input);
        String inputString = etEingabe.getText().toString();

        // bildet alternierende Quersumme mit (-1)^n * (int at length - 1 - n) mit n von 0 bis length - 1
        int altQuersumme = 0;
        for (int i = 0; i<inputString.length(); i++) {
            altQuersumme += Math.pow(-1, i) * Character.getNumericValue(inputString.charAt(inputString.length()-1-i));
        }
        // Quersumme gerade oder ungerade
        String output = "Die alternierende Quersumme "+altQuersumme+" von "+inputString+" ist ";
        if(altQuersumme % 2 == 0) output += "gerade";
        else output += "ungerade";

        // output result
        tvAntwort.setText(output);
    }
}