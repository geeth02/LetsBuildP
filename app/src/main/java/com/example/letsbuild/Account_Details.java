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

public class Account_Details extends AppCompatActivity {
    TextView name,nicNumber,phoneNumber,addressLine01,addressLine02,city,status;
    ImageView nicBack,nicFront;
    String sName,sNicNumber,sPhoneNumber,sAddressLine01,sAddressLine02,sCity,sNicFront,sNicBack,sStatus,docId;
    Button approved,dicline;
    FirebaseFirestore dbroot;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        nicBack =(ImageView) findViewById(R.id.imgBckNic1);
        nicFront =(ImageView) findViewById(R.id.imgFrontNic1);
        dbroot = FirebaseFirestore.getInstance();
        name=(TextView) findViewById(R.id.viName);
        nicNumber=(TextView) findViewById(R.id.viNicNumber);
        phoneNumber=(TextView) findViewById(R.id.viPhoneNumber);
        addressLine01=(TextView) findViewById(R.id.viAddress01);
        addressLine02=(TextView) findViewById(R.id.viAddress02);
        city=(TextView) findViewById(R.id.viiCity);
        status=(TextView) findViewById(R.id.viStatus);

        approved=(Button) findViewById(R.id.brnAprovedA);
        dicline=(Button) findViewById(R.id.btnDeclineA);

        Intent intent= getIntent();
        if(intent.getExtras()!=null){

            sName=intent.getStringExtra("name");
            sNicNumber=intent.getStringExtra("nicNumber");
            sNicFront=intent.getStringExtra("nicFront");
            sNicBack=intent.getStringExtra("nicBack");
            sPhoneNumber=intent.getStringExtra("phoneNumber");
            sStatus=intent.getStringExtra("status");
            sCity=intent.getStringExtra("city");
            sAddressLine01=intent.getStringExtra("addressLine01");
            sAddressLine02=intent.getStringExtra("addressLine02");
            docId=intent.getStringExtra("docId");


            System.out.println(docId);
            Glide.with(Account_Details.this).load(sNicFront).into(nicFront);
            Glide.with(Account_Details.this).load(sNicBack).into(nicBack);

            name.setText(sName);
            nicNumber.setText(sNicNumber);
            phoneNumber.setText(sPhoneNumber);
            city.setText(sCity);
            addressLine01.setText(sAddressLine01);
            addressLine02.setText(sAddressLine02);


            if(sStatus.equals("0")){
                status.setText("Pending");
            }else if(status.equals("1")){
                status.setText("Approved");
            }else if(status.equals("2")){
                status.setText("Decline");
            }




        }

        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> approved = new HashMap<>();
                approved.put("status","1");
                dbroot.collection("AccountInfo")
                        .document(docId)
                        .update(approved)
                        .addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(Account_Details.this, "Approved successfully!", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });


        dicline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> approved = new HashMap<>();
                approved.put("status","2");
                dbroot.collection("AccountInfo")
                        .document(docId)
                        .update(approved)
                        .addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(Account_Details.this, "Decline successfully!", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });




    }
}