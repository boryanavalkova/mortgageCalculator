package com.company;

import java.text.NumberFormat;

public class MortgageCalculator {

    private int principal;
    private float annualRate;
    private byte period;

    public MortgageCalculator(int principal, float annualRate, byte period) {
        this.principal = principal;
        this.annualRate = annualRate;
        this.period = period;
    }

    public double calculateBalance(short numberOfPaymentsMade){
        int numberOfPaymentsCalculate = getNumberOfPaymentsCalculate();
        float monthlyRateCalculate = getMonthlyRateCalculate();

        double balance = principal * (Math.pow(1+monthlyRateCalculate, numberOfPaymentsCalculate) - Math.pow(1 + monthlyRateCalculate, numberOfPaymentsMade))
                / (Math.pow(1+monthlyRateCalculate, numberOfPaymentsCalculate) -1);
        return balance;
    }

    public double calculateMortgage() {

        int numberOfPaymentsCalculate = getNumberOfPaymentsCalculate();
        float monthlyRateCalculate = getMonthlyRateCalculate();

        double mortgage = principal * (monthlyRateCalculate * Math.pow(1 + monthlyRateCalculate, numberOfPaymentsCalculate))
                / (Math.pow(1 + monthlyRateCalculate, numberOfPaymentsCalculate) - 1);

        return mortgage;
    }

    public double[] getRemainingBalances(){
        var balances = new double[getNumberOfPaymentsCalculate()];
        for (short month = 1; month <= balances.length; month++){
            balances[month - 1] = calculateBalance(month);
        }
        return balances;
    }

    private int getNumberOfPaymentsCalculate() {
        return period * 12;
    }

    private float getMonthlyRateCalculate() {
        return annualRate / 1200;
    }
}
