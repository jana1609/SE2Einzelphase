package com.example.seiieinzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    String output;

    public void sendServer(View view){
        // send input to server and show output

        // get Layout Elements
        TextView tvAntwort = findViewById(R.id.tvAntwort);
        EditText input = findViewById(R.id.input);

        // config Thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("se2-isys.aau.at",53212);
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                    printWriter.println(input.getText().toString());
                    printWriter.flush();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    output = bufferedReader.readLine();
                    printWriter.close();
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                    output = "Server error\nPlease try again";
                }
            }
        });

        // run thread
        thread.start();
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
            output = "Server error\nPlease try again";
        }

        // output result
        tvAntwort.setText(output);
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
        output = "Die alternierende Quersumme "+altQuersumme+" von "+inputString+" ist ";
        if(altQuersumme % 2 == 0) output += "gerade";
        else output += "ungerade";

        // output result
        tvAntwort.setText(output);
    }
}