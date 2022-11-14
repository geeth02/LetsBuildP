package com.example.letsbuild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Member_Dash extends AppCompatActivity {
Button logOut;

Button service,pendingAds,approvedAds;
TextView name;
ImageView profilePhoto;
    Bitmap myBitmap;
    GoogleSignInAccount signInAccount;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    private List<String> docId;
    private List<UserInfo> products;
    UserInfo product;
    boolean accountInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_dash);
        logOut=findViewById(R.id.btnLogOut);
        mAuth=FirebaseAuth.getInstance();
        service=findViewById(R.id.addService);
        pendingAds=findViewById(R.id.btnPendingAds);
        approvedAds=findViewById(R.id.btnApprovedAds);
        firebaseFirestore = FirebaseFirestore.getInstance();
        docId= new ArrayList<>();
        products= new ArrayList<>();


     //   name=findViewById(R.id.name);
     //   profilePhoto=findViewById(R.id.profileImage);
       //  signInAccount= GoogleSignIn.getLastSignedInAccount(this);
         //   if(signInAccount.getId()!=null){
            //    loadimage(profilePhoto);
              //  profilePhoto.setImageURI(signInAccount.getPhotoUrl());
             //   name.setText(signInAccount.getDisplayName());

           // }
        //}
        firebaseFirestore.collection("AccountInfo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                        product= snapshot.toObject(UserInfo.class);
                        mAuth= FirebaseAuth.getInstance();
                        if(mAuth.getUid().equals(product.getUserId())) {
                            accountInfo=true;
                            System.out.println(mAuth.getUid());
                            System.out.println(product.getUserId());
                            if (product.getStatus().equals("0")) {
                                Intent intent = new Intent(Member_Dash.this, AccountNotVerifyMassage.class);
                                startActivity(intent);
                                finish();
                            }
                            break;
                        }else{
                            accountInfo=false;
                        }
                    }
                    System.out.println(accountInfo);
                    if(accountInfo!=true){
                        System.out.println(accountInfo);
                        Intent intent = new Intent(Member_Dash.this, Account_info.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });





        approvedAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Member_Dash.this, Approved_Ads.class);
                startActivity(intent);
            }
        });




logOut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(Member_Dash.this, Login.class);
        startActivity(intent);

    }
});
pendingAds.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Member_Dash.this, Pending_Ads_Member.class);
        startActivity(intent);
    }
});


service.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Member_Dash.this, Add_Service.class);
        startActivity(intent);
    }
});

    }


    public void loadimage(View view) {
     //   profilePhoto=findViewById(R.id.profileImage);

        class ImageLoadTask extends AsyncTask<Void, Void, Bitmap>
        {

            private String url;
            private ImageView imageView;

            public ImageLoadTask(String url, ImageView imageView) {
                this.url = url;
                this.imageView = imageView;
            }

            @Override
            protected Bitmap doInBackground(Void... params) {
                try {
                    URL connection = new URL(url);

                    InputStream input = connection.openStream();
                    myBitmap = BitmapFactory.decodeStream(input);
                    Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 100, 100, true);
                    return resized;
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                super.onPostExecute(result);
                imageView.setImageBitmap(result);
            }

        }

                ImageLoadTask obj=new ImageLoadTask(String.valueOf(signInAccount.getPhotoUrl()),profilePhoto);
                obj.execute();




    }


}