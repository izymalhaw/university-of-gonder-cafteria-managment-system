package com.example.uogcafe;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class activity_qr extends AppCompatActivity {
    private ImageView qrCodeIV;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    DBHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        conn = new DBHelper(this);
        Cursor res = conn.getdata();
        StringBuilder id = new StringBuilder();
        while(res.moveToNext()){
            id.append(res.getString(0));
        }
        qrCodeIV = findViewById(R.id.idIVQrcode);
        generate(id.toString());
    }
    public void generate(String studID) {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;
        qrgEncoder = new QRGEncoder(studID, null, QRGContents.Type.TEXT, dimen);
        bitmap = qrgEncoder.getBitmap();
        qrCodeIV.setImageBitmap(bitmap);
        Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();
    }
    }































































    // programmed by yishak Terfe.....................