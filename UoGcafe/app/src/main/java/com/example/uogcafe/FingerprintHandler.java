package com.example.uogcafe;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;

    // Constructor
    public FingerprintHandler(Context mContext) {
        context = mContext;
    }

    // Fingerprint authentication starts here..
    public void Authentication(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    // On authentication failed
    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(context, "failed", Toast.LENGTH_LONG).show();
    }

    // On successful authentication
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Toast.makeText(context, "Successfully Authenticated...", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context.getApplicationContext(),activity_qr.class);
        context.startActivity(intent);
    }

    // This method is used to update the text message
    // depending on the authentication result
    public void update(String e, Boolean success){
        if(success){
            Toast.makeText(context, "Successfully Authenticated...", Toast.LENGTH_LONG).show();
        }
    }
}







































































// programmed by yishak Terfe.....................