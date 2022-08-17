package com.example.ordinariopuntodeventa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class venta extends AppCompatActivity {
    Button buton1 , buton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        buton2 = (Button) findViewById(R.id.buton2);

        buton2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(venta.this,MainActivity.class);
                startActivity(intent);
            }

        });
    }


}