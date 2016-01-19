package com.example.sowmyap.lcalc;

import android.widget.EditText;

import java.util.Calendar;
import java.util.*;


public class MortgageCalculator {


    public double convertToDouble(EditText v) {
        try {
            return Double.parseDouble(v.getText().toString());
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    public double getTotalPropertyTax( EditText homeValue, String termValue, EditText taxRateValue ) {
        double price = convertToDouble(homeValue);
        double txRate = convertToDouble(taxRateValue);
        System.out.println(price+" "+ txRate);
        int n = Integer.parseInt(termValue);

        if (Double.isNaN(price) || Double.isNaN(txRate)  || n == -1) {
            return Double.NaN;
        }
        double tp = price * (txRate/100) * n;
        System.out.println("getTotalPropertyTax return:"+ tp );

        return (price * (txRate/100) * n );
    }

    public double getMonthlyPropertyTax( EditText homeValue, String termValue, EditText taxRateValue ) {

        int n = Integer.parseInt(termValue);
        int termInMonths = n * 12;
        double totalTax = getTotalPropertyTax( homeValue, termValue, taxRateValue);

        if (Double.isNaN(totalTax) || n == -1) {
            return Double.NaN;
        }
        double mpt =  totalTax/termInMonths ;
        System.out.println("getMonthlyPropertyTax return:"+ mpt );
        return totalTax/termInMonths ;
    }

    public double getTotalInterestPaid(EditText homeValue, EditText downPaymentValue,
                                       EditText interestRateValue, String termValue, EditText taxRateValue ) {

        double  mp = getMonthlyPayment(homeValue, downPaymentValue, interestRateValue, termValue,
                taxRateValue);
        int n = Integer.parseInt(termValue);
        int termInMonths = n * 12;
        double price = convertToDouble(homeValue);
        double downPayment = convertToDouble(downPaymentValue);
        double p = price - downPayment;

        if (Double.isNaN(price) || Double.isNaN(downPayment)  || n == -1) {
            return Double.NaN;
        }
        double ip = (mp * termInMonths) - (p+downPayment) ;
        System.out.println("getTotalInterestPaid return:"+ ip );
        return (mp * termInMonths) - (p+downPayment);

    }

    public double getMonthlyPayment(EditText homeValue, EditText downPaymentValue,
                                    EditText interestRateValue, String termValue, EditText taxRateValue) {

        double monthlyPropertyTax = getMonthlyPropertyTax(homeValue, termValue, taxRateValue );

        double price = convertToDouble(homeValue);
        double downPayment = convertToDouble(downPaymentValue);
        double p = price - downPayment;
        double r = convertToDouble(interestRateValue);
        int n = Integer.parseInt(termValue);
        int termInMonths = n * 12;


        if (Double.isNaN(price) || Double.isNaN(r)  || n == -1) {
            return Double.NaN;
        }
        r = r / 1200;
        double monthlyMortgagePayment =  (p*r) / (1-Math.pow(1+r, -termInMonths));

        double mp = monthlyPropertyTax + monthlyMortgagePayment;
        System.out.println("getMonthlyPayment return:"+ mp );
        return monthlyPropertyTax + monthlyMortgagePayment;
    }

    public String getPayoffDate(String termInYears) {
        int term = Integer.parseInt(termInYears);
        Calendar c = Calendar.getInstance();
        int year = term + c.get(Calendar.YEAR);
        String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String yr= month+" "+String.valueOf(year);
        return yr;
    }

}
