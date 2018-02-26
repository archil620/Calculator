package com.example.archil.calculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


//Instead of making onclicklistner for single buttons we can implement it on main activity so that we don't have to write it every time, it will need void method of inClick.
 public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

            private Button seven, eight, nine, plus, minus, equal, div, mul, clear, dot, one, two, three, four, five, six, sqrt, allclear, zero,plusminus;
            private Character signOperator;
            private float numberBefore = Float.NaN;
            EditText editText;
            String pro;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                //To find button in the layout

                zero = findViewById(R.id.zero);
                one = findViewById(R.id.one);
                two = findViewById(R.id.two);
                three = findViewById(R.id.three);
                four = findViewById(R.id.four);
                five = findViewById(R.id.five);
                six = findViewById(R.id.six);
                seven = findViewById(R.id.seven);
                eight = findViewById(R.id.eight);
                nine = findViewById(R.id.nine);
                dot = findViewById(R.id.dot);

                allclear = findViewById(R.id.allclear);
                editText = findViewById(R.id.editText);
                clear = findViewById(R.id.clear);

                plusminus= findViewById(R.id.plusminus);
                sqrt = findViewById(R.id.sqrt);
                plus = findViewById(R.id.plus);
                minus = findViewById(R.id.minus);
                equal = findViewById(R.id.equals);
                div = findViewById(R.id.divide);
                mul = findViewById(R.id.multiply);

               //Initial screen declaration
                editText.setText("");

                //Implementing onClickListeners method to all buttons.
                allclear.setOnClickListener(this);
                editText.setOnClickListener(this);
                clear.setOnClickListener(this);

                zero.setOnClickListener(this);
                one.setOnClickListener(this);
                two.setOnClickListener(this);
                three.setOnClickListener(this);
                four.setOnClickListener(this);
                five.setOnClickListener(this);
                six.setOnClickListener(this);
                seven.setOnClickListener(this);
                eight.setOnClickListener(this);
                nine.setOnClickListener(this);
                dot.setOnClickListener(this);

                plus.setOnClickListener(this);
                minus.setOnClickListener(this);
                mul.setOnClickListener(this);
                div.setOnClickListener(this);
                equal.setOnClickListener(this);
                plusminus.setOnClickListener(this);
                sqrt.setOnClickListener(this);

            }

            //this method will call onClick method to perform actions.
            private void setNumberBefore(Character sign) {
                String numBef = editText.getText().toString();
                if (numBef.startsWith(".")) {
                    numBef = "0" + numBef;
                }

                numberBefore = Float.parseFloat(numBef);
                signOperator = sign;
                editText.setText("0");
            }

           //for a default screen.
            private void getKeyboard(String string) {

                String currentString = editText.getText().toString();

                if (currentString.equals("0")) {
                    currentString = "";
                }
            // For preventing users from entering multiple dots(.)
                if (currentString.contains(".")) {
                    if (string.contains(".")) {
                        currentString = "0";
                    } else if (string.contains("..")) {
                        currentString = "0";
                    }
                }
                currentString += string;
                if (currentString.contains("0.")) {
                    editText.setText(currentString);
                }
                editText.setText(currentString);
            }


            private void setResult() {
               //Users to enter second number
                if (!Float.isNaN(numberBefore)) {
                    String numberAft = editText.getText().toString();

                    float numberAfter = Float.parseFloat(numberAft);
                    float finalResult = 0;

                    //Onclick method will perform this operations
                    if (signOperator == '+') {
                        finalResult = numberAfter + numberBefore;
                    }

                    if (signOperator == '-') {
                        finalResult = numberBefore - numberAfter;
                    }

                    if (signOperator == '÷') {
                        finalResult = numberBefore / numberAfter;
                    }

                    if (signOperator == 'X') {
                        finalResult = numberAfter * numberBefore;
                    }

                    if (signOperator == '√') {
                        finalResult = (float)Math.sqrt(numberBefore);
                    }
                     //If number is greter than zero then it will minus it and if it is negative it will make it positive
                    if (signOperator == '±' ) {
                        numberAfter = Float.parseFloat(editText.getText().toString());

                        if(numberAfter >= 0)
                            finalResult = numberBefore * (-1);
                        else
                            finalResult = numberBefore * (+1);
                    }

                    //If user wants to perform one more equation, for eg after 3+2 if he wants +5 this  method will be called.
                    if (signOperator =='='){
                        numberAfter = Float.parseFloat(editText.getText().toString());
                        switch (pro){
                            case "+":
                                finalResult = numberBefore +numberAfter;
                                editText.setText(finalResult+ "");
                                break;
                            case "-":
                                finalResult = numberBefore - numberAfter;
                                editText.setText(finalResult+"");
                                break;
                            case "X":
                                finalResult = numberAfter * numberAfter;
                                editText.setText(finalResult+"");
                                break;
                            case "÷":
                                    finalResult = numberBefore / numberAfter;
                                    editText.setText(finalResult + "");
                                break;
                            case "√":
                                finalResult = (float)Math.sqrt(numberAfter);
                                break;
                            case "±":
                                numberAfter = Float.parseFloat(editText.getText().toString());

                                if(numberAfter >= 0)
                                    finalResult =numberAfter * (-1);
                                else
                                    finalResult = numberAfter;
                        }
                    }

                    //to remove NaN and Infinite error message.
                    if (Float.isInfinite(finalResult) || Float.isNaN(finalResult)) {
                        editText.setText("0");
                    } else {
                        editText.setText(String.valueOf(finalResult));
                    }

                }
            }

            //Getting id of the operators to perform action
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.plus:
                        setNumberBefore('+');
                        pro="+";
                        break;

                    case R.id.minus:
                        setNumberBefore('-');
                        pro = "-";
                        break;

                    case R.id.divide:
                        setNumberBefore('÷');
                        pro = "÷";
                        break;

                    case R.id.multiply:
                        setNumberBefore('X');
                        pro ="X";
                        break;

                    case R.id.allclear:
                        editText.setText("0");
                        numberBefore = 0;
                        break;

                    case R.id.equals:
                        setResult();
                        numberBefore = Float.NaN;
                        break;

                    case R.id.sqrt:
                        setNumberBefore('√');
                        pro="√";
                        break;

                    case R.id.plusminus:
                        setNumberBefore('±');
                        pro="±";
                        break;

                    case R.id.clear:
                        if(!editText.getText().toString().equals("")) {
                            String value = editText.getText().toString();
                            if (value.length() > 0) {
                                value = value.substring(0, value.length() - 1);
                                editText.setText(value);
                            }
                        }
                        break;

                    default:
                        String defaultSign = ((Button) v).getText().toString();
                        getKeyboard(defaultSign);
                        break;
                }
            }
}

