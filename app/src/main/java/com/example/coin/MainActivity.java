package com.example.coin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    private com.google.firebase.firestore.FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    private Button button;
    private EditText acc,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        acc = findViewById(R.id.acc);
        pass = findViewById(R.id.pass);

        mAuth=FirebaseAuth.getInstance();

        storageReference= FirebaseStorage.getInstance().getReference();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = acc.getText().toString();
                String password = pass.getText().toString();

                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"認證成功"+task.getResult().getUser().getEmail(),
                                            Toast.LENGTH_SHORT).show();

                                    checkUser();
                                }
                            }

                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
    void checkUser(){
        if (mAuth.getCurrentUser()!=null){
            String userEmail = mAuth.getCurrentUser().getEmail();

            Intent it = new Intent(MainActivity.this,MainActivity2.class);

            startActivityForResult(it,101);

        }else {

        }
    }

}