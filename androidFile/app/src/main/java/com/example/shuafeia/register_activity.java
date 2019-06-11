package com.example.shuafeia;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class register_activity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password, name;
    Button submit, login;

    private Uri uri;
    ProgressBar progressBar;
    public static final int pick_image = 1;
    ImageView image;

    FirebaseAuth auth;
    com.google.firebase.storage.StorageReference StorageReference;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance().getReference().child("Users");
        StorageReference = FirebaseStorage.getInstance().getReference().child("images");

        uri = null;
        progressBar = (ProgressBar) findViewById(R.id.register_progressBar);
        email = (EditText) findViewById(R.id.register_email_editText);
        name = (EditText) findViewById(R.id.register_name_editText);
        password = (EditText) findViewById(R.id.register_password_editText);
        submit = (Button) findViewById(R.id.register_create_account_button);
        login = (Button) findViewById(R.id.register_close_button);
        image = (ImageView) findViewById(R.id.new_account_imageView);

        submit.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_create_account_button:
                Toast.makeText(register_activity.this, "Registration Completed", Toast.LENGTH_SHORT).show();
                finish();
                //Register();
                break;
            case R.id.register_close_button:
                finish();
                break;
        }
    }
/*
    private void Register() {

        final String user_name = name.getText().toString();
        String EmailTxt = email.getText().toString();
        String PassTxt = password.getText().toString();

        if (uri != null) {

            progressBar.setVisibility(View.VISIBLE);

            if (!TextUtils.isEmpty(user_name) && !TextUtils.isEmpty(EmailTxt) && !TextUtils.isEmpty(PassTxt)) {

                auth.createUserWithEmailAndPassword(EmailTxt, PassTxt).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        Log.d("sabi", "lala");
                        if (task.isSuccessful()) {
                            final String user_id = auth.getCurrentUser().getUid();
                            StorageReference user_profile = StorageReference.child(user_id + ".jpg");

                            user_profile.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> uploadtask) {

                                    uploadtask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                                            String image_uri = downloadUrl.toString();
                                            String token_id =  FirebaseInstanceId.getInstance().getToken();
                                            DatabaseReference user_data = database.child(user_id);
                                            user_data.child("name").setValue(user_name);
                                            user_data.child("user_image").setValue(image_uri);
                                            user_data.child("token_id").setValue(token_id);;
                                            progressBar.setVisibility(View.INVISIBLE);
                                            startActivity(new Intent(register_activity.this, MainActivity.class));
                                            finishAffinity();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(register_activity.this, "Image not Uploaded", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    });
                                }
                            });
                        } else {
                            Toast.makeText(register_activity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pick_image) {
            uri = data.getData();
            image.setImageURI(uri);
        }
    }
*/
}