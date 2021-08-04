package com.exampl.galaxy.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exampl.galaxy.Homepage.Homepage;
import com.exampl.galaxy.Login;
import com.exampl.galaxy.MainActivity;
import com.exampl.galaxy.R;
import com.exampl.galaxy.Verify;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {
EditText layoutUsername,layoutPhone,layoutEmailId,layoutPassword,layoutCnfPassword;
Button btn_sign_up,verification;
ImageView imageBack;
ConstraintLayout verify;
EditText num1;
TextView exit,number;
boolean isUser,isEmail,isPassword,isRepassword,isMobile;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        layoutUsername=findViewById(R.id.Username);
        layoutPhone=findViewById(R.id.Phone);
        layoutEmailId=findViewById(R.id.EmailId);
        layoutPassword=findViewById(R.id.Password);
        layoutCnfPassword=findViewById(R.id.CnfPassword);
        btn_sign_up=findViewById(R.id.btn_sign_up);
        verify=findViewById(R.id.verify);
        exit=findViewById(R.id.exit);
        verification=findViewById(R.id.verification);
        number=findViewById(R.id.number);
        imageBack=findViewById(R.id.imageBack);
        num1=findViewById(R.id.num1);
//        num2=findViewById(R.id.num2);
//        num3=findViewById(R.id.num3);
//        num4=findViewById(R.id.num4);
//        num5=findViewById(R.id.num5);
//        num6=findViewById(R.id.num6);

        mAuth = FirebaseAuth.getInstance();
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify.setVisibility(View.GONE);
                num1.setText("");
            }
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d("onVerificationCompleted", credential+"");
                num1.setText(credential.getSmsCode()+"");
                signInWithPhoneAuthCredential(credential);
                verification.setEnabled(true);
                verification.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SignUp.this, Homepage.class));
                    }
                });

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.d("onVerificationFailed", e+"");
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Log.d("onCodeSent" , verificationId+"");
            }
        };
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButtonValidation();
//                verify.setVisibility(View.VISIBLE);
//                String mobilenumber="+91"+layoutPhone.getText().toString();
//                startPhoneNumberVerification(mobilenumber);

            }
        });

    }

    private void startPhoneNumberVerification(String s) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(s)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(SignUp.this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("signInWithCredential","success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("signInWithCredential", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void checkButtonValidation() {
        if (layoutUsername.getText().toString().isEmpty()) {

            layoutUsername.setError(getResources().getString(R.string.name_error));
            isUser = false;
        } else  {
            isUser = true;
        }
        //check email validation
        if (layoutEmailId.getText().toString().isEmpty()) {

            layoutEmailId.setError(getResources().getString(R.string.email_error));
            isEmail = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(layoutEmailId.getText().toString()).matches()) {
            layoutEmailId.setError(getResources().getString(R.string.error_invalid_email));
            isEmail = false;
        } else  {
            isEmail = true;
        }

        // check password validation
        if (layoutPassword.getText().toString().isEmpty()) {
            layoutPassword.setError(getResources().getString(R.string.password_error));
            isPassword= false;
        } else if (layoutPassword.getText().length() < 8) {
            layoutPassword.setError(getResources().getString(R.string.error_invalid_password));
            isPassword = false;
        } else  {
            isPassword= true;
        }

        // check confirm password validation
        if (layoutCnfPassword.getText().toString().isEmpty()) {
            layoutCnfPassword.setError(getResources().getString(R.string.repassword_error));
            isRepassword= false;
        } else if (!((layoutPassword.getText().toString()).equals(layoutCnfPassword.getText().toString()))) {
            layoutCnfPassword.setError(getResources().getString(R.string.match_password));
            isRepassword = false;
        } else  {
            isRepassword= true;
        }

        // check mobile validation
        if (layoutPhone.getText().toString().isEmpty()) {
            layoutPhone.setError(getResources().getString(R.string.phone_error));
            isMobile= false;
        } else if (layoutPhone.getText().length() < 10) {
            layoutPhone.setError(getResources().getString(R.string.error_invalid_mobile));
            isMobile = false;
        }else if (layoutPhone.getText().length() > 10) {
            layoutPhone.setError(getResources().getString(R.string.error_in_mobile));
            isMobile = false;
        }
        else  {
            isMobile= true;
        }

        if (isUser && isEmail && isPassword && isRepassword && isMobile) {
            verify.setVisibility(View.VISIBLE);
            number.setText(layoutPhone.getText().toString());
            String mobilenumber="+91"+layoutPhone.getText().toString();
            startPhoneNumberVerification(mobilenumber);
            verification.setEnabled(false);
        }
    }
}