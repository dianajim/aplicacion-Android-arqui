package com.example.tawi.rutasproyecto;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Connection;

import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public double lat_pos_ini=14.587687;
    public double long_pos_ini=-90.553147;
    public double lat_pos_fin=14.587687;
    public double long_pos_fin=-90.553147;
    public double lat_pos_act=14.587687;
    public double long_pos_act=-90.553147;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        int status= GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(status== ConnectionResult.SUCCESS) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else{
            Dialog dialog=GooglePlayServicesUtil.getErrorDialog(status,(Activity)getApplicationContext(),10);
            dialog.show();
        }
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
        //
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        UiSettings uiSettings=mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        //

        LatLng sydney = new LatLng(lat_pos_ini, long_pos_ini );
        mMap.addMarker(new MarkerOptions().position(sydney).title("Facultad de Ingenieria").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        float zoomlevel=16;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,zoomlevel));
    }
}
