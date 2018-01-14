package com.example.daniel.loginact_homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name, email, phone, desc;
    Button logout, save;
    SharedPreferences sp, fp;
    SharedPreferences.Editor editor1, editor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.name2_edit_txt);
        email = findViewById(R.id.email2_edit_txt);
        phone = findViewById(R.id.phone2_edit_txt);
        desc = findViewById(R.id.desc2_edit_txt);
        logout = findViewById(R.id.logout_btn);
        save = findViewById(R.id.save_btn);
        fp = getSharedPreferences("email", MODE_PRIVATE);
        sp = getSharedPreferences(fp.getString("email", "none"), MODE_PRIVATE);
        name.setText(sp.getString("name", ""));
        email.setText(sp.getString("email", ""));
        phone.setText(sp.getString("phone", ""));
        desc.setText(sp.getString("desc", ""));
        if (sp.getString("flag", "false").equals("true")) {
            setFields();
        }
        logout.setOnClickListener(this);
        save.setOnClickListener(this);

    }

    public void setFields() {
        if (sp.contains("name") || sp.contains("email") || sp.contains("phone") || sp.contains("desc")) {
            name.setText(sp.getString("name", ""));
            email.setText(sp.getString("email", ""));
            phone.setText(sp.getString("phone", ""));
            desc.setText(sp.getString("desc", ""));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == logout.getId()) {
            Intent intent = new Intent("logout.btn");
            sp = getSharedPreferences(getIntent().getStringExtra(LoginAct_Activity.DATA), MODE_PRIVATE);
            editor1 = sp.edit();
            editor1.putString("flag", "false");
            editor1.commit();
            editor2 = fp.edit();
            editor2.putString("email", "");
            editor2.commit();
            startActivity(intent);
            finish();
        }

        if (v.getId() == save.getId()) {
            editor1 = sp.edit();
            editor1.putString("name", name.getText().toString());
            editor1.putString("email", email.getText().toString());
            editor1.putString("phone", phone.getText().toString());
            editor1.putString("desc", desc.getText().toString());
            editor1.commit();

        }
    }
}

