package com.example.sowmyap.lcalc;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Output extends AppCompatActivity {

    private EditText taxPaidValue;
    private EditText interestPaidValue;
    private EditText monthlyPaymentValue;
    private EditText payOffDateValue;

    private final String ERROR = "Error - Please enter valid numerical inputs.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output_layout);

        Intent intent = getIntent();


        String ttp = intent.getStringExtra("totalTaxPaid");
        String tip = intent.getStringExtra("totalInterestPaid");
        String mp = intent.getStringExtra("monthlyPayment");
        String pod = intent.getStringExtra("payOffDate");

        taxPaidValue = (EditText) findViewById(R.id.totaltaxpaidEditText);
        interestPaidValue = (EditText) findViewById(R.id.totalinterestpaidEditText);
        monthlyPaymentValue = (EditText) findViewById(R.id.monthlypaymentEditText);
        payOffDateValue = (EditText) findViewById(R.id.payoffdateEditText);

        taxPaidValue.setKeyListener(null);
        interestPaidValue.setKeyListener(null);
        monthlyPaymentValue.setKeyListener(null);
        payOffDateValue.setKeyListener(null);

        String totTaxPaid = String.valueOf(ttp);
        String totIntPaid = String.valueOf(tip);
        String monthlyPay = String.valueOf(mp);
        String dateResult = String.valueOf(pod);


        taxPaidValue.setText(totTaxPaid);
        interestPaidValue.setText(totIntPaid);
        monthlyPaymentValue.setText(monthlyPay);
        payOffDateValue.setText(dateResult);

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
        Intent myIntent = new Intent(Output.this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }
}