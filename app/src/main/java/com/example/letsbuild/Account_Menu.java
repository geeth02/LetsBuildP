package com.example.letsbuild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Account_Menu extends AppCompatActivity {
Button pendingAccount,appAccount,decAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_menu);

        pendingAccount=(Button)findViewById(R.id.btnPendingAccount);
        appAccount=(Button)findViewById(R.id.btnAppAccount2);
        decAccount=(Button)findViewById(R.id.btnDecAccount3);

        pendingAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account_Menu.this, AccountInformation.class);
                startActivity(intent);
            }
        });

        appAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account_Menu.this, AccountApproved.class);
                startActivity(intent);
            }
        });

        decAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Account_Menu.this, Account_Decline.class);
                startActivity(intent);
            }
        });
    }
}