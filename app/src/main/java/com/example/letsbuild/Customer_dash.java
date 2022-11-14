package com.example.letsbuild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer_dash extends AppCompatActivity {
Button roofing,tiling,civiling,wiring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dash);

        roofing=(Button) findViewById(R.id.btnCtRoofing);
        tiling=(Button) findViewById(R.id.btnCtTiling);
        civiling=(Button) findViewById(R.id.btnCtCiviling);
        wiring=(Button) findViewById(R.id.btnCtWiring);

        roofing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_dash.this, View_Roofing.class);
                startActivity(intent);
            }
        });

        tiling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_dash.this, View_Tiling.class);
                startActivity(intent);
            }
        });

       civiling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_dash.this, View_Civiling.class);
                startActivity(intent);
            }
        });

        wiring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_dash.this, View_Wiring.class);
                startActivity(intent);
            }
        });
    }
}