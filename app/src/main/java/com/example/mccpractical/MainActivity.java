package com.example.mccpractical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

  private static final int PERMISSION_REQUEST_CODE = 200;
  private static final String TAG = "MainActivity";
  private Button btn_sdcard, btn_notfication, btn_gps, btn_primitives;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btn_sdcard = findViewById(R.id.btn_sdcard);
    btn_notfication = findViewById(R.id.btn_notification);
    btn_gps = findViewById(R.id.btn_gps);
    btn_primitives = findViewById(R.id.btn_primitives);

    if(!checkPermission()){
      requestPermission();
    }
    btn_sdcard.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          startActivity(new Intent(MainActivity.this, SDCard.class));
      }
    });

    btn_notfication.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, NotificationActivity.class));

      }
    });

    btn_gps.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, GPSActivity.class));
      }
    });

    btn_primitives.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, PrimitivesActivity.class));

      }
    });
  }


  private boolean checkPermission() {
    int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
    int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
    return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
  }
  private void requestPermission() {
    ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, ACCESS_FINE_LOCATION},
                                    PERMISSION_REQUEST_CODE);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    switch (requestCode){
      case PERMISSION_REQUEST_CODE:
        if (grantResults.length > 0){
          boolean writeAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED;
          if(writeAccepted){
            return;
          }else{
            Toast.makeText(MainActivity.this, "Permission Denied",Toast.LENGTH_SHORT).show();
            return;
          }
        }
        break;
    }
  }
}
