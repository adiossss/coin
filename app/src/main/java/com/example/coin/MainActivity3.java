package com.example.coin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    private EditText edit1, edit2,edit3;

    private Button button2;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);

        button2 = findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();

        String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                String userEmail = mAuth.getCurrentUser().getEmail();

                db.collection("coin")
                        .document(edit1.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    Long asd =documentSnapshot.getLong("money");
                                    int i=asd.intValue();
                                    String ad= edit2.getText().toString();
                                    int ed=Integer.parseInt(ad);
                                    int ms = i+ed;
                                    Map<String,Object> coins = new HashMap<>();
                                    coins.put("money",ms);
                                    db.collection("coin")
                                            .document(edit1.getText().toString())
                                            .set(coins);
                                Toast.makeText(MainActivity3.this,"轉帳成功",
                                        Toast.LENGTH_SHORT).show();

                            }
                        });
                db.collection("coin")
                        .document(userEmail)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                DocumentSnapshot documentSnapshot = task.getResult();
                                Long dsa =documentSnapshot.getLong("money");
                                int q=dsa.intValue();
                                String ad= edit2.getText().toString();
                                int de=Integer.parseInt(ad);
                                int qr = q-de;
                                Map<String,Object> coins = new HashMap<>();
                                coins.put("money",qr);
                                db.collection("coin")
                                        .document(userEmail)
                                        .set(coins);
                            }
                        });
                Map<String,Object> give = new HashMap<>();
                String value= edit2.getText().toString();
                int finalValue=Integer.parseInt(value);
                give.put("gnowDate",nowDate);
                give.put("gname",edit1.getText().toString());
                give.put("gmoney",finalValue);
                give.put("gcouse",edit3.getText().toString());
                db.collection(userEmail)
                        .add(give)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                            }
                        });
                Map<String,Object> get = new HashMap<>();
                String valu= edit2.getText().toString();
                int finalValu=Integer.parseInt(valu);
                get.put("hnowDate",nowDate);
                get.put("hname",userEmail);
                get.put("hmoney",finalValu);
                get.put("hcouse",edit3.getText().toString());
                db.collection("h"+edit1.getText().toString())
                        .add(get)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                            }
                        });


            }
        });
    }

}


