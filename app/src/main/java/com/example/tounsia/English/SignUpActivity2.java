package com.example.tounsia.English;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tounsia.Forum.View_Controller.SetupActivity;
import com.example.tounsia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity2 extends AppCompatActivity {

    // These are the EditTexts,Buttons and Progressbar that are defined in the activity_register.xml
    // These elements are declared as private and will be later binded to the ids of the specific elements which are given in
    // activity_register.xml
    private EditText regemailText;
    private EditText regPasstext;
    private Button regBtn;
    private EditText  usernameReg,numeroReg,payReg;
    private Button regLoginBtn;
    private ProgressBar regProgress;

    //FireBaseAuth class is a public abstract class FirebaseAuth extends Object
    // and first we will obtain an instance of this class by calling getInstance() which we will see later.
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        usernameReg = findViewById(R.id.regusername);
        regPasstext = findViewById(R.id.regPassword);
        regemailText = findViewById(R.id.regemail);

        regBtn = findViewById(R.id.validate);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regemailText.getText().toString().trim();
                String pass = regPasstext.getText().toString().trim();
                if(!emailVerify(email)){
                    Toast.makeText(SignUpActivity2.this, "Invalid email", Toast.LENGTH_SHORT).show();

                }else{
                    if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
                        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            //On complete and there are two possibilities success or failure
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //If the task is sucessful then send the user to SetUpActivity using Explict intent
                                if (task.isSuccessful()) {
                                    String user_id = mAuth.getCurrentUser().getUid();
                                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                                    String username = usernameReg.getText().toString();
                                    String email = regemailText.getText().toString();

                                    Map newPost = new HashMap();
                                    newPost.put("username", username);
                                    newPost.put("email", email);


                                    current_user_db.setValue(newPost);

                                    //Declared an Explict Intent from RegisterActivity to SetUpActivity
                                    Intent setUpIntent = new Intent(SignUpActivity2.this, SignInActivity2.class);
                                    //Started Explict Intent
                                    finish();
                                }
                                else {
                                    Toast.makeText(SignUpActivity2.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else {
                        Toast.makeText(SignUpActivity2.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

    }
    @Override
    protected void onStart() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            sendTomain();
        }
        super.onStart();
    }
    private boolean emailVerify(String email){
        int flag=0,f=0;
        for(int i=0;i<email.length();i++){
            if(email.charAt(i) == '@'){
                flag=1;
            } else if(email.charAt(i) == '.' && flag==1){
                flag=2;
            } else if((flag==2 && (int)email.charAt(i) >= 97 && (int)email.charAt(i) <= 122)){
                f=1;
            } else if(f==1){
                return false;
            }
        }
        if(f==1){
            return true;
        } else{
            return false;
        }
    }

    private void sendTomain() {
        Intent mainIntent = new Intent(SignUpActivity2.this, VerifyPhoneActivity2.class);
        startActivity(mainIntent);
        finish();

    }
}
