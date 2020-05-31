package com.example.tounsia.English;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tounsia.AboutRightActivity;
import com.example.tounsia.HomeActivity;
import com.example.tounsia.R;

public class AboutRightActivity2 extends AppCompatActivity {
    RecyclerView recycleView;
    com.example.tounsia.English.Adapter adapter;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_right2);


        String[] titles = getResources().getStringArray(R.array.rights_title2);
        String[] contents = getResources().getStringArray(R.array.rights_content2);


        recycleView = findViewById(R.id.storiesListsView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, titles,contents);
        recycleView.setAdapter(adapter);

        back =findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AboutRightActivity2.this, HomeActivity2.class);
                startActivity(a);
            }
        });





    }}
