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
import android.util.Patterns;
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

public class Add_Service extends AppCompatActivity {
    Spinner s;
    String addCatogry;
    Button addPhoto,submit ;
    EditText title,price,phoneNumber,description;
    FirebaseFirestore dbroot;
    private FirebaseAuth mAuth;
   Uri proPicUri;
   String path;
   String defaultImage="https://firebasestorage.googleapis.com/v0/b/letsbuild-fc7d4.appspot.com/o/advertisment%2FGAoU1MjZhKQZTAnLtluWatps6Xg1content%3A%2Fcom.android.providers.media.documents%2Fdocument%2Fimage%253A16316?alt=media&token=9cb47888-5ba6-4e2d-9919-f97106de5d4e";


    ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
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
        String title1= title.getText().toString().trim();
        String price1= price.getText().toString().trim();
        String phone1= phoneNumber.getText().toString().trim();

        if (title1.isEmpty()) {
            title.setError("Add title is required!");
            title.requestFocus();
            return;
        }

        if (price1.isEmpty()) {
           price.setError("Price is required!");
           price.requestFocus();
            return;
        }
        if (phone1.isEmpty()) {
            price.setError("Phone Number is required!");
            price.requestFocus();
            return;
        }

        if (phone1.length() == 10) {
            phoneNumber.setError("Phone Number length should be 10 characters!");
            phoneNumber.requestFocus();
            return;
        }
        addCatogry= String.valueOf(s.getSelectedItem());
        Map<String, Object> adsdata = new HashMap<>();
        adsdata.put("userId", mAuth.getUid());
        adsdata.put("category", addCatogry);
        adsdata.put("photoUri", proPicUri);
        adsdata.put("title", title.getText().toString().trim());
        adsdata.put("price", price.getText().toString().trim());
        adsdata.put("phonenumber", phoneNumber.getText().toString().trim());
        adsdata.put("description", description.getText().toString().trim());
        adsdata.put("status", "0");


        if(addCatogry.equals("Roofing")){
            dbroot.collection("Roofing").add(adsdata)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            title.setText("");
                            price.setText("");
                            phoneNumber.setText("");
                            description.setText("");
                          Glide.with(Add_Service.this).load(defaultImage).into(photo);
                            Toast.makeText(Add_Service.this, "successfully!", Toast.LENGTH_LONG).show();
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
                            Glide.with(Add_Service.this).load(defaultImage).into(photo);
                            Toast.makeText(Add_Service.this, "successfully!", Toast.LENGTH_LONG).show();
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
                            Glide.with(Add_Service.this).load(defaultImage).into(photo);
                            Toast.makeText(Add_Service.this, "successfully!", Toast.LENGTH_LONG).show();

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
                            Glide.with(Add_Service.this).load(defaultImage).into(photo);
                            Toast.makeText(Add_Service.this, "successfully!", Toast.LENGTH_LONG).show();
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
        ProgressDialog dialog = new ProgressDialog(Add_Service.this);

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
                Toast.makeText(Add_Service.this, "Uploading Faild", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                dialog.setMessage("Uploading  Picutre...");
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
                Glide.with(Add_Service.this).load(uri).into(container);
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

