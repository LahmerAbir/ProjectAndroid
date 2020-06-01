package com.example.tounsia.Forum.View_Controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tounsia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity {
    private ImageView setupImage;
    private Uri mainImageURI = null;
    private EditText setupName;
    private Button setupBtn;
    private String user_id;
    private boolean isChanged = false;
    private ProgressBar setupProgress;

    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);


        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user_id = firebaseAuth.getCurrentUser().getUid();

        firebaseFirestore = FirebaseFirestore.getInstance();

        setupImage = findViewById(R.id.setup_image);

        setupName = findViewById(R.id.setup_name);
        setupBtn = findViewById(R.id.setup_btn);
        setupProgress = findViewById(R.id.setup_progress);
        setupProgress.setVisibility(View.VISIBLE);
        setupBtn.setEnabled(false);
        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    if (task.getResult().exists()) {
                        String name = task.getResult().getString("name");

                        setupName.setText(name);

                    }
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(SetupActivity.this, "firestore Retrieval Error "+error, Toast.LENGTH_SHORT).show();
                }
                setupProgress.setVisibility(View.INVISIBLE);
                setupBtn.setEnabled(true);
            }
        });
        setupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String user_name = setupName.getText().toString().trim();

                if (!TextUtils.isEmpty(user_name)) {
                    setupProgress.setVisibility(View.VISIBLE);
                    storeFirestore(user_name);

                 }
            }
        });


    }

    private void storeFirestore(String user_name) {

        Map<String, String> userMap = new HashMap<>();
        userMap.put("name", user_name);
        firebaseFirestore.collection("Users").document(user_id).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(SetupActivity.this, "User Settings Updated", Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(SetupActivity.this,ForumActivity.class);
                    startActivity(mainIntent);
                    finish();

                }else{
                    Toast.makeText(SetupActivity.this, "Fire base Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                setupProgress.setVisibility(View.INVISIBLE);
            }
        });

    }



}
