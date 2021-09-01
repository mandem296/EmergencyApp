package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Center extends AppCompatActivity {

    private Button mHospital,mCustomer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        mHospital=(Button) findViewById(R.id.hospital1);
        mCustomer=(Button) findViewById(R.id.customer1);

        mCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Center.this,Login.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        mHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Center.this,HospitalLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });



    }
}