package com.example.coin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
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
import java.util.Locale;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {

    private Button button9,button6;
    private TextView textView4,textView3;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        button9 = findViewById(R.id.button9);

        textView4 = findViewById(R.id.textView4);
        textView3 = findViewById(R.id.textView3);


        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView3.setText("");
                textView4.setText("");
                mAuth=FirebaseAuth.getInstance();
                String userEmail = mAuth.getCurrentUser().getEmail();
                db.collection(userEmail.toLowerCase())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot doc: task.getResult()){
                                        Map<String,Object> z =doc.getData();
                                        String gname =z.get("gname").toString();
                                        String gcouse =z.get("gcouse").toString();
                                        String gmoney =z.get("gmoney").toString();
                                        String gnowDate =z.get("gnowDate").toString();
                                        textView4.append("時間:"+gnowDate+"給:"+gname+" "+gmoney+"元"+"課程:"+gcouse+"\n");
                                    }
                                }
                            }
                        });
                db.collection("h"+userEmail.toLowerCase())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot doc: task.getResult()){
                                        Map<String,Object> z =doc.getData();
                                        String hname =z.get("hname").toString();
                                        String hmoney =z.get("hmoney").toString();
                                        String hcouse =z.get("hcouse").toString();
                                        String hnowDate =z.get("hnowDate").toString();
                                        textView3.append("時間:"+hnowDate+"從:"+hname+"獲得:"+hmoney+"元"+"課程:"+hcouse+"\n");
                                    }
                                }
                            }
                        });
            }
        });

    }
}