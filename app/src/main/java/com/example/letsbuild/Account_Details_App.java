package com.example.letsbuild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Account_Details_App extends AppCompatActivity {
    TextView name,nicNumber,phoneNumber,addressLine01,addressLine02,city;
    ImageView nicBack,nicFront;
    String sName,sNicNumber,sPhoneNumber,sAddressLine01,sAddressLine02,sCity,sNicFront,sNicBack,docId;

    FirebaseFirestore dbroot;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details_app);
        nicBack =(ImageView) findViewById(R.id.imgBckNic1);
        nicFront =(ImageView) findViewById(R.id.imgFrontNic1);
        dbroot = FirebaseFirestore.getInstance();
        name=(TextView) findViewById(R.id.viName);
        nicNumber=(TextView) findViewById(R.id.viNicNumber);
        phoneNumber=(TextView) findViewById(R.id.viPhoneNumber);
        addressLine01=(TextView) findViewById(R.id.viAddress01);
        addressLine02=(TextView) findViewById(R.id.viAddress02);
        city=(TextView) findViewById(R.id.viiCity);




        Intent intent= getIntent();
        if(intent.getExtras()!=null){

            sName=intent.getStringExtra("name");
            sNicNumber=intent.getStringExtra("nicNumber");
            sNicFront=intent.getStringExtra("nicFront");
            sNicBack=intent.getStringExtra("nicBack");
            sPhoneNumber=intent.getStringExtra("phoneNumber");
            sCity=intent.getStringExtra("city");
            sAddressLine01=intent.getStringExtra("addressLine01");
            sAddressLine02=intent.getStringExtra("addressLine02");
            docId=intent.getStringExtra("docId");


            System.out.println(docId);
            Glide.with(Account_Details_App.this).load(sNicFront).into(nicFront);
            Glide.with(Account_Details_App.this).load(sNicBack).into(nicBack);

            name.setText(sName);
            nicNumber.setText(sNicNumber);
            phoneNumber.setText(sPhoneNumber);
            city.setText(sCity);
            addressLine01.setText(sAddressLine01);
            addressLine02.setText(sAddressLine02);







        }








    }
}