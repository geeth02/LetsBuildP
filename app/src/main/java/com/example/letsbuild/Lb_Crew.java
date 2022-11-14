package com.example.letsbuild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Lb_Crew extends AppCompatActivity {
Button addAds,myAds,pendingAds,approvedB,delete,logOut,userAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lb_crew);

        addAds=findViewById(R.id.addServiceAdmin);
        myAds=findViewById(R.id.btnMyAds);
        pendingAds=findViewById(R.id.btnPendingAdsAdmin);
        approvedB=findViewById(R.id.btnApprovedAdsAdmin);
        delete=findViewById(R.id.btnDeleteAds);
        logOut=findViewById(R.id.btnLogOutC);
        userAccount=findViewById(R.id.btnUserAccount);

        userAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lb_Crew.this, Account_Menu.class);
                startActivity(intent);
            }
        });

        addAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lb_Crew.this, Admin_Add_Ads.class);
                startActivity(intent);
            }
        });

        myAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lb_Crew.this, My_Ads.class);
                startActivity(intent);
            }
        });

        pendingAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lb_Crew.this, Admin_Pending_Ads.class);
                startActivity(intent);
            }
        });

        approvedB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lb_Crew.this, View_Admin_Approved_Ads.class);
                startActivity(intent);
            }
        });

       delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lb_Crew.this, Delete_Selection.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lb_Crew.this, selectUser.class);
                startActivity(intent);
            }
        });

    }
}