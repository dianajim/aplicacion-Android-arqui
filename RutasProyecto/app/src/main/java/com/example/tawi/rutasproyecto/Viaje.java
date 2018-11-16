package com.example.tawi.rutasproyecto;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.tawi.rutasproyecto.R.id.activity_viaje;

public class Viaje extends AppCompatActivity {
    public  LocationManager locManager;
    public  Location loc;
    public double lat_pos_act=0; //latitud de Posicion actual de viaje
    public double long_pos_act=0;//longitud de posicion actual de Viaje
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viaje);

        Button ver=(Button)findViewById(R.id.button4);
        ver.setOnClickListener(new View.OnClickListener()
                               {
                                   @Override
                                   public void onClick(View v) {
                                       obtener_coordenadas(); //obtiene las coordenas de la posicion actual
                                       //insertar datos en la bd



                                       //regresar a activity inicial
                                       Intent pag=new Intent(Viaje.this, com.example.tawi.rutasproyecto.MainActivity.class);
                                       startActivity(pag);

                                   }
                               }
        );





    }
    public void obtener_coordenadas(){
        // Obtencion de la ubicacion actual por medio de gps
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            //Error si no se puede obtener la ruta actual
            lat_pos_act=14.626597; //latitud de Posicion actual de viaje
            long_pos_act=-90.555731;//longitud de posicion actual de Viaje
            return;

        }else{
            locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(loc== null){
                //error si no se puede obtener la ruta actual
                lat_pos_act=14.626597; //latitud de Posicion actual de viaje
                long_pos_act=-90.555731;//longitud de posicion actual de Viaje
            }
            else{
                //obtencion de coordenadas de ruta correcta actual
                lat_pos_act=loc.getLatitude(); //latitud de Posicion actual de viaje
                long_pos_act=loc.getLongitude();//longitud de posicion actual de Viaje
            }

        }

    }

}
