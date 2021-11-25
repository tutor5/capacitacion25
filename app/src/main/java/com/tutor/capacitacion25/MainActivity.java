package com.tutor.capacitacion25;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText etemail, etpassword;
    Button btningresar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btningresar = findViewById(R.id.button_ingresar);
        etemail = findViewById(R.id.editTextTextEmailAddress);
        etpassword = findViewById(R.id.editTextTextPassword);
        mAuth = FirebaseAuth.getInstance();

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();

                singIn(email,password);


            }
        });



    }


    private void singIn(String email, String password){


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){

               FirebaseUser user = mAuth.getCurrentUser();
               Intent i = new Intent(MainActivity.this,DatabaseActivity.class);
               startActivity(i);

           }else{
               Toast.makeText(MainActivity.this, "Fallo la autenticacion.",
                       Toast.LENGTH_SHORT).show();
           }
            }
        });

    }


}