package com.example.tounsia.English;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.tounsia.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DemandeSucces2 extends AppCompatActivity {

    Button retour;
    TextView TextVlid;
    String TextSucess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_succes2);
        retour = (Button) findViewById(R.id.retour);
        TextVlid = (TextView) findViewById(R.id.Textvalid);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
        String formattedDate = df.format(c);
        TextSucess="شكايتك وصلتلنا اليوم " + formattedDate + " ستنا منا مكالمة في أقرب وقت" ;

        TextVlid.setText(TextSucess);

        retour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent a = new Intent(DemandeSucces2.this, HomeActivity2.class);
                startActivity(a);
            }

            });




    }
}
