package com.example.tawi.rutasproyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ingresar=(Button)findViewById(R.id.button2);
        ingresar.setOnClickListener(new View.OnClickListener()
                                    {


                                        @Override
                                        public void onClick(View v) {

                                            Intent pag=new Intent(MainActivity.this, com.example.tawi.rutasproyecto.MapsActivity.class);
                                            startActivity(pag);
                                        }
                                    }
        );
    }
}
