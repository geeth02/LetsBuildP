package com.example.letsbuild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Account_Decline extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private List<UserInfo> products;
    private List<String> docId;
    UserInfo product;
    private GridView productView;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_account_decline);
        firebaseFirestore = FirebaseFirestore.getInstance();
        products= new ArrayList<>();
        docId= new ArrayList<>();
        productView=(GridView) findViewById(R.id.accountDecList);

        addProduct();

        productView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Account_Adapter adapter = new Account_Adapter(Account_Decline.this,products);

                UserInfo p1 =(UserInfo) adapter.getItem(i);
                Intent intent = new Intent(Account_Decline.this,Account_Details_App.class)
                        .putExtra("name",p1.getName())
                        .putExtra("nicNumber",p1.getNicNumber())
                        .putExtra("nicFront",p1.getNicFront())

                        .putExtra("nicBack",p1.getNicBack())
                        .putExtra("phoneNumber",p1.getPhoneNumber())
                        .putExtra("status",p1.getStatus())
                        .putExtra("city",p1.getCity())
                        .putExtra("addressLine01",p1.getAddressLine01())
                        .putExtra("addressLine02",p1.getAddressLine02())
                        .putExtra("docId",docId.get(i))
                        ;
                System.out.println(p1.getNicFront());
                startActivity(intent);
            }
        });
    }

    private void addProduct() {
        Account_Adapter adapter = new Account_Adapter(Account_Decline.this,products);
        productView.setAdapter(adapter);
        firebaseFirestore.collection("AccountInfo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                        product= snapshot.toObject(UserInfo.class);
                        mAuth= FirebaseAuth.getInstance();
                        if (product.getStatus().equals("2")) {
                            docId.add(snapshot.getId());

                            products.add(product);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

            }
        });

    }
}