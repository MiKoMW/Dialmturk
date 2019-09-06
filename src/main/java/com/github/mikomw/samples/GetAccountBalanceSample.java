package com.github.mikomw.samples;

import com.github.mikomw.MturkClient;

public class GetAccountBalanceSample {

    public static void main(String[] args){

        MturkClient mturkClient = new MturkClient(true);
        String balance = mturkClient.getAccountBalance();
        System.out.println("My account balance is: " + balance + ".");

    }

}