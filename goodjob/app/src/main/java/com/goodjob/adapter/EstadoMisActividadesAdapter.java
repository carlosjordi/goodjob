package com.goodjob.adapter;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goodjob.R;
import com.goodjob.classes.EstadoMisActividadesResponse;
import com.goodjob.viewholder.EstadoMisActividadesViewHolder;

import java.util.List;

public class EstadoMisActividadesAdapter extends RecyclerView.Adapter<EstadoMisActividadesViewHolder> {

    private List<EstadoMisActividadesResponse> misActividades;
    private OnEstadoActividadListener onEstadoActividadListener;

    public EstadoMisActividadesAdapter(List<EstadoMisActividadesResponse> misActividades, OnEstadoActividadListener onEstadoActividadListener) {
        this.misActividades = misActividades;
        this.onEstadoActividadListener = onEstadoActividadListener;
    }

    @NonNull
    @Override
    public EstadoMisActividadesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.estado_mis_actividades_item,
                viewGroup, false);

        return new EstadoMisActividadesViewHolder(view, onEstadoActividadListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EstadoMisActividadesViewHolder eav, int posicion) {
        eav.titulo.setText(misActividades.get(posicion).getTitulo());
        eav.fecha.setText(dateFormat(posicion));
        eav.estado.setText(misActividades.get(posicion).getEstado());
        eav.distrito.setText(misActividades.get(posicion).getDistrito());
        if(misActividades.get(posicion).getEstado().equals("Aceptado")){
            eav.item.setBackgroundTintList(ContextCompat.getColorStateList(eav.itemView.getContext(), R.color.colorAceptado));
        }if(misActividades.get(posicion).getEstado().equals("En Espera")){
            eav.item.setBackgroundTintList(ContextCompat.getColorStateList(eav.itemView.getContext(), R.color.colorEnEspera));
        }if(misActividades.get(posicion).getEstado().equals("Rechazado")){
            eav.item.setBackgroundTintList(ContextCompat.getColorStateList(eav.itemView.getContext(), R.color.colorRechazado));
        }

    }

    @Override
    public int getItemCount() {
        return misActividades.size();
    }

    private String dateFormat(int posicion) {
        String[] fechaEnPartes = misActividades.get(posicion).getFecha().split("-");
        return fechaEnPartes[2] + "/" + fechaEnPartes[1] + "/" + fechaEnPartes[0];
    }

    public interface OnEstadoActividadListener {
        void onEstadoActividadClick(int posicion);
    }
}