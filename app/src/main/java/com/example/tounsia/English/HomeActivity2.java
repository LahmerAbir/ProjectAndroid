package com.example.tounsia.English;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tounsia.English.Forum.View_Controller.ForumActivity;
import com.example.tounsia.MapActivity;
import com.example.tounsia.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private ImageView rights,violence,stattistic,call,demande,forum,map,image7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main222);
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();


        map =findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                //Declaration of explict Intent from MainActivity to LoginActivity
                Intent intent = new Intent(HomeActivity2.this, SignInActivity2.class);
                //Starting of the Intent
                startActivity(intent);
                finish();
            }
        });

        map =findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, MapActivity.class);
                startActivity(a);
            }
        });

        rights =findViewById(R.id.rights);
        rights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, AboutRightActivity2.class);
                startActivity(a);
            }
        });
        violence =findViewById(R.id.violence);
        violence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, AboutVilolenceActivity2.class);
                startActivity(a);
            }
        });
        stattistic =findViewById(R.id.stat);
        stattistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, StatisticActivity2.class);
                startActivity(a);
            }
        });
        call =findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, Call2.class);
                startActivity(a);
            }
        });

        demande =findViewById(R.id.demande);
        demande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, Demande2.class);
                startActivity(a);
            }
        });


        forum =findViewById(R.id.forum);
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(HomeActivity2.this, ForumActivity.class);
                startActivity(a);
            }
        });


    }
}
