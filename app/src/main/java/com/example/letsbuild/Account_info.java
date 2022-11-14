package com.example.letsbuild;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Account_info extends AppCompatActivity {
Button nicFront,nicBack,adddata;
ImageView phNicFront,phNicBack;
    FirebaseFirestore dbroot;
    private FirebaseAuth mAuth;
    Uri uriFront;
    String path;
    String pathBack;
    Uri uriBack;
    EditText nicNumber,name,phoneNumber,adLine01,adLine02,city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
        phNicFront=(ImageView) findViewById(R.id.imgNicFont);
        phNicBack=(ImageView) findViewById(R.id.imgNicBack);
        nicFront=(Button) findViewById(R.id.btnFront);
        nicBack=(Button) findViewById(R.id.btnBack);
        adddata=(Button) findViewById(R.id.btnAddInfo);
        dbroot=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();


        nicNumber=(EditText) findViewById(R.id.eTNic);
        name=(EditText) findViewById(R.id.eTName);
        phoneNumber=(EditText) findViewById(R.id.eTPhoneNum);
        adLine01=(EditText) findViewById(R.id.eTAddress1);
        adLine02=(EditText) findViewById(R.id.eTAddress2);
        city=(EditText) findViewById(R.id.eTCity);

        nicFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                launcher.launch(intent);
            }
        });

        nicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                launcher1.launch(intent);
            }
        });

        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });



    }

    private void addData() {

        String sNicNumber= nicNumber.getText().toString().trim();
        String sName= name.getText().toString().trim();
        String sPhoneNumber= phoneNumber.getText().toString().trim();
        String sAddressLine01= adLine01.getText().toString().trim();
        String sAddressLine02= adLine02.getText().toString().trim();
        String sCity= city.getText().toString().trim();

        if (sNicNumber.isEmpty()) {
            nicNumber.setError("NIC Number is required!");
            nicNumber.requestFocus();
            return;
        }

        if (sName.isEmpty()) {
            name.setError("Name is required!");
            name.requestFocus();
            return;
        }

        if (sPhoneNumber.isEmpty()) {
            phoneNumber.setError("Phone Number is required!");
            phoneNumber.requestFocus();
            return;
        }

        if (sAddressLine01.isEmpty()) {
            adLine01.setError("Address is required!");
            adLine01.requestFocus();
            return;
        }

        if (sCity.isEmpty()) {
            city.setError("City is required!");
            city.requestFocus();
            return;
        }


        Map<String, Object> accountInfo = new HashMap<>();
        accountInfo.put("userId", mAuth.getUid());
        accountInfo.put("name", name.getText().toString().trim());
        accountInfo.put("phoneNumber", phoneNumber.getText().toString().trim());
        accountInfo.put("nicNumber",nicNumber.getText().toString().trim());
        accountInfo.put("addressLine01", adLine01.getText().toString().trim());
        accountInfo.put("addressLine02", adLine02.getText().toString().trim());
        accountInfo.put("city", city.getText().toString().trim());
        accountInfo.put("nicFront", uriFront);
        accountInfo.put("nicBack", uriBack);
        accountInfo.put("status", "0");

        dbroot.collection("AccountInfo").add(accountInfo)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(Account_info.this, "successfully!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Account_info.this, AccountNotVerifyMassage.class);
                        startActivity(intent);
                    }
                });

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
        ProgressDialog dialog = new ProgressDialog(Account_info.this);

        storage.getReference("nicfront").child(path)
                .putFile(imgPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dialog.dismiss();
                getProPicFromDB(phNicFront);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Account_info.this, "Uploading Faild", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                dialog.setMessage("Uploading NIC Picutre...");
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }


    private void getProPicFromDB( ImageView container){
        // String id = Add_Service.this.getSharedPreferences("user_data", Context.MODE_PRIVATE).getString("id", "");
        StorageReference reference = FirebaseStorage.getInstance().getReference().child("nicfront/" + path);
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.i("SET PRO PIC", "URI: "+ uri);
                Glide.with(Account_info.this).load(uri).into(container);
                uriFront = uri;
                System.out.println(uriFront);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("SET PRO PIC", "URI: "+ "FAILED");
            }
        });
    }



//---------------------------------------------------------------------------------------------------------------------------------
    //Nic Back----------------------------------------------------------
    //--------------------------------------------------------------


    ActivityResultLauncher<Intent> launcher1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.i("IMG", "onActivityResult: came to launcher");
                    if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null){
                        Uri data = result.getData().getData();
                        addProfilePictureToFirestore1(FirebaseStorage.getInstance(), data);
//                            Toast.makeText(getContext(), "came to onActivityResult", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.i("IMG", "onActivityResult: getData is null");
                    }
                }
            });


    private void addProfilePictureToFirestore1(FirebaseStorage storage, Uri imgPath) {
        pathBack=mAuth.getUid()+String.valueOf(imgPath);
        ProgressDialog dialog = new ProgressDialog(Account_info.this);

        storage.getReference("nicBack").child(path)
                .putFile(imgPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dialog.dismiss();
                getProPicFromDB1(phNicBack);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Account_info.this, "Uploading Faild", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                dialog.setMessage("Uploading NIC Picutre...");
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }


    private void getProPicFromDB1( ImageView container){
        // String id = Add_Service.this.getSharedPreferences("user_data", Context.MODE_PRIVATE).getString("id", "");
        StorageReference reference = FirebaseStorage.getInstance().getReference().child("nicBack/" + path);
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.i("SET PRO PIC", "URI: "+ uri);
                Glide.with(Account_info.this).load(uri).into(container);
                uriBack = uri;
                System.out.println(uriFront);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("SET PRO PIC", "URI: "+ "FAILED");
            }
        });
    }
}