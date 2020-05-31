package com.example.tounsia.English;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.example.tounsia.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DemandeP22 extends AppCompatActivity {

    CheckBox checkOui , checkNo;
    FirebaseDatabase rootNode ;
    DatabaseReference ref;
    ImageButton back;
    LinearLayout VisLayout;
    Button importe , demande;
    String AdressMap="" ,Histoire , Violence="" , Personne=" ";
    EditText Histo;
    String name ="" , tel;
    Boolean Rep;
    String formattedDate;
    FusedLocationProviderClient fusedLocationProviderClient;
    private String current_user_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_p22);

        checkOui = (CheckBox) findViewById(R.id.Oui);
        checkNo = (CheckBox) findViewById(R.id.No);
        importe = (Button) findViewById(R.id.importe);
        demande = (Button) findViewById(R.id.demande);
        Histo = (EditText) findViewById(R.id.Histo);
        VisLayout = (LinearLayout) findViewById(R.id.VisLayout);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);



        ///////////////////////////////////////////////////////////////////////






//////////////////////////////////////////////



        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //if (user != null) {
        // Name, email address, and profile photo Url
        // name = user.getDisplayName();
        //current_user_id = mAuth.getCurrentUser().getUid();




        /////////////////////////////////////////////////////////

        checkOui.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    VisLayout.setVisibility(v.VISIBLE);
                }
                else
                    VisLayout.setVisibility(v.INVISIBLE);

            }
        });

        demande.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                ref = rootNode.getReference("DemandeUser");

                if(ActivityCompat.checkSelfPermission(DemandeP22.this
                        , Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    //getLocation
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {

                            Location location = task.getResult();
                            if(location!= null ){

                                try {


                                    Geocoder geocoder = new Geocoder(DemandeP22.this,
                                            Locale.getDefault());


                                    List<Address> addresses = geocoder.getFromLocation(
                                            location.getLatitude(),location.getLongitude(),1
                                    );

                                    AdressMap = addresses.get(0).getAddressLine(0);

                                    Histoire =Histo.getText().toString();

                                    Violence= getIntent().getStringExtra("VIOL");
                                    Personne = getIntent().getStringExtra("PERSO");
                                    name = getIntent().getStringExtra("NAME");
                                    tel = getIntent().getStringExtra("PHONE");


                                    DemandeHelper2 demandeHelper = new DemandeHelper2(Violence,Personne,Histoire,AdressMap,name,formattedDate,tel);

                                    ref.child(name).push().setValue(demandeHelper);
                                    Intent a = new Intent(DemandeP22.this, DemandeSucces2.class);
                                    startActivity(a);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else {
                                openDialogRefus();


                            }


                        }
                    });

                }
                else
                {
                    ActivityCompat.requestPermissions(    DemandeP22.this
                            ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);


                }


            }

        });


    }

    public void openDialogRefus() {
        MessageRefus2 exampleDialog = new MessageRefus2();
        exampleDialog.show(getSupportFragmentManager(), "Dialog Refus");
    }

    public void openDialogSuccess() {
        MessagheSuccess2 exampleDialog = new MessagheSuccess2();
        exampleDialog.show(getSupportFragmentManager(), "Dialog Success");
    }
}
