package com.example.a300283635.tipcalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener,View.OnClickListener {

    private EditText billAmountEditText;
    private TextView percentTextView;
    private Button percentUpButton;
    private Button percentDownButton;
    private TextView tipTextView;
    private TextView totalTextView;

    // Define instance variables that should be saved
    private String billAmountString = "";
    private float tipPercent = .15f;

    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            billAmountString = savedInstanceState.getString("billAmountString", "");
            tipPercent = savedInstanceState.getFloat("tipPercent", 0.15f);
        } else {
            // Probably initialize members with default values for a new instance
            billAmountString = "";
        }


        billAmountEditText = (EditText) findViewById(R.id.bilLAmt);
        percentTextView = (TextView) findViewById(R.id.tipPercent);
        percentUpButton = (Button) findViewById(R.id.percentUpBtn);
        percentDownButton = (Button) findViewById(R.id.percentDownBtn);
        tipTextView = (TextView) findViewById(R.id.tipAmt);
        totalTextView = (TextView) findViewById(R.id.totalAmt);

        // set the listeners
        billAmountEditText.setOnEditorActionListener(this);
        percentUpButton.setOnClickListener(this);
        percentDownButton.setOnClickListener(this);

        savedValues =getSharedPreferences("SavedValues",MODE_PRIVATE);
    }

    @Override
    public void onPause(){
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("billAmountString",billAmountString);
        editor.putFloat("tipPercent",tipPercent);
        editor.commit();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        // get the instance variables
        billAmountString = savedValues.getString("billAmountString", "");
        tipPercent = savedValues.getFloat("tipPercent", 0.15f);

        billAmountEditText.setText(billAmountString);

        calculate();
    }

    public void calculate() {

        // Bill Amount
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")) {
            billAmount = 0;
        } else {
            billAmount = Float.parseFloat(billAmountString);
        }
        // Tip and Total Calculations
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        // Format Results
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.percentDownBtn:
                tipPercent = tipPercent - .01f;
                calculate();
                break;
            case R.id.percentUpBtn:
                tipPercent = tipPercent + .01f;
                calculate();
                break;
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        // Call Calculate method on either done button of the keyboard or on any unspecified button

        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculate();
        }

        return false;
    }
}
