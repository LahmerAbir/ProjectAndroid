package com.example.tounsia.English;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tounsia.R;

public class Details22 extends AppCompatActivity {

    TextView storyContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details2);

        storyContent = findViewById(R.id.contentOfRights);
        Intent i = getIntent();
        String title = i.getStringExtra("titleOfRights");
        String content = i.getStringExtra("contentOfRights");
        // getSupportActionBar().setTitle(title);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // set the appbar title as Story title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // set content of the story to textview
        storyContent.setText(content);
        storyContent.setMovementMethod(new ScrollingMovementMethod());

        // enable back button to main activity or recyclerview
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


}
