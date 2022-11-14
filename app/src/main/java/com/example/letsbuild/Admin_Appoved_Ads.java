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

public class Admin_Appoved_Ads extends AppCompatActivity {
    ImageView image;
    TextView tTitle,tPrice,tCatogory,tPhoneNumber,tDescription,tStatus;
    String userId;
    String title;
    String description;
    String photoUri;
    String price;
    String category;
    String phonenumber;
    String status,docId;
    Button approve,decline;
    FirebaseFirestore dbroot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appoved_ads);
        image=(ImageView) findViewById(R.id.imgPendingAdds);
        dbroot = FirebaseFirestore.getInstance();
        tTitle=(TextView) findViewById(R.id.lbTitle);
        tPrice=(TextView) findViewById(R.id.lbPrice);
        tCatogory=(TextView) findViewById(R.id.lbCatogory);
        tPhoneNumber=(TextView) findViewById(R.id.lbPhoneNumber);
        tDescription=(TextView) findViewById(R.id.lbDescription);
        tStatus=(TextView) findViewById(R.id.viName);
       approve=(Button) findViewById(R.id.btnPending);
        decline=(Button) findViewById(R.id.btnDeleteM);




        Intent intent= getIntent();
        if(intent.getExtras()!=null){

            description=intent.getStringExtra("description");
            phonenumber=intent.getStringExtra("phonenumber");
            photoUri=intent.getStringExtra("photoUri");
            price=intent.getStringExtra("price");
            category=intent.getStringExtra("category");
            title=intent.getStringExtra("title");
            status=intent.getStringExtra("status");
           docId=intent.getStringExtra("docId");

            Glide.with(Admin_Appoved_Ads.this).load(photoUri).into(image);
            tTitle.setText(title);
            tPrice.setText("LKR."+price+".00");
            tCatogory.setText(category);
            tPhoneNumber.setText(phonenumber);
            tDescription.setText(description);

            if(status.equals("0")){
                tStatus.setText("Pending");
            }else{
                tStatus.setText("Approd");
            }

            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String,Object> approved = new HashMap<>();
                    approved.put("status","1");
                    dbroot.collection(category)
                            .document(docId)
                            .update(approved)
                            .addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(Admin_Appoved_Ads.this, "Approved successfully!", Toast.LENGTH_LONG).show();
                                }
                            });
                }
            });


            decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String,Object> approved = new HashMap<>();
                    approved.put("status","2");
                    dbroot.collection(category)
                            .document(docId)
                            .update(approved)
                            .addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(Admin_Appoved_Ads.this, "Decline successfully!", Toast.LENGTH_LONG).show();
                                }
                            });
                }
            });

        }


    }
}