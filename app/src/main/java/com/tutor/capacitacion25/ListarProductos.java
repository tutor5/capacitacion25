package com.tutor.capacitacion25;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListarProductos extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("productos");
    private RecyclerView recyclerView;
    private Adaptador adaptador;
    private List<Productos> listaproductos;
    ArrayList<Productos> productos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_productos);
        recyclerView = findViewById(R.id.reciclerproductos);
        myRef.addChildEventListener(childEventListener);

        listaproductos = new ArrayList<>();
        adaptador = new Adaptador(listaproductos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptador);
    }


    public void refrescarproductos() {

        if (adaptador == null) return;
        listaproductos = productos;
        //Log.d("prueba ubicacion", String.valueOf(ubicaciones));
        adaptador.setlistaproductos(listaproductos);
        adaptador.notifyDataSetChanged();
    }

    public void mostrarproductos(Productos producto) {
        //Log.d("prueba ubicacion 2", String.valueOf(ubicacion));
        productos.add(producto);
        refrescarproductos();
    }



    ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

            // A new comment has been added, add it to the displayed list
            Productos producto = dataSnapshot.getValue(Productos.class);
            // ..
            if (producto != null) mostrarproductos(producto);

            // ...
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            // A new comment has been added, add it to the displayed list
            Productos producto = dataSnapshot.getValue(Productos.class);
            // ..
            if (producto != null) mostrarproductos(producto);



        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

            // A comment has changed, use the key to determine if we are displaying this
            // comment and if so remove it.
            String commentKey = dataSnapshot.getKey();

            // ...
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

            // A comment has changed position, use the key to determine if we are
            // displaying this comment and if so move it.
            // A new comment has been added, add it to the displayed list
            Productos producto = dataSnapshot.getValue(Productos.class);
            // ..
            if (producto != null) mostrarproductos(producto);


            // ...
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }


    };




}