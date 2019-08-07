package com.example.finrecapps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ImageButton btnLogin;
    TextView ChangePassword;
    EditText etUsername, etPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // START INIT VIEW
        btnLogin = findViewById(R.id.btnLogin);
        ChangePassword = findViewById(R.id.tvChangePassword);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        // END INIT VIEW


        // START SHARED PREF
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.shared_pref_password), Context.MODE_PRIVATE);

        if(sharedPref.getBoolean("isLogin", true)){
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        Log.d("asd", sharedPref.getString("pass", "dev"));
        SharedPreferences.Editor editor = sharedPref.edit();
        if(sharedPref.getString("pass","admin") == null ||
        sharedPref.getString("pass", "admin").equalsIgnoreCase("")){
            Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();
            editor.putString("pass", "admin");
            editor.apply();
        }


        // END SHARED PREF


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();

                SharedPreferences pref = getBaseContext().getSharedPreferences(
                        getString(R.string.shared_pref_password), Context.MODE_PRIVATE);
                String pass = pref.getString("pass", "admin");
                String etPass = etPassword.getText().toString();

                Toast.makeText(LoginActivity.this, "shared"+ pass, Toast.LENGTH_SHORT).show();
                Log.v("sharedPreference", pass);
                if(username.equalsIgnoreCase("admin")&&etPass.equalsIgnoreCase(pass)){
                    SharedPreferences.Editor edt = pref.edit();
                    edt.putBoolean("isLogin",true);
                    edt.apply();
                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(login);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Password Salah", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(LoginActivity.this, ChangePasswordActivity.class);
                startActivity(change);
            }
        });

    }
}
