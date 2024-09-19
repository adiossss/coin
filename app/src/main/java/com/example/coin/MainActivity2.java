package com.example.coin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {

    private Button button4,button5,button3,button7;
    private TextView textView6,textView7;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);

        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);


        mAuth=FirebaseAuth.getInstance();

        String userEmail = mAuth.getCurrentUser().getEmail();
        textView6.setText("使用者:"+userEmail);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("coin")
                        .document(userEmail)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()){
                                    DocumentSnapshot documentSnapshot = task.getResult();

                                    Long asd =documentSnapshot.getLong("money");
                                    textView7.setText("當前餘額:"+asd);

                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Demo",e.getMessage());
                            }
                        });
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity2.this,MainActivity3.class);

                startActivityForResult(it,101);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity2.this,MainActivity4.class);

                startActivityForResult(it,101);
            }
        });


    }
}