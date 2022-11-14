package com.example.letsbuild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Delete_Selection extends AppCompatActivity {
Button deleteAds,declineAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_selection);

        deleteAds=findViewById(R.id.btnDeleteS);
        declineAds=findViewById(R.id.btnDeclineS);

        deleteAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Delete_Selection.this,Admin_Delete_Ads.class);
                startActivity(intent);
            }
        });

       declineAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Delete_Selection.this,Admin_Decline_Ads.class);
                startActivity(intent);
            }
        });

    }
}