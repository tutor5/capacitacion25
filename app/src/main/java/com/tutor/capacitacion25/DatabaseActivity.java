package com.tutor.capacitacion25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("productos");
    EditText etnombre,etdescripcion,etprecio;
    Button btnagregar, btlistar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        etnombre = findViewById(R.id.etnombre);
        etdescripcion = findViewById(R.id.etdescripcion);
        etprecio = findViewById(R.id.etprecio);
        btnagregar = findViewById(R.id.button_agregar);
        btlistar = findViewById(R.id.button_listar);




        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = etnombre.getText().toString();
                String descripcion = etdescripcion.getText().toString();
                String precio = etprecio.getText().toString();
                Productos producto = new Productos(nombre,descripcion,precio);

                agregar_producto(producto);
            }
        });



        btlistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DatabaseActivity.this,ListarProductos.class);
                startActivity(i);
            }
        });






    }

    public void agregar_producto(Productos producto){

        myRef.push().setValue(producto);


    }
}