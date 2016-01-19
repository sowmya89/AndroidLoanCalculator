package com.example.sowmyap.lcalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText homeValue;
    private EditText downPaymentValue;
    private EditText interestRateValue;
    private EditText taxRateValue;
    private Button calculate;
    private String termValue;

    DecimalFormat deci = new DecimalFormat("###,###,###.##");
    private MortgageCalculator calc = new MortgageCalculator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeValue = (EditText) findViewById(R.id.homeValueEditText);
        downPaymentValue = (EditText) findViewById(R.id.downPaymentEditText);
        interestRateValue = (EditText) findViewById(R.id.APREditText);

        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        mySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                termValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        taxRateValue = (EditText) findViewById(R.id.taxrateEditText);
        calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        double totTaxPaid = calc.getTotalPropertyTax(homeValue, termValue, taxRateValue);
        double totIntPaid = calc.getTotalInterestPaid(homeValue, downPaymentValue, interestRateValue, termValue,
                taxRateValue);
        double monthlyPayment = calc.getMonthlyPayment(homeValue, downPaymentValue, interestRateValue, termValue,
                taxRateValue);
        String PayOffDate = calc.getPayoffDate(termValue);

        if (Double.isNaN(totTaxPaid) || Double.isNaN(totIntPaid) || Double.isNaN(monthlyPayment)) {
            Toast.makeText(MainActivity.this, "Kindly enter all the fields", Toast.LENGTH_LONG).show();
        } else {
            String ttp = deci.format(totTaxPaid);
            String tip = deci.format(totIntPaid);
            String mp = deci.format(monthlyPayment);

            Intent myIntent = new Intent(MainActivity.this, Output.class);
            myIntent.putExtra("totalTaxPaid", ttp);
            myIntent.putExtra("totalInterestPaid", tip);
            myIntent.putExtra("monthlyPayment", mp);
            myIntent.putExtra("payOffDate", PayOffDate);
            startActivity(myIntent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.reset:
                reset();
                return true;
            case R.id.action_settings:
//                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void reset(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
