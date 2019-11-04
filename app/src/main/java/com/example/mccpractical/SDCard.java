package com.example.mccpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class SDCard extends AppCompatActivity {
  private static final String TAG = "SDCARD";
  EditText e1;
  Button write, read, clear;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sdcard);

    e1 =  findViewById(R.id.editText);
    write = findViewById(R.id.button);
    read = findViewById(R.id.button2);
    clear = findViewById(R.id.button3);


    write.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String message = e1.getText().toString();
        try {
          File f  = new File("/sdcard/myfile.txt");
          f.createNewFile();
          FileOutputStream fout = new FileOutputStream(f);
          fout.write(message.getBytes());
          fout.close();
          Toast.makeText(getBaseContext(), "Data Written in SDCARD",
              Toast.LENGTH_LONG).show();
        } catch (Exception e) {
          Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
      }
    });
    read.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String message;
        String buf = "";
        try {
          File f = new File("/sdcard/myfile.txt");
          FileInputStream fin = new FileInputStream(f);
          BufferedReader br = new BufferedReader(new InputStreamReader(fin));
          while ((message = br.readLine()) != null) {
            buf += message;
          }
          e1.setText(buf);
          br.close();
          fin.close();
          Toast.makeText(getBaseContext(), "Data Recived from  SDCARD", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
          Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
      }
    });
    clear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        e1.setText("");
      }
    });
  }


}