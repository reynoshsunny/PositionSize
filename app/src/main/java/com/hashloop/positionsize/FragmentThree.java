/*
 * *
 *  * Created by Reynosh Sunny on 16/1/20 3:08 AM
 *  * reynoshsunny@gmail.com
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 16/1/20 2:30 AM
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

import androidx.fragment.app.Fragment;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    EditText txtBalance, txtRiskAmt, txtRiskPerc, txtMaxLoss, txtRndMultiple;
    double dblBalance, dblRiskP, dblRiskAmt;

    public FragmentThree() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_fragment_three, container, false);
        prefs = Objects.requireNonNull(getActivity()).getSharedPreferences(
                "sdsdsds", Context.MODE_PRIVATE);

        txtBalance = root.findViewById(R.id.txtBalance);
        txtRiskAmt = root.findViewById(R.id.txtRiskAmt);
        txtRiskPerc = root.findViewById(R.id.txtRiskPerc);
        txtMaxLoss = root.findViewById(R.id.txtMaxLoss);
        txtRndMultiple = root.findViewById(R.id.txtRndMultiple);

        txtBalance.setText(prefs.getString(GlobalConstants.ACC_BALANCE, "0"));
        txtRiskAmt.setText(prefs.getString(GlobalConstants.RISK_AMOUNT, "0"));
        txtRiskPerc.setText(prefs.getString(GlobalConstants.RISK_PERC, "0"));
        txtMaxLoss.setText(prefs.getString(GlobalConstants.MAX_LOSS, "0"));
        txtRndMultiple.setText(prefs.getString(GlobalConstants.RND_MULTIPLE, "1"));


        txtBalance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor = prefs.edit();
                if (txtBalance.getText().toString().trim().length() != 0) {
                    editor.putString(GlobalConstants.ACC_BALANCE, txtBalance.getText().toString().trim());
                }
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtRiskAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor = prefs.edit();
                if (txtRiskAmt.getText().toString().trim().length() != 0) {
                    editor.putString(GlobalConstants.RISK_AMOUNT, txtRiskAmt.getText().toString().trim());
                }
                editor.apply();

            /*    String strBalance;
                strBalance = txtBalance.getText().toString().trim();

                double dblBalance = 0;
                if (strBalance.length() != 0) {
                    dblBalance = Double.valueOf(strBalance);
                } else {
                    dblBalance = 0;
                }

                String strValue;
                strValue = txtRiskAmt.getText().toString().trim();
                double dblValue = 0;
                if (strValue.length() != 0) {
                    dblValue = Double.valueOf(strValue);
                } else {
                    dblValue = 0;
                }

                double dblfinal = (dblValue / dblBalance) * 100;

                txtRiskPerc.setText(String.valueOf(dblfinal));
*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtRiskPerc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor = prefs.edit();
                if (txtRiskPerc.getText().toString().trim().length() != 0) {
                    editor.putString(GlobalConstants.RISK_PERC, txtRiskPerc.getText().toString().trim());
                }
                editor.apply();

                String strBalance;
                strBalance = txtBalance.getText().toString().trim();

                double dblBalance = 0;
                if (strBalance.length() != 0) {
                    dblBalance = Double.valueOf(strBalance);
                } else {
                    dblBalance = 0;
                }

                String strValue;
                strValue = txtRiskPerc.getText().toString().trim();
                double dblValue = 0;
                if (strValue.length() != 0) {
                    dblValue = Double.valueOf(strValue);
                } else {
                    dblValue = 0;
                }

                double dblfinal = (dblBalance * dblValue) / 100;

                txtRiskAmt.setText(String.valueOf(dblfinal));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtMaxLoss.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor = prefs.edit();
                if (txtMaxLoss.getText().toString().trim().length() != 0) {
                    editor.putString(GlobalConstants.MAX_LOSS, txtMaxLoss.getText().toString().trim());
                }
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtRndMultiple.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor = prefs.edit();
                if (txtRndMultiple.getText().toString().trim().length() != 0) {
                    editor.putString(GlobalConstants.RND_MULTIPLE, txtRndMultiple.getText().toString().trim());
                }
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return root;
    }

}
