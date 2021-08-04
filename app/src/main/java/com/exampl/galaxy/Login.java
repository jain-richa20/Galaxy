package com.exampl.galaxy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.exampl.galaxy.Homepage.Homepage;
import com.exampl.galaxy.signup.SignUp;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class Login extends AppCompatActivity {
    EditText emailid, password;
    Button btn_log_in;
    LoginButton login_button;
    SignInButton sign_in_button;
    TextView forgot, skip, click_sign;
    ImageView Back;
    boolean isEmailValid, isPasswordValid;
    CallbackManager callbackManager;
    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;

        SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        emailid = findViewById(R.id.emailid);
        password = findViewById(R.id.password);
        btn_log_in = findViewById(R.id.btn_log_in);
        skip = findViewById(R.id.skip);
        Back = findViewById(R.id.Back);
        forgot = findViewById(R.id.forgot);
        click_sign = findViewById(R.id.click_sign);
        login_button=findViewById(R.id.login_button);
        sign_in_button=findViewById(R.id.sign_in_button);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        
        // skip
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("weather")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String msg = "Successful login";
                                if (!task.isSuccessful()) {
                                    msg = "Failed";
                                }
                            }
                        });
                startActivity(new Intent(Login.this, Homepage.class));
            }
        });
        //for Back
        /*Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });*/
        click_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });
        //validation on button
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButtonValidation();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void checkButtonValidation() {
        String email = emailid.getText().toString();
        String pass = password.getText().toString();
        if (emailid.getText().toString().isEmpty()) {

            emailid.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailid.getText().toString()).matches()) {
            emailid.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else {
            isEmailValid = true;
        }

        // check password validation
        if (password.getText().toString().isEmpty()) {
            password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 8) {
            password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
        }

        if (isEmailValid && isPasswordValid) {
//            SharedPreferences.Editor editor=sharedPreferences.edit();
//            editor.putString("Email",email);
//            editor.putString("Password",pass);
//            editor.commit();
            startActivity(new Intent(Login.this, Homepage.class));
            finish();
        }
    }
}