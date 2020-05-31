package com.example.tounsia;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tounsia.Forum.View_Controller.ForumActivity;
import com.example.tounsia.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignInActivity extends AppCompatActivity {

    // These are the EditTexts,Buttons and Progressbar that are defined in the activitylogin.xml
    // These elements are declared as private and will be later binded to the ids of the specific elements which are given in
    // activitylogin.xml

    private EditText loginemailText;
    private EditText loginPasstext;
    private Button loginBtn;
    private Button loginRegBtn;

    //FireBaseAuth class is a public abstract class FirebaseAuth extends Object
    // and first we will obtain an instance of this class by calling getInstance() which we will see later.
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Getting an instance of this class for authentication by calling getInstance() method,
        // after calling this method we will be able to firebase features
        mAuth = FirebaseAuth.getInstance();

        //We are stting the view to the specific layout when this Java class is called.
        // In this we are setting content view to layout activitylogin.xml
        setContentView(R.layout.activity_sign_in);

        //As said before we binded our private elements to the ids that are to be connected which are declaredin layout file
        loginemailText = findViewById(R.id.regusername);
        loginPasstext = findViewById(R.id.motdepass);
        loginBtn = findViewById(R.id.go);
        loginRegBtn = findViewById(R.id.signup);

        //we have set  on click listener on the button login button when it is clicked if the login is successful it will send to mianActivity
        //else it will throws an error message according to the error that occured
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //we are taking the email and password from the given two edit texts and storing them in the two
                // strings loginEmail and loginPass which are declared locally to check in the Firebase.
                String loginEmail = loginemailText.getText().toString().trim();
                String loginPass = loginPasstext.getText().toString().trim();

                //checking if the loginEmail and loginPass are not empty ,if not empty then go on
                if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)) {

                    //set the progressbar visible so that user can know that process in running

                    //Authentication of FireBase provides with various kinds of methods for login like
                    //loginWithMailandPassword etc....here we used a method signInWithEmailAndPassword which we
                    //passed loginEmail and loginPass th that method and we add on complete listener if it is complete then do the following things
                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        //On complete and there are two possibilities success or failure
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //If the task is sucessful then send the user to MainActicity using Explict intent
                            if (task.isSuccessful()) {

                                sendToMain();
                            }

                            //If the task is not sucessful then give a toast with the error message provided by the task
                            else {
                                Toast.makeText(SignInActivity.this,
                                        task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            //After on completion make the progress bar invisible by writing the following code of line

                        }
                    });

                }else{
                    Toast.makeText(SignInActivity.this, "Email or Password can't be empty", Toast.LENGTH_SHORT).show();
                    if(TextUtils.isEmpty(loginEmail)){
                        loginemailText.getError();
                    }else if(TextUtils.isEmpty(loginPass)){
                        loginPasstext.getError();
                    }else{
                        loginemailText.getError();
                        loginPasstext.getError();

                    }
                }

            }
        });

        // loginRegBtn is a button when clicks takes the user to registerActivity to register the user and by entering details
        loginRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Explict Intent from LoginActicity to RegisterActivity
                Intent regIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                //Started the Intent
                startActivity(regIntent);
            }
        });


    }


    //This is onStart method.This method will works as soon as this activity is started and check if
    //user is logged in and if the user is logged in, it will send to MainActivity otherwise it will send to LoginActcivity
    @Override
    protected void onStart() {

        //super is called to restore the previous super class onStart()data
        super.onStart();

        //FirebaseUser class is public abstract class FirebaseUser extends Object
        //implements Parcelable UserInfo which is used in helping to check if the user is logged in or not
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(SignInActivity.this, "مرحبى", Toast.LENGTH_SHORT).show();

            //If user is logged in then send him to the MianActivity
            sendToMain();

        }
    }

    // the sendToMain() class uses an Explict Intent to jump from LoginActicity to MainActicity
    private void sendToMain() {
        //Declaration of Explicit Intent from LoginActivity to MainActicity
        Intent mainIntent = new Intent(SignInActivity.this, HomeActivity.class);
        //Starting the mainIntent
        startActivity(mainIntent);
        //finish() method is called because to finish the present layout so that when a user presses back button it will exists
        // from app rather than showing Login layout if the user is logged in
        finish();
    }
}
