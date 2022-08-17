package com.example.ordinariopuntodeventa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class inventario extends AppCompatActivity {
        Button buton1, buton2, buton3 , buton4;
    EditText addnombre, addprecio, addcantidad, addid, id, newprecio,newcantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        buton1 = (Button) findViewById(R.id.buton1);
        buton2 = (Button) findViewById(R.id.buton2);
        buton3 = (Button) findViewById(R.id.buton3);
        buton4 = (Button) findViewById(R.id.buton4);

        addnombre = (EditText) findViewById(R.id.addnombre);
        addprecio = (EditText) findViewById(R.id.addprecio);
        addcantidad = (EditText) findViewById(R.id.addcantidad);
        addid = (EditText) findViewById(R.id.addid);
        id = (EditText) findViewById(R.id.id);
        newprecio= (EditText) findViewById(R.id.newprecio);
        newcantidad = (EditText) findViewById(R.id.newcantidad);





        buton4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(inventario.this,MainActivity.class);
                startActivity(intent);
            }

        });

        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(inventario.this);
                final View customlayout = getLayoutInflater().inflate(R.layout.insertar, null);
                alert.setCancelable(false);
                EditText nombre = customlayout.findViewById(R.id.addnombre);
                EditText precio = customlayout.findViewById(R.id.addprecio );
                EditText cantidad = customlayout.findViewById(R.id.addcantidad);

                alert.setView(customlayout);
                alert.setPositiveButton("guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!nombre.getText().toString().equals("") && !precio.getText().toString().equals("")&&!cantidad.getText().toString().equals(""))
                            Guardar (nombre.getText().toString(),precio.getText().toString(),cantidad.getText().toString());
                        else
                            Toast.makeText(getApplicationContext(), "favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getApplicationContext(), "cambios descartados", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });

        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(inventario.this);
                final View customlayout = getLayoutInflater().inflate(R.layout.eliminar, null);
                alert.setCancelable(false);
                EditText id = customlayout.findViewById(R.id.addid);

                alert.setView(customlayout);
                alert.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Eliminar(id.getText().toString());
                    }
                });
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getApplicationContext(), "Cancelando", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();

            }
        });





    }


    private void Guardar(String nombres, String precios, String cantidades) {
        base admin = new base(getApplicationContext(), "OrdinarioBD", null, 1);
        SQLiteDatabase basedatos= admin.getReadableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombres);
        registro.put("precio", precios);
        registro.put("cantidad", cantidades);
        basedatos.insert("productos", null, registro);
        basedatos.close();
        Toast.makeText(getApplicationContext(), "Registro Insertado con exito", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), inventario.class);
        startActivity(intent);
        addnombre.setText("");
        addprecio.setText("");
        addcantidad.setText("");

    }
    private void Eliminar(String nombres) {
        base  admin = new base(this, "OrdinarioBD", null, 1);
        SQLiteDatabase basedatos = admin.getReadableDatabase();
        basedatos.delete( "productos", "nombre='"+nombres+"'", null);
        basedatos.close();
        Toast.makeText(getApplicationContext(), "Registro Eliminado Con Exito!! ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),  inventario.class);
        startActivity(intent);
        addid.setText("");

    }


}