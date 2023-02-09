package com.example.barcode;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    FrameLayout f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        f = findViewById(R.id.layout);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                        check chk = new check();
                        try {
                            chk.send(result.toString());
                            Toast.makeText(MainActivity.this, chk.res, Toast.LENGTH_SHORT).show();
                            if(Integer.parseInt(chk.res) == 1){
                                Toast.makeText(MainActivity.this,R.string.has_eaten,Toast.LENGTH_SHORT).show();
                                //f.setBackgroundColor(Color.RED);
                            }else if(Integer.parseInt(chk.res) ==0){
                                chk.insert(result.toString());
                                Toast.makeText(MainActivity.this,R.string.not_eaten,Toast.LENGTH_SHORT).show();
                                //f.setBackgroundColor(Color.GREEN);

                            }
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
        if (ActivityCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, INTERNET) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        }
        else{
            requestPermission();
        }
    }
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{CAMERA,INTERNET}, 100);
        }
    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (ActivityCompat.checkSelfPermission(this, CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, INTERNET) == PackageManager.PERMISSION_GRANTED) {
                    return;
                }}}
    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}
























































































































































// programmed by yishak Terfe.....................