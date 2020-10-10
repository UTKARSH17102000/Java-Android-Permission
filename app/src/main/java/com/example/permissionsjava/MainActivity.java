package com.example.permissionsjava;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button btnStatusBar,btnDial;
    TextView  tvStatus;
    EditText etphNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStatusBar = findViewById(R.id.btnStatusBar);
        tvStatus = findViewById(R.id.tvStatus);
        btnDial = findViewById(R.id.btnDial);
        etphNo = findViewById(R.id.etPhNo);

        btnStatusBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                boolean isConnected = netInfo != null && netInfo.isConnected();
                tvStatus.setText(isConnected ? "CONNECTED" : "DISCONNECTED");
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int permission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);

                if (permission == PackageManager.PERMISSION_GRANTED) {
                    callNumber();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 121);
                }
            }


        });
    }

    void callNumber() {
        String telNo = etphNo.getText().toString();
        Uri uri = Uri.parse("tel:" + telNo);
        Intent i = new Intent(Intent.ACTION_CALL,uri);
        startActivity(i);

    }
}