package com.example.tounsia.English;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tounsia.HomeActivity;
import com.example.tounsia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class VerifyPhoneActivity2 extends AppCompatActivity {
        private CountryCodePicker ccp;
        private EditText phoneText;
        private EditText codeText;
        private Button continueAndNextBtn;
        private String checker ="", phoneNumber="";
        private RelativeLayout relativeLayout;
        private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
        private FirebaseAuth mAuth;
        private String mVerficationId;
        private PhoneAuthProvider.ForceResendingToken mResendToken;
        private ProgressDialog loadingBar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_verify_phone);
            mAuth=FirebaseAuth.getInstance();
            loadingBar= new ProgressDialog(this);
            phoneText=findViewById(R.id.phoneText);
            codeText=findViewById(R.id.codeText);
            continueAndNextBtn=findViewById(R.id.continueNextButton);
            relativeLayout=findViewById(R.id.phoneAuth);
            ccp=(CountryCodePicker)findViewById(R.id.ccp);
            ccp.registerCarrierNumberEditText(phoneText);
            continueAndNextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    if(continueAndNextBtn.getText().equals("Submit") || checker.equals("Code Sent"))

                    {
                        String verificationCode=codeText.getText().toString();
                        if (verificationCode.equals(""))
                        {
                            Toast.makeText(VerifyPhoneActivity2.this,"please write verification code first",Toast.LENGTH_SHORT).show();
                        }
                        else

                        {
                            loadingBar.setTitle("Code Verification");
                            loadingBar.setMessage("Please wait , while we are verify code");
                            loadingBar.setCanceledOnTouchOutside(false);
                            loadingBar.show();
                            PhoneAuthCredential credential=PhoneAuthProvider .getCredential(mVerficationId,verificationCode);
                            signInWithPhoneAuthCredential(credential);
                        }

                    }
                    else
                    {
                        phoneNumber=ccp.getFullNumberWithPlus();
                        if(!phoneNumber.equals(""))
                        {

                            loadingBar.setTitle("Phone  Number Verification");
                            loadingBar.setMessage("Please wait , while we are verify your number");
                            loadingBar.setCanceledOnTouchOutside(false);
                            loadingBar.show();


                            PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, VerifyPhoneActivity2.this,mCallbacks);

                        }
                        else
                        {
                            Toast.makeText(VerifyPhoneActivity2.this,"please write valid phone number",Toast.LENGTH_SHORT);
                        }

                    }
                    String user_id = mAuth.getCurrentUser().getUid();
                    FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                }
            });


            mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
            {
                @Override
                public void onVerificationCompleted( PhoneAuthCredential phoneAuthCredential)
                {

                    signInWithPhoneAuthCredential(phoneAuthCredential);
                }


                @Override
                public void onVerificationFailed(FirebaseException e)
                {
                    Toast.makeText(VerifyPhoneActivity2.this,"Invalid",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    relativeLayout.setVisibility(View.VISIBLE);

                    continueAndNextBtn.setText("Continue");
                    codeText.setVisibility(View.GONE);


                }

                @Override
                public void onCodeSent( String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
                {
                    super.onCodeSent(s, forceResendingToken);
                    mVerficationId=s;
                    mResendToken=forceResendingToken;
                    relativeLayout.setVisibility(View.GONE);
                    checker="Code Sent";
                    continueAndNextBtn.setText("Submit");
                    codeText.setVisibility(View.VISIBLE);
                    loadingBar.dismiss();

                    Toast.makeText(VerifyPhoneActivity2.this,"Code has been Send",Toast.LENGTH_SHORT).show();

                }
            };
        }
        private void sendUserToMainActivity()
        {
            Intent intent=new Intent(VerifyPhoneActivity2.this, HomeActivity2.class);
            startActivity(intent);
            finish();
        }

        private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
        {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete( Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                String user_id = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                                String phoneNo = phoneText.getText().toString();
                                Map newPost = new HashMap();
                                newPost.put("phoneNo", phoneNo);
                                current_user_db.setValue(newPost);
                                loadingBar.dismiss();
                                Toast.makeText(VerifyPhoneActivity2.this, "loggin succesfully", Toast.LENGTH_SHORT).show();
                                sendUserToMainActivity();

                            }

                            else
                            {
                                loadingBar.dismiss();
                                String e=task.getException().toString();
                                Toast.makeText(VerifyPhoneActivity2.this,"Error:"+ e,Toast.LENGTH_SHORT).show();

                            }
                        }

                    });
        }
    }

