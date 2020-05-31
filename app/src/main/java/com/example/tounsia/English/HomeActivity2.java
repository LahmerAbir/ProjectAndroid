package com.example.tounsia.English;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tounsia.AboutRightActivity;
import com.example.tounsia.AboutVilolenceActivity;
import com.example.tounsia.Call;
import com.example.tounsia.Demande;
import com.example.tounsia.English.Forum.View_Controller.ForumActivity;
import com.example.tounsia.R;
import com.example.tounsia.StatisticActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private ImageView imageView,imageView1,imageView2,imageView3,imageView4,image5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main222);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();




        imageView =findViewById(R.id.image1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, AboutVilolenceActivity2.class);
                startActivity(a);
            }
        });
        imageView1 =findViewById(R.id.image2);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, AboutRightActivity2.class);
                startActivity(a);
            }
        });
        imageView2 =findViewById(R.id.image3);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, ForumActivity.class);
                startActivity(a);
            }
        });
        imageView3 =findViewById(R.id.image4);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, StatisticActivity2.class);
                startActivity(a);
            }
        });

        imageView4 =findViewById(R.id.image5);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, Call2.class);
                startActivity(a);
            }
        });


        image5 =findViewById(R.id.image6);
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, Demande2.class);
                startActivity(a);
            }
        });


    }
}
