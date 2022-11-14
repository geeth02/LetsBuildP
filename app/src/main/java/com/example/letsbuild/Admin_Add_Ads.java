package com.example.letsbuild;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Admin_Add_Ads extends AppCompatActivity {
    Spinner s;
    String addCatogry;
    Button addPhoto,submit ;
    EditText title,price,phoneNumber,description;
    FirebaseFirestore dbroot;
    private FirebaseAuth mAuth;
    Uri proPicUri;
    String path;

    ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_ads);
        mAuth=FirebaseAuth.getInstance();

        String[] arraySpinner = new String[] {
                "Roofing", "Tiling", "Wiring", "Civiling"
        };
        s= (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        addPhoto=(Button) findViewById(R.id.btnAddPhoto);
        submit=(Button) findViewById(R.id.btnSubmit);
        title=(EditText)findViewById(R.id.edTitle);
        price=(EditText)findViewById(R.id.edPrice);
        phoneNumber=(EditText)findViewById(R.id.edPhoneNumber);
        description=(EditText)findViewById(R.id.edDescription);
        dbroot=FirebaseFirestore.getInstance();
        photo=(ImageView) findViewById(R.id.imgPhoto);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });


        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                launcher.launch(intent);
            }
        });
    }

    private void insertData() {
        addCatogry= String.valueOf(s.getSelectedItem());
        Map<String, Object> adsdata = new HashMap<>();
        adsdata.put("userId", "Admin");
        adsdata.put("category", addCatogry);
        adsdata.put("photoUri", proPicUri);
        adsdata.put("title", title.getText().toString().trim());
        adsdata.put("price", price.getText().toString().trim());
        adsdata.put("phonenumber", phoneNumber.getText().toString().trim());
        adsdata.put("description", description.getText().toString().trim());
        adsdata.put("status", "1");


        if(addCatogry.equals("Roofing")){
            dbroot.collection("Roofing").add(adsdata)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            title.setText("");
                            price.setText("");
                            phoneNumber.setText("");
                            description.setText("");
                        }
                    });

        }else if(addCatogry.equals("Tiling")){
            dbroot.collection("Tiling").add(adsdata)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            title.setText("");
                            price.setText("");
                            phoneNumber.setText("");
                            description.setText("");
                        }
                    });


        }else if(addCatogry.equals("Wiring")){
            dbroot.collection("Wiring").add(adsdata)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            title.setText("");
                            price.setText("");
                            phoneNumber.setText("");
                            description.setText("");
                        }
                    });
        }else if(addCatogry.equals("Civiling")){
            dbroot.collection("Civiling").add(adsdata)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            title.setText("");
                            price.setText("");
                            phoneNumber.setText("");
                            description.setText("");
                        }
                    });
        }
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.i("IMG", "onActivityResult: came to launcher");
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Uri data = result.getData().getData();
                        addProfilePictureToFirestore(FirebaseStorage.getInstance(), data);
//                            Toast.makeText(getContext(), "came to onActivityResult", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.i("IMG", "onActivityResult: getData is null");
                    }
                }
            });

    private void addProfilePictureToFirestore(FirebaseStorage storage, Uri imgPath) {
        path=mAuth.getUid()+String.valueOf(imgPath);
        ProgressDialog dialog = new ProgressDialog(Admin_Add_Ads.this);

        storage.getReference("advertisment").child(path)
                .putFile(imgPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dialog.dismiss();
                getProPicFromDB(photo);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Admin_Add_Ads.this, "Uploading Faild", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                dialog.setMessage("Uploading Profile Picutre...");
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }

    private void getProPicFromDB( ImageView container){
        // String id = Add_Service.this.getSharedPreferences("user_data", Context.MODE_PRIVATE).getString("id", "");
        StorageReference reference = FirebaseStorage.getInstance().getReference().child("advertisment/" + path);
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.i("SET PRO PIC", "URI: "+ uri);
                Glide.with(Admin_Add_Ads.this).load(uri).into(container);
                proPicUri = uri;
                System.out.println(proPicUri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("SET PRO PIC", "URI: "+ "FAILED");
            }
        });
    }
}

