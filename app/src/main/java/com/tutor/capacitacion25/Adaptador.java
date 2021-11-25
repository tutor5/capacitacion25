package com.tutor.capacitacion25;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MyViewHolder>{

    private List<Productos> listaproductos;

    public void setlistaproductos(List<Productos> listaproductos) {
        this.listaproductos = listaproductos;
    }

    public Adaptador(List<Productos> ubicaciones) {
        this.listaproductos = ubicaciones;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaProducto = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.productos, viewGroup, false);
        return new MyViewHolder(filaProducto);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Productos producto = listaproductos.get(i);


        String nombre = producto.getNombre();
        String descripcion = producto.getDescripcion();
        String precio = producto.getPrecio();

        myViewHolder.nombre.setText(nombre);
        myViewHolder.descripcion.setText(descripcion);
        myViewHolder.precio.setText(precio);

    }

    @Override
    public int getItemCount() {
        return listaproductos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,descripcion, precio;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nombre = itemView.findViewById(R.id.tvnombre);
            this.descripcion = itemView.findViewById(R.id.tvdescripcion);
            this.precio = itemView.findViewById(R.id.tvprecio);

        }
    }

}