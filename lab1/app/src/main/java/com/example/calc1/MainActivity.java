package com.example.calc1;

import static android.graphics.Color.rgb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.*;
import android.content.DialogInterface.OnClickListener;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEqual, btnDiv, btnMult, btnPlus, btnMinus, btn0, btn1, btn2, btn3,
            btn4, btn5, btn6, btn7, btn8, btn9, btnDot;

    private TextView textInput, textPrevOperand, textHistory;

    private boolean isResult = false, isOperatorClicked = false;
    private operators currentOperator;
    private enum operators{
        div,
        mult,
        plus,
        minus
    }
    private double prevOperand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        this.btnEqual = (Button)findViewById(R.id.btnEqual);
        this.btnDiv = (Button)findViewById(R.id.btnDiv);
        this.btnMult = (Button)findViewById(R.id.btnMult);
        this.btnPlus = (Button)findViewById(R.id.btnPlus);
        this.btnMinus = (Button)findViewById(R.id.btnMinus);
        this.btn0 = (Button)findViewById(R.id.btn0);
        this.btn1 = (Button)findViewById(R.id.btn1);
        this.btn2 = (Button)findViewById(R.id.btn2);
        this.btn3 = (Button)findViewById(R.id.btn3);
        this.btn4 = (Button)findViewById(R.id.btn4);
        this.btn5 = (Button)findViewById(R.id.btn5);
        this.btn6 = (Button)findViewById(R.id.btn6);
        this.btn7 = (Button)findViewById(R.id.btn7);
        this.btn8 = (Button)findViewById(R.id.btn8);
        this.btn9 = (Button)findViewById(R.id.btn9);
        this.btnDot = (Button)findViewById(R.id.btnDot);
        this.textInput = (TextView)findViewById(R.id.textInput);
        this.textHistory = (TextView)findViewById(R.id.textHistory);


        this.btnEqual.setOnClickListener(this);
        this.btnDiv.setOnClickListener(this);
        this.btnMult.setOnClickListener(this);
        this.btnPlus.setOnClickListener(this);
        this.btnMinus.setOnClickListener(this);
        this.btn0.setOnClickListener(this);
        this.btn1.setOnClickListener(this);
        this.btn2.setOnClickListener(this);
        this.btn3.setOnClickListener(this);
        this.btn4.setOnClickListener(this);
        this.btn5.setOnClickListener(this);
        this.btn6.setOnClickListener(this);
        this.btn7.setOnClickListener(this);
        this.btn8.setOnClickListener(this);
        this.btn9.setOnClickListener(this);
        this.btnDot.setOnClickListener(this);

    }

    private void operatorClick (String str, operators operator){
        this.currentOperator=operator;
        if (!isOperatorClicked) {
            this.prevOperand = Double.parseDouble(str);
            this.textHistory.setText(str);
            this.isOperatorClicked = true;
        }
    }

    private void numberClick(String str, String c){
        if (isResult) {
            this.prevOperand = Double.parseDouble(str);
            textInput.setText(c);
            this.isResult = false;

        } else if(isOperatorClicked){
            textInput.setText(c);
            //textHistory.setText(textHistory.getText());
            this.isOperatorClicked = false;

        } else {
            if ( !str.equals("0") ) {
                textInput.setText(str + c);
            } else {
                textInput.setText(c);
            }
        }
    }

    @Override
    public void onClick(View v) {
        String str = textInput.getText().toString();
        textInput.setTextColor(rgb(0,0,0));

        if (v.getId() == R.id.btnEqual){
            this.isResult = true;
            double currentOperator = Double.parseDouble(str);

            switch (this.currentOperator){
                case div:
                    if (currentOperator!=0) {
                        textInput.setText(String.valueOf(this.prevOperand / currentOperator));
                    } else {
                        textInput.setHint("Error");
                        textInput.setTextColor(rgb(255,0,0));
                        this.isResult = false;
                    }
                    break;
                case mult:
                    textInput.setText(String.valueOf(this.prevOperand * currentOperator));
                    break;
                case plus:
                    textInput.setText(String.valueOf(this.prevOperand + currentOperator));
                    break;
                case minus:
                    textInput.setText(String.valueOf(this.prevOperand - currentOperator));
                    break;
            }


        } else if (v.getId() == R.id.btnDiv){
            operatorClick(str, operators.div);


        } else if (v.getId() == R.id.btnMult){
            operatorClick(str, operators.mult);

        } else if (v.getId() == R.id.btnPlus){
            operatorClick(str, operators.plus);

        } else if (v.getId() == R.id.btnMinus){
            operatorClick(str, operators.minus);

        } else if (v.getId() == R.id.btn0){
            if ( !str.equals("0") ) {
                numberClick(str, "0");
            }

        } else if (v.getId() == R.id.btn1){
            numberClick(str, "1");

        } else if (v.getId() == R.id.btn2){
            numberClick(str, "2");

        } else if (v.getId() == R.id.btn3){
            numberClick(str, "3");

        } else if (v.getId() == R.id.btn4){
            numberClick(str, "4");

        } else if (v.getId() == R.id.btn5){
            numberClick(str, "5");

        } else if (v.getId() == R.id.btn6){
            numberClick(str, "6");

        } else if (v.getId() == R.id.btn7){
            numberClick(str, "7");

        } else if (v.getId() == R.id.btn8){
            numberClick(str, "8");

        } else if (v.getId() == R.id.btn9){
            numberClick(str, "9");

        } else if (v.getId() == R.id.btnDot){
            if (!str.contains(".")) {
                numberClick(str, ".");
            }
        }

    }
}