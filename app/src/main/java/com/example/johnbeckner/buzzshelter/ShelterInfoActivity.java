package com.example.johnbeckner.buzzshelter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class ShelterInfoActivity extends AppCompatActivity {
    Shelter info;

    TextView name;
    TextView capacity;
    TextView address;
    TextView restrictions;
    TextView phone;
    Button reserveButton;
    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_info);

        name = (TextView) findViewById(R.id.shelterName);
        capacity = (TextView) findViewById(R.id.shelterCap);
        address = (TextView) findViewById(R.id.shelterAddress);
        restrictions = (TextView) findViewById(R.id.shelterRes);
        phone = (TextView) findViewById(R.id.shelterPhone);
        reserveButton = findViewById(R.id.reserveButton);
        returnButton = findViewById(R.id.returnButton);

        info = getIntent().getParcelableExtra("shelter_info");
        setShelterInfo();
    }

    private void setShelterInfo() {
        name.setText(info.getShelterName());
        capacity.setText(String.format("%d\nThis has been reserved by %s",
                info.getCapacity(), info.getReservations().keySet().toString()));
        address.setText(info.getAddress());
        restrictions.setText(info.getRestrictions());
        phone.setText(info.getPhoneNumber());

        reserveButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReserveActivity.class);
            intent.putExtra("shelter_info", info);
            startActivityForResult(intent, 1);
        });

        returnButton.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                info = data.getParcelableExtra("result");
                setShelterInfo();
            }
        }
    }
}
