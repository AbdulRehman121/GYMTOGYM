package com.example.gtg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText user_id,user_password;
    private Button login;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    TextView registration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        login = (Button)findViewById(R.id.login);
        registration =  (TextView)findViewById(R.id.reg);
        login.setOnClickListener(this);
        registration.setOnClickListener(this);
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(this);

    }

    public void LOGIN(String email,String password)
    {
        if(email.isEmpty())
        {
            Toast.makeText(this, "Provide Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty())
        {
            Toast.makeText(this, "Provide Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful())
            {
                Toast.makeText(this,"User Logged In",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
            else
            {
                Toast.makeText(this,"Unable to signin",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }
    public void register(){
        Toast.makeText(this,"Register Here",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent (MainActivity.this, registration.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login:
            user_id = (EditText)findViewById(R.id.user_id);
            user_password = (EditText)findViewById(R.id.user_password);
            String email = user_id.getText().toString().toLowerCase().trim();
            String password = user_password.getText().toString().trim();
            LOGIN(email,password);
            break;
            case R.id.reg:
                register();
                break;

        }
    }
}