package com.example.daniel.loginact_homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginAct_Activity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sp, fp;
    Button login;
    EditText email;
    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_act_);
        email = findViewById(R.id.email_edit_txt);
        login = findViewById(R.id.login_btn);
        login.setOnClickListener(this);
        fp = getSharedPreferences("email", MODE_PRIVATE);
        sp = getSharedPreferences(fp.getString("email", "none"), MODE_PRIVATE);
        if (sp.getString("flag", "false").equals("true")) {
            Intent intent = new Intent("login.btn");
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn) {
            Intent intent = new Intent("login.btn");
            intent.putExtra(LoginAct_Activity.DATA, email.getText().toString());
            sp = getSharedPreferences(email.getText().toString(), MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sp.edit();
            editor1.putString("flag", "true");

            editor1.commit();
            SharedPreferences.Editor editor2 = fp.edit();
            editor2.putString("email",email.getText().toString());
            editor2.commit();
            startActivity(intent);
            finish();
        }
    }

}
