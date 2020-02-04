package com.example.goodjob.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goodjob.R;
import com.example.goodjob.classes.EstadoMisPublicacionesResponse;
import com.example.goodjob.viewholder.EstadoMisPublicacionesViewHolder;

import java.util.List;

public class EstadoMisPublicacionesAdapter extends RecyclerView.Adapter<EstadoMisPublicacionesViewHolder> {

    private List<EstadoMisPublicacionesResponse> publicaciones;
    private OnEstadoMisPublicacionesListener onEstadoMisPublicacionesListener;

    public EstadoMisPublicacionesAdapter(List<EstadoMisPublicacionesResponse> publicaciones, OnEstadoMisPublicacionesListener onEstadoMisPublicacionesListener) {
        this.publicaciones = publicaciones;
        this.onEstadoMisPublicacionesListener = onEstadoMisPublicacionesListener;
    }

    @NonNull
    @Override
    public EstadoMisPublicacionesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.estado_mis_publicaciones_item,
                viewGroup, false);

        return new EstadoMisPublicacionesViewHolder(view, onEstadoMisPublicacionesListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EstadoMisPublicacionesViewHolder evh, int posicion) {
        evh.titulo.setText(publicaciones.get(posicion).getTitulo());
        evh.fecha.setText(formatDate(publicaciones.get(posicion).getFecha()));
        evh.postulantes.setText(String.valueOf(publicaciones.get(posicion).getPostulantes()));
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    private String formatDate(String fecha) {
        String[] fechaEnPartes = fecha.split("-");
        return fechaEnPartes[2] + "/" + fechaEnPartes[1] + "/" + fechaEnPartes[0];
    }

    public interface OnEstadoMisPublicacionesListener {
        void onMisPublicacionesClick(int posicion);
    }
}