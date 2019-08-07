package com.example.finrecapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etOldPassword, etNewPassword, etConfirmPassword;

    ImageButton btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        // INIT VIEW
        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        // INIT VIEW END

        btnRegister.setOnClickListener(this);
        // GET STRING FROM SHARED PREF



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegister:
                String etOldPass = etOldPassword.getText().toString();
                String etNewPass = etNewPassword.getText().toString();
                String etConfirmPass = etConfirmPassword.getText().toString();
                SharedPreferences pref = getBaseContext().getSharedPreferences(
                        getString(R.string.shared_pref_password), Context.MODE_PRIVATE);

                String oldPassword = pref.getString("pass", "admin");
                if(etOldPass.equalsIgnoreCase(oldPassword)
                        && etNewPass.equalsIgnoreCase(etConfirmPass)){
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString("pass", etNewPass);
                    edit.apply();
                    Toast.makeText(this, "Berhasil Ganti Password", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Tidak Dapat Menyimpan Data", Toast.LENGTH_SHORT).show();
                }
                
                
                break;
        }
    }
}
