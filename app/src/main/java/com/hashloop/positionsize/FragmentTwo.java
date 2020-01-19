/*
 * *
 *  * Created by Reynosh Sunny on 16/1/20 3:08 AM
 *  * reynoshsunny@gmail.com
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 16/1/20 2:43 AM
 *
 */

package com.hashloop.positionsize;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {

    double dblAccountsBalance, dblRiskAmount, dblRiskP, dblMaximumLoss;
    double dblQty, dblTarget, dblBuyPrice, dblStopLossAmt, dblStopValue;
    EditText txtQty, txtTarget, txtBuyPrice, txtStopLoss, txtStopLossValue;
    int introundvalue = 0;
    private SharedPreferences prefs;

    public FragmentTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fragment_two, container, false);


        txtQty = root.findViewById(R.id.txtQty);
        txtTarget = root.findViewById(R.id.txtTarget);
        txtBuyPrice = root.findViewById(R.id.txtBuyPrice);
        txtStopLoss = root.findViewById(R.id.txtStopLoss);
        txtStopLossValue = root.findViewById(R.id.txtStopLossValue);

        txtBuyPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ReCalc();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtStopLossValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ReCalc();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        prefs = Objects.requireNonNull(getActivity()).getSharedPreferences(
                "sdsdsds", Context.MODE_PRIVATE);

        dblAccountsBalance = Double.valueOf(prefs.getString(GlobalConstants.ACC_BALANCE, "0"));
        dblRiskP = Double.valueOf(prefs.getString(GlobalConstants.RISK_PERC, "0"));
        introundvalue = Integer.valueOf(prefs.getString(GlobalConstants.RND_MULTIPLE, "1"));

        dblMaximumLoss = (dblAccountsBalance * dblRiskP) / 100;

        if (dblAccountsBalance == 0) {
            Toast.makeText(getContext(), "Account balance is zero", Toast.LENGTH_SHORT).show();
        }

        return root;
    }

    public void ReCalc() {

        String strBuyPrice, strStopLoss, strStopValue;
        strBuyPrice = txtBuyPrice.getText().toString().trim();

        if (strBuyPrice.length() != 0) {
            dblBuyPrice = Double.valueOf(strBuyPrice);
        } else {
            dblBuyPrice = 0;
        }

        strStopValue = txtStopLossValue.getText().toString().trim();
        if (strStopValue.length() != 0) {
            dblStopValue = Double.valueOf(strStopValue);
        } else {
            dblStopValue = 0;
        }

        dblStopLossAmt = dblStopValue - dblBuyPrice;

        txtStopLoss.setText(String.valueOf(dblStopLossAmt));

        dblQty = dblMaximumLoss / dblStopLossAmt;
        if (introundvalue != 1) {
            dblQty = round(dblQty);
        }
        txtQty.setText(String.valueOf(dblQty));

        dblTarget = dblBuyPrice - (2 * dblStopLossAmt);
        txtTarget.setText(String.valueOf(dblTarget));
    }

    double round(double num) {
        return Math.floor((num + introundvalue / 2) / introundvalue) * introundvalue;
    }
}
