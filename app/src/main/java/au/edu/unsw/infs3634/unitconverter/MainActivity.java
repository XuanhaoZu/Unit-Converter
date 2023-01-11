package au.edu.unsw.infs3634.unitconverter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Receive message(unit category) from the FirstActivity
        Intent intent = getIntent();
        String message = intent.getStringExtra(FirstActivity.EXTRA_MESSAGE);

        //hide action bar for better UI
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        //initialize textView
        TextView Unit1 = findViewById(R.id.txtUnit1);
        TextView Unit2 = findViewById(R.id.txtUnit2);
        EditText input = findViewById(R.id.txtInput);
        TextView output = findViewById(R.id.txtOutput);
        TextView process = findViewById(R.id.txtProcess);
        TextView symbol1 = findViewById(R.id.txtSymbol1);
        TextView symbol2 = findViewById(R.id.txtSymbol2);
        TextView title = findViewById(R.id.tvTitle);
        title.setText(message);
        TextView category = findViewById(R.id.tvCategory);
        category.setText(message+" CONVERSION");

        //set units randomly
        randomUnit(Unit1, Unit2,symbol1,symbol2);


        //Conversion after click convert button
        Button convert = findViewById(R.id.btnConvert);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //execute conversion
                    process.setText(String.valueOf(convert(Unit1, Unit2, input, output,symbol1,symbol2)));
                }
                //avoid invalid value put in input box
                catch (Exception e){
                    Toast.makeText(MainActivity.this,"Invalid value, please re-enter a number",Toast.LENGTH_SHORT).show();
                    input.setText("");
                    output.setText("");
                    process.setText("");
                }
            }
        });


        //Refresh the Random Unit after click refresh button
        Button refresh = findViewById(R.id.btnRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reset units
                randomUnit(Unit1, Unit2,symbol1,symbol2);
                //clear the edittext
                input.setText("");
                output.setText("");
                process.setText("");
            }
        });


        //Switch units
        ImageButton switchUnit = findViewById(R.id.btnSwitch);
        switchUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String unitName1 = (String) Unit1.getText();
                String unitName2 = (String) Unit2.getText();
                Unit1.setText(unitName2);
                Unit2.setText(unitName1);
                String symbol1Value = (String) symbol1.getText();
                String symbol2Value = (String) symbol2.getText();
                symbol1.setText(symbol2Value);
                symbol2.setText(symbol1Value);
                try {
                    //execute conversion
                    process.setText(String.valueOf(convert(Unit1, Unit2, input, output,symbol1,symbol2)));

                }
                catch (Exception e){
                    //avoid invalid value put in input box and clear the result of conversion
                    output.setText("");
                    process.setText("");
                }
            }
        });


        //Back button for back to the firstActivity(the choose categories screen)
        ImageButton backToFirstActivity = findViewById(R.id.btnBack);
        backToFirstActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });
    }





//Main methods

    //Set Unit randomly(initialize)
    public void randomUnit(TextView Unit1, TextView Unit2, TextView symbol1, TextView symbol2) {
        Intent intent = getIntent();
        String message = intent.getStringExtra(FirstActivity.EXTRA_MESSAGE);
        Unit unit1 = Unit.randomUnit(message);
        Unit unit2 = Unit.randomUnit(message);
        String unitName1 = unit1.getName();
        String unitName2 = unit2.getName();
        //set Unit textView
        while (unitName1.equals(unitName2)) {
            unit2 = Unit.randomUnit(message);
            unitName2 = unit2.getName();
        }

        Unit1.setText(unitName1);
        Unit2.setText(unitName2);
        symbol1.setText(String.valueOf(unit1.getSymbol()));
        symbol2.setText(String.valueOf(unit2.getSymbol()));
    }



    //CONVERT PROCESS
    public String convert(TextView Unit1, TextView Unit2, EditText input, TextView output, TextView symbol1, TextView symbol2){

        String unitName1 = (String) Unit1.getText();
        String unitName2 = (String) Unit2.getText();
        String symbol1Value = (String) symbol1.getText();
        String symbol2Value = (String) symbol2.getText();
        BigDecimal inputValue = new BigDecimal(String.valueOf(input.getText()));
        //Conversion Process
        BigDecimal multiplier = Unit.ConvertStrategy(unitName1, unitName2);
        BigDecimal outputValue = multiplier.multiply(inputValue);
        //Reserving five decimal places
        outputValue = outputValue.setScale(5, BigDecimal.ROUND_HALF_UP);
        output.setText(String.valueOf(outputValue));
        //show the process hint
        String hint = "1"+ symbol1Value +" = "+ multiplier +symbol2Value+"\n" +
                inputValue + symbol1Value + " = " + inputValue +"*"+ multiplier + symbol2Value + " = "+outputValue+ symbol2Value;
        return hint;

    }
}










