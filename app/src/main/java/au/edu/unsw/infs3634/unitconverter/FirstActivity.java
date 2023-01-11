package au.edu.unsw.infs3634.unitconverter;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "UnitCategory.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        //hide action bar for better UI
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //pass the message to mainActivity
        Intent intent = new Intent(FirstActivity.this, MainActivity.class);


        //initialize UI elements
        ImageButton btnWeight = findViewById(R.id.ibWeight);
        ImageButton btnLength = findViewById(R.id.ibLength);
        ImageButton btnTime = findViewById(R.id.ibTime);
        ImageButton btnVelocity = findViewById(R.id.ibVelocity);
        TextView tvWeight = findViewById(R.id.tvWeight);
        TextView tvLength = findViewById(R.id.tvLength);
        TextView tvTime = findViewById(R.id.tvTime);
        TextView tvVelocity = findViewById(R.id.tvVelocity);



        //Click button for enter MainActivity(conversion activity)
        btnWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = tvWeight.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

        btnLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = tvLength.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = tvTime.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });

        btnVelocity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = tvVelocity.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }
}

