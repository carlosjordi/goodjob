package com.goodjob.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.goodjob.R;
import com.goodjob.adapter.EstadoMisPublicacionesAdapter;

public class EstadoMisPublicacionesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView titulo;
    public TextView fecha;
    public TextView postulantes;
    private EstadoMisPublicacionesAdapter.OnEstadoMisPublicacionesListener onEstadoMisPublicacionesListener;

    public EstadoMisPublicacionesViewHolder(@NonNull View itemView, EstadoMisPublicacionesAdapter.OnEstadoMisPublicacionesListener onEstadoMisPublicacionesListener) {
        super(itemView);

        titulo = itemView.findViewById(R.id.tvTituloMisPublicaciones);
        fecha = itemView.findViewById(R.id.tvFechaMisPublicaciones);
        postulantes = itemView.findViewById(R.id.tvCantidadPostulantes);
        this.onEstadoMisPublicacionesListener = onEstadoMisPublicacionesListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onEstadoMisPublicacionesListener.onMisPublicacionesClick(getAdapterPosition());
    }
}