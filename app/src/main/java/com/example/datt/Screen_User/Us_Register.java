package com.example.datt.Screen_User;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class Us_Register extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button btn_register;
    TextInputEditText edt_email,edt_pass,edt_repass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_us_register);

        mAuth=FirebaseAuth.getInstance();
        btn_register=findViewById(R.id.btn_register);
        edt_email=findViewById(R.id.Regis_Email);
        edt_pass=findViewById(R.id.Regis_Pass);
        edt_repass=findViewById(R.id.Regis_RePass);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password,repass;
                email = String.valueOf(edt_email.getText());
                password = String.valueOf(edt_pass.getText());
                repass = String.valueOf(edt_repass.getText());

                // Kiểm tra các trường bắt buộc
                if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Us_Register.this, "Enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Us_Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(repass)) {
                    Toast.makeText(Us_Register.this, "Enter repassword", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra mật khẩu mạnh
                if (!isValidPassword(password)) {
                    Toast.makeText(Us_Register.this, "Password must include at least one uppercase letter, one lowercase letter, one number, and one special character.", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!password.equals(repass)) {
                    Toast.makeText(Us_Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(Us_Register.this, "Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Us_Register.this, Us_Login.class));
                                    finish();
                                } else {
                                    String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
                                    Toast.makeText(Us_Register.this, "Failed: " + errorMessage, Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });

    }
    // Hàm kiểm tra mật khẩu mạnh
    private boolean isValidPassword(String password) {
        Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        return passwordPattern.matcher(password).matches();
    }
}