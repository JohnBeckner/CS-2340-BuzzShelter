package com.example.johnbeckner.buzzshelter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        final Button mlogin_Button = findViewById(R.id.Login_Button);
        final Button mRegister_Button = findViewById(R.id.Register_Button);

        File file = file = new File(this.getFilesDir(), "data.bin");
        if (file.exists()) {
            BinarySerialize bs = new BinarySerialize();
            bs.loadBinary(file);
        }

        mlogin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
            }
        });

        mRegister_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaunchActivity.this, RegistrationActivity.class));
            }
        });
    }
}
