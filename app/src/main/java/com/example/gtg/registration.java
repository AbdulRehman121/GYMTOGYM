package com.example.gtg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity implements View.OnClickListener {
        private EditText jUser_name,jFather_name,juser_email,juser_mobile_no,jPassword,jR_Password;
        private CheckBox checkBox;
        private Button SIGNUP;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        jUser_name = (EditText)findViewById(R.id.User_name);
        jFather_name =  (EditText)findViewById(R.id.Father_name);
        juser_email =  (EditText)findViewById(R.id.user_email);
        juser_mobile_no =  (EditText)findViewById(R.id.user_mobile_no);
        jPassword =  (EditText)findViewById(R.id.Password);
        jR_Password =  (EditText)findViewById(R.id.R_Password);
        SIGNUP = (Button)findViewById(R.id.add);
        SIGNUP.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        firebaseAuth= FirebaseAuth.getInstance();
    }
    public void signup(String email,String pass)
    {
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(),"Email Required",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(getApplicationContext(),"Password Required",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering......");
        progressDialog.show();
    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, task -> {
        if (task.isSuccessful())
        {
            Toast.makeText(getApplicationContext(),"User Successfully Registered",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent (registration.this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"User Unable To Register",Toast.LENGTH_SHORT).show();
        }
    });
    }


        @Override
    public void onClick(View v) {
        String email = juser_email.getText().toString().trim();
        String password = jPassword.getText().toString().trim();
        signup(email,password);

    }
}