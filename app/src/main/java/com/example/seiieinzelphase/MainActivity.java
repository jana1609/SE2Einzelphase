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

    public void calculation(View view){
        // do something
        TextView tvAntwort = (TextView) findViewById(R.id.tvAntwort);
        EditText etEingabe = (EditText) findViewById(R.id.input);
        int input = Integer.parseInt(etEingabe.getText().toString());
        tvAntwort.setText("Button was clicked!!!" + input);
    }
}