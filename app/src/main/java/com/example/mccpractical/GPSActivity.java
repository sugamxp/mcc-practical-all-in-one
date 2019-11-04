package com.example.mccpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.List;
import java.util.Locale;

public class GPSActivity extends AppCompatActivity {
  private TextView tv_location, btn_share;
  private FusedLocationProviderClient fusedLocationClient;
  double latitude, longitude;
  private static final String TAG = "GPSActivity";
  Geocoder geocoder;
  List<Address> addresses;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gps);
    tv_location = findViewById(R.id.tv_location);
    btn_share = findViewById(R.id.btn_share);
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    geocoder = new Geocoder(this, Locale.getDefault());


    fusedLocationClient.getLastLocation()
        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
          @Override
          public void onSuccess(Location location) {

            if (location != null) {
              longitude = location.getLongitude();
              latitude = location.getLatitude();
              Log.d(TAG, "onSuccess: " + latitude + " " + longitude);
              try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
              }catch (Exception e){
                e.printStackTrace();
              }
              String address = addresses.get(0).getAddressLine(0);
              String city = addresses.get(0).getLocality();
              String state = addresses.get(0).getAdminArea();
              String country = addresses.get(0).getCountryName();
              String postalCode = addresses.get(0).getPostalCode();
              String knownName = addresses.get(0).getFeatureName();


              tv_location.setText("Latitute => "+location.getLatitude() +
                                  " \nLongitude => " + location.getLongitude() +
                  "\n Address => " + address +
                  "\n City => " + city
                  );
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
