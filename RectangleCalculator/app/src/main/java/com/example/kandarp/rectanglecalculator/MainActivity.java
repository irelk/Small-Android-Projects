package com.example.kandarp.rectanglecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    TextView areaCalc;
    TextView periCalc;
    EditText width;
    EditText height;
    Button calc;
    double areaFinal;
    double periFinal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        width = (EditText) findViewById(R.id.width);
        height = (EditText)findViewById(R.id.height);
        areaCalc = (TextView) findViewById(R.id.areaCalc);
        periCalc = (TextView) findViewById(R.id.periCalc);
        calc = (Button) findViewById(R.id.calcBtn);



        calc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                double w = Double.parseDouble(width.getText().toString());
                double h = Double.parseDouble(height.getText().toString());

                if(width.getText().toString().trim().equals("") || height.getText().toString().trim().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Width or Height Attribute is Missing", Toast.LENGTH_SHORT).show();
                }else{
                    areaFinal = w * h;
                    periFinal = (2*w)+(2*h);

                    DecimalFormat precision = new DecimalFormat("0.00");

                    String a = String.format("%.2f",areaFinal);
                    String p = String.format("%.2f",periFinal);

                    areaCalc.setText(a);
                    periCalc.setText(p);
                }
            }
        });
    }

}
