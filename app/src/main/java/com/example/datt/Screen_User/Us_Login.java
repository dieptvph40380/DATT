package com.example.datt.Screen_User;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datt.MainActivity;
import com.example.datt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Us_Login extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button btn_Sigin;
    TextView tv_Regis;
    TextInputEditText edt_email,edt_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_us_login);

        mAuth=FirebaseAuth.getInstance();
        btn_Sigin=findViewById(R.id.btn_Signin);
        tv_Regis=findViewById(R.id.tv_Regis);
        edt_email=findViewById(R.id.Sign_Email);
        edt_pass=findViewById(R.id.Sign_Pass);

        tv_Regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Us_Login.this, Us_Register.class));
            }
        });

        btn_Sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email,password;
                email = String.valueOf(edt_email.getText());
                password = String.valueOf(edt_pass.getText());

                // Kiểm tra thông tin đăng nhập
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Us_Login.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Us_Home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Us_Login.this, "failed.", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}