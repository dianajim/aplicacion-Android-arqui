package com.example.tawi.rutasproyecto;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public double lat_pos_ini=14.587687; //latitud de Posicion de inicio de viaje
    public double long_pos_ini=-90.553147;//longitud de Posicion de Inicio de viaje

    public double lat_pos_fin=14.652525; //latitud de Posicion de fin  de Viaje
    public double long_pos_fin=-90.488321;//longitud de posicion de fin de Viaje

    public double lat_pos_act=14.626597; //latitud de Posicion actual de viaje
    public double long_pos_act=-90.555731;//longitud de posicion actual de Viaje

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

        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //Sirve para definir el tipo de mapa
        UiSettings uiSettings=mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true); //permite activar el zoom del mapa
        float zoomlevel=10;  //zoom del mapa


        //marca de inicio
        LatLng posicion_inicio = new LatLng(lat_pos_ini, long_pos_ini );
        mMap.addMarker(new MarkerOptions().position(posicion_inicio).title("Inicio de Viaje").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion_inicio,zoomlevel));
       //marca  de fin
        LatLng fin_viaje = new LatLng(lat_pos_fin, long_pos_fin );
        mMap.addMarker(new MarkerOptions().position(fin_viaje).title("Fin viaje").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fin_viaje,zoomlevel));
        //marca de mi ubicacion
        LatLng mi_posicion = new LatLng(lat_pos_act, long_pos_act );
        mMap.addMarker(new MarkerOptions().position(mi_posicion).title("Mi Ubicacion").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mi_posicion,zoomlevel));

        //Poligono de Ruta
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(lat_pos_ini, long_pos_ini), new LatLng(lat_pos_fin, long_pos_fin))
                .width(5)
                .color(Color.GREEN));

    }
}


