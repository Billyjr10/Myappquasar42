package com.example.myappquasar42;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myappquasar42.databinding.ActivityMapsBinding;

import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    MarkerOptions Marker;
    LatLng CenterLocation;

    Vector<MarkerOptions> Markeroptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        CenterLocation = new LatLng(48.9,2.3);//coordonnées de la ville de PARIS

        Markeroptions = new Vector<>();

        Markeroptions.add(new MarkerOptions().title("La péniche-Cinéma")
                .position(new LatLng(48.9, 2.4))
                .snippet("Ouvert tous les jours"));

        Markeroptions.add(new MarkerOptions().title("Cinéma UGC")
                .position(new LatLng(48.873123, 2.335246))
                .snippet("Ouvert tous les jours"));

        Markeroptions.add(new MarkerOptions().title("Brasserie maison de l'Alsace")
                .position(new LatLng(48.869952, 2.305824))
                .snippet("Ouvert tous les jours"));

        Markeroptions.add(new MarkerOptions().title("Le club Haussman")
                .position(new LatLng(48.8731241, 2.3352724))
                .snippet("Ouvert tous les jours"));

        Markeroptions.add(new MarkerOptions().title("Cinéma UGC Chatelet")
                .position(new LatLng(48.861878, 2.3471384))
                .snippet("Ouvert tous les jours"));

        Markeroptions.add(new MarkerOptions().title("Restaurants Dragons Elysées")
                .position(new LatLng(48.8724071, 2.3039734))
                .snippet("Ouvert tous les jours"));






    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        for(MarkerOptions Mark : Markeroptions) {
            mMap.addMarker(Mark);
        }

        enableMyLocation();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CenterLocation, 8));
    }
    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            return;
        }
        String perms []= {"android.permission.ACCESS_FINE_LOCATION"};
        // 2. Otherwise, request location permissions from the user.
        ActivityCompat.requestPermissions(this,perms,200);
    }
}