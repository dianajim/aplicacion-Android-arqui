package com.example.tawi.rutasproyecto;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    TextView tvLatitud, tvLongitud, tvAltura, tvPrecision;
    LocationManager locManager;
    Location loc;
    public String bestProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLatitud = (TextView)findViewById(R.id.tvLatitud);
        tvLongitud = (TextView)findViewById(R.id.tvLongitud);
        tvAltura = (TextView)findViewById(R.id.tvAltura);
        tvPrecision = (TextView)findViewById(R.id.tvPrecision);
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            tvLatitud.setText("No se han definido los permisos necesarios.");
            tvLongitud.setText("");
            tvAltura.setText("");
            tvPrecision.setText("");
            return;

        }else{
            locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(loc== null){
              //  locManager.requestLocationUpdates(bestProvider, 1000, 0, this);
                tvLatitud.setText("No se han permitido el servicio de ubicacion");
            }
            else{
                tvLatitud.setText(String.valueOf(loc.getLatitude()));
                tvLongitud.setText(String.valueOf(loc.getLongitude()));
                tvAltura.setText(String.valueOf(loc.getAltitude()));
                tvPrecision.setText(String.valueOf(loc.getAccuracy()));
            }

        }


        Button ver=(Button)findViewById(R.id.button2);
        ver.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v) {

                                            Intent pag=new Intent(MainActivity.this, com.example.tawi.rutasproyecto.MapsActivity.class);
                                            startActivity(pag);
                                        }
                                    }
        );
        Button nuevo=(Button)findViewById(R.id.button5);
        nuevo.setOnClickListener(new View.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(View v) {

                                            Intent pag=new Intent(MainActivity.this, com.example.tawi.rutasproyecto.Viaje.class);
                                            startActivity(pag);
                                        }
                                    }
        );
    }
}
