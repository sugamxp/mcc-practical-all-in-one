package com.example.mccpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class GPSActivity extends AppCompatActivity {
  private TextView tv_location, btn_share;
  private FusedLocationProviderClient fusedLocationClient;
  double latitude, longitude;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gps);
    tv_location = findViewById(R.id.tv_location);
    btn_share = findViewById(R.id.btn_share);
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    fusedLocationClient.getLastLocation()
        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
          @Override
          public void onSuccess(Location location) {
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
              // Logic to handle location object
              longitude = location.getLongitude();
              latitude = location.getLatitude();
              tv_location.setText("Latitute => "+location.getLatitude() + " \nLongitude => " + location.getLongitude());
            }
          }
        });

    btn_share.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String uri = "geo:" + latitude + ","
            +longitude + "?q=" + latitude
            + "," + longitude;
        startActivity(new Intent(android.content.Intent.ACTION_VIEW,
            Uri.parse(uri)));
      }
    });
  }
}
