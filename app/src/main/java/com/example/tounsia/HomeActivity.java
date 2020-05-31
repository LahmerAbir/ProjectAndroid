package com.example.tounsia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tounsia.Forum.View_Controller.ForumActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private ImageView rights,violence,stattistic,call,demande,forum,map,image7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();


        map =findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                //Declaration of explict Intent from MainActivity to LoginActivity
                Intent intent = new Intent(HomeActivity.this, SignInActivity.class);
                //Starting of the Intent
                startActivity(intent);
                finish();
            }
        });

        map =findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, MapActivity.class);
                startActivity(a);
            }
        });

        rights =findViewById(R.id.rights);
        rights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, AboutRightActivity.class);
                startActivity(a);
            }
        });
        violence =findViewById(R.id.violence);
        violence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, AboutVilolenceActivity.class);
                startActivity(a);
            }
        });
        stattistic =findViewById(R.id.stat);
        stattistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, StatisticActivity.class);
                startActivity(a);
            }
        });
        call =findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, Call.class);
                startActivity(a);
            }
        });

        demande =findViewById(R.id.demande);
        demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, Demande.class);
                startActivity(a);
            }
        });


        forum =findViewById(R.id.forum);
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity.this, ForumActivity.class);
                startActivity(a);
            }
        });


    }
}
