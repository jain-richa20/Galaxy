package com.exampl.galaxy.Cart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.exampl.galaxy.Homepage.Homepage;
import com.exampl.galaxy.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, LocationListener, onPermissionResult, GoogleMap.OnMarkerDragListener {

    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    LocationManager locationManager;
    GoogleMap mMap;
    Geocoder geocoder;
    TextView tvLocality;
//    SearchView sv_location;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
//        sv_location = findViewById(R.id.sv_location);
//        tvCity=findViewById(R.id.tvCity);
//        tvState=findViewById(R.id.tvState);
//        tvCountry=findViewById(R.id.tvCountry);
//        tvPin=findViewById(R.id.tvPin);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_own);
        tvLocality = findViewById(R.id.tvLocality);

//        sv_location.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                String location = sv_location.getQuery().toString();
//                List<Address> addressList = null;
//                if (location != null || !location.equals("")) {
//                    Geocoder geocoder = new Geocoder(MapActivity.this);
//                    try {
//
//                        addressList = geocoder.getFromLocationName(location, 1);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    LatLng latLng = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());
//                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//
//
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//        mapFragment.getMapAsync(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
        findViewById(R.id.cnfbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapActivity.this, Homepage.class));
            }
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationEnabled();
        getLocation();
        geocoder = new Geocoder(this);

    }

    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(MapActivity.this)
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude()
                            + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_own);
                    supportMapFragment.getMapAsync(MapActivity.this);

                }
            }
        });

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            tvCity.setText(addresses.get(0).getLocality());
//            tvState.setText(addresses.get(0).getAdminArea());
//            tvCountry.setText(addresses.get(0).getCountryName());
//            tvPin.setText(addresses.get(0).getPostalCode());
            tvLocality.setText(addresses.get(0).getAddressLine(0));

        } catch (Exception e) {
            finish();
        }
    }

    @Override
    public void onMapLongClick(@NonNull @NotNull LatLng latLng) {
        Log.d("onMapLongClick: ", latLng.toString());
//        googleMap.addMarker(new MarkerOptions().position(latLng)) ;
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses.size() > 0) {
//                tvCity.setText(addresses.get(0).getLocality());
//                tvState.setText(addresses.get(0).getAdminArea());
//                tvCountry.setText(addresses.get(0).getCountryName());
//                tvPin.setText(addresses.get(0).getPostalCode());
//                tvLocality.setText(addresses.get(0).getAddressLine(0));
                mMap.addMarker(new MarkerOptions().position(latLng));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMapReady(@NotNull GoogleMap googleMap) {
        mMap=googleMap;
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .draggable(true)
                .title("Current Location");

        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
        mMap.animateCamera(cameraUpdate);
        mMap.addMarker(markerOptions);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerDragListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fetchLastLocation();
                }
                break;
        }

    }

    //this method is defined for Drag marker

    @Override
    public void onMarkerDragStart(@NonNull @NotNull Marker marker) {

    }

    @Override
    public void onMarkerDrag(@NonNull @NotNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull @NotNull Marker marker) {


        LatLng latLng = marker.getPosition();
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
//            tvCity.setText(addresses.get(0).getLocality());
//            tvState.setText(addresses.get(0).getAdminArea());
//            tvCountry.setText(addresses.get(0).getCountryName());
//            tvPin.setText(addresses.get(0).getPostalCode());
            tvLocality.setText(addresses.get(0).getAddressLine(0));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}